package ar.edu.unq.dopplereffect.projects;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.joda.time.Interval;

import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.employees.EmployeeTimeCalculator;
import ar.edu.unq.dopplereffect.entity.Entity;
import ar.edu.unq.dopplereffect.exceptions.UserException;
import ar.edu.unq.dopplereffect.time.IntervalDurationStrategy;

/**
 */
public class ProjectAssignmentStrategy extends Entity implements IProjectAssignmentStrategy {

    private static final long serialVersionUID = 1L;

    private transient Project project;

    private transient EmployeeTimeCalculator employeeTimeCalculator;

    public Project getProject() {
        return project;
    }

    public void setEmployeeTimeCalculator(final EmployeeTimeCalculator calculator) {
        employeeTimeCalculator = calculator;
    }

    @Override
    public ProjectAssignment manualAssignment(final Project aProject, final Employee employee,
            final IntervalDurationStrategy interval) {
        project = aProject;
        employeeTimeCalculator = new EmployeeTimeCalculator();
        return this.internalManualAssignment(employee, interval);
    }

    private ProjectAssignment internalManualAssignment(final Employee employee, final IntervalDurationStrategy interval) {
        this.validateAssignment(employee, interval);
        final ProjectAssignment projectAssignment = project.findOrCreateAssignment(employee);
        projectAssignment.addInterval(interval);
        employee.addAssignment(projectAssignment);
        return projectAssignment;
    }

    /**
     * XXX no se entiende como esta implementado este metodo, que estrategia
     * utilizan ?? XXX faltaria crear mas test asi queda claro cuales son las
     * estrategias
     */
    @Override
    public void automaticAssignment(final Project anProject, final List<Employee> employees,
            final IntervalDurationStrategy intervalDurationStrategy) {

        project = anProject;
        // ordeno la lista por prioridad
        Collections.sort(employees, this.getComparatorBySkills(intervalDurationStrategy));
        for (Employee employee : employees) {
            this.tryToAssign(intervalDurationStrategy, employee);
            if (project.getMaxEffort() <= project.getCurrentEffort()) {
                return;
            }
        }
    }

    /**
     * XXX Que hace este metodo?? dice Try pero pero lo asigan ?? no deberia
     * retornar un booleano ?
     */
    protected void tryToAssign(final IntervalDurationStrategy intervalDurationStrategy, final Employee employee) {
        List<IntervalDurationStrategy> availableIntervals = employeeTimeCalculator.getAvailableIntervals(employee,
                intervalDurationStrategy);
        for (IntervalDurationStrategy interval : availableIntervals) {
            if (project.validateEffort(interval)) {
                this.internalManualAssignment(employee, interval);
            } else {
                int missingDays = ProjectHelper.hoursEffortToDays(project.getMaxEffort() - project.getCurrentEffort());
                if (interval.getAmountOfDays() >= missingDays) {
                    IntervalDurationStrategy reducedInterval = this.craeteReducedInterval(interval, missingDays);
                    if (project.validateEffort(reducedInterval)) {
                        this.internalManualAssignment(employee, reducedInterval);
                    }
                }
            }
        }
    }

    protected void validateAssignment(final Employee employee, final IntervalDurationStrategy interval) {
        final ProjectAssignment assignment = project.getAssignment(employee);
        if (assignment != null && assignment.overlapsAssignment(interval)) {
            throw new UserException("El empleado no puede tener dos asignaciones en el proyecto en un mismo intervalo");
        }
        if (interval.getEndDate().isAfter(interval.getStartDate().plus(project.getTimeProyect()))) {
            throw new UserException("El tiempo asignado no puede superar al tiempo del proyecto");
        }
        if (!employeeTimeCalculator.isFreeAtInterval(employee, interval.getInterval())) {
            throw new UserException("El empleado no esta libre en el intervalo " + interval);
        }
        if (project.validateEffort(interval)) {
            long hoursAssignment = project.getHoursOfEffort(interval);
            project.plusEffort(hoursAssignment);
        } else {
            throw new UserException("Se ha alcanzado el maximo de horas de esfuerzo del proyecto");
        }
    }

    protected boolean higherPriority(final Employee employee1, final Employee employee2,
            final IntervalDurationStrategy intervalDurationStrategy) {
        return this.getpriorityLevelEmployee(employee1, intervalDurationStrategy) >= this.getpriorityLevelEmployee(
                employee2, intervalDurationStrategy);
    }

    protected int getpriorityLevelEmployee(final Employee employee,
            final IntervalDurationStrategy intervalDurationStrategy) {
        return employeeTimeCalculator.availabilityLevel(employee, intervalDurationStrategy)
                + employee.satisfactionLevelOfSkills(project.getSkills());
    }

    protected Comparator<Employee> getComparatorBySkills(final IntervalDurationStrategy intervalDurationStrategy) {
        return new Comparator<Employee>() {

            @Override
            public int compare(final Employee employee1, final Employee employee2) {
                return ProjectAssignmentStrategy.this.higherPriority(employee1, employee2, intervalDurationStrategy) ? 1
                        : -1;
            }
        };

    }

    private IntervalDurationStrategy craeteReducedInterval(final IntervalDurationStrategy interval,
            final int missingDays) {
        // creado solo porque el PMD chilla
        return new IntervalDurationStrategy(new Interval(interval.getStartDate(), interval.getStartDate().plusDays(
                missingDays - 1)));
    }
}
