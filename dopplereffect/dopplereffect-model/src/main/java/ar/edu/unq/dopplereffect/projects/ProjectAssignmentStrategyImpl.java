package ar.edu.unq.dopplereffect.projects;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.joda.time.Interval;

import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.employees.EmployeeTimeCalculator;
import ar.edu.unq.dopplereffect.entity.Entity;
import ar.edu.unq.dopplereffect.exceptions.AssignmentException;
import ar.edu.unq.dopplereffect.time.IntervalDurationStrategy;

/**
 */
public class ProjectAssignmentStrategyImpl extends Entity implements IProjectAssignmentStrategy {

    private static final long serialVersionUID = 1L;

    private static final String HAVE_ASSIGNMENT_IN_INTERVAL = "haveAssignment";

    private static final String TIME_EXCEEDED = "timeExceeded";

    private static final String EMPLOYEE_NOT_FREE = "employeeNotFree";

    private static final String MAX_EFFORT = "maxEffort";

    private transient Project project;

    private transient EmployeeTimeCalculator employeeTimeCalculator;

    public ProjectAssignmentStrategyImpl() {
        employeeTimeCalculator = new EmployeeTimeCalculator();
    }

    @Override
    public ProjectAssignment manualAssignment(final Project aProject, final Employee employee,
            final IntervalDurationStrategy interval) {
        this.setProject(aProject);
        return this.internalManualAssignment(employee, interval);
    }

    private ProjectAssignment internalManualAssignment(final Employee employee, final IntervalDurationStrategy interval) {
        this.validateAssignment(employee, interval);
        final ProjectAssignment projectAssignment = this.getProject().findOrCreateAssignment(employee);
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

        this.setProject(anProject);
        // ordeno la lista por prioridad
        Collections.sort(employees, this.getComparatorBySkills(intervalDurationStrategy));
        for (Employee employee : employees) {
            this.assignInAvailableIntervals(intervalDurationStrategy, employee);
            if (this.isTimeProjectExceeded()) {
                return;
            }
        }
    }

    protected boolean isTimeProjectExceeded() {
        return this.getProject().getMaxEffort() <= this.getProject().getCurrentEffort();
    }

    protected void assignInAvailableIntervals(final IntervalDurationStrategy intervalDurationStrategy,
            final Employee employee) {
        List<IntervalDurationStrategy> availableIntervals = employeeTimeCalculator.getAvailableIntervals(employee,
                intervalDurationStrategy);
        for (IntervalDurationStrategy interval : availableIntervals) {
            if (this.getProject().validateEffort(interval)) {
                this.internalManualAssignment(employee, interval);
            } else {
                int missingDays = ProjectHelper.hoursEffortToDays(this.getProject().getMaxEffort()
                        - this.getProject().getCurrentEffort());
                if (interval.getAmountOfDays() >= missingDays) {
                    IntervalDurationStrategy reducedInterval = this.craeteReducedInterval(interval, missingDays);
                    if (this.getProject().validateEffort(reducedInterval)) {
                        this.internalManualAssignment(employee, reducedInterval);
                    }
                }
            }
        }
    }

    protected void validateAssignment(final Employee employee, final IntervalDurationStrategy interval) {
        final ProjectAssignment assignment = this.getProject().getAssignment(employee);
        if (assignment != null && assignment.overlapsAssignment(interval)) {
            throw new AssignmentException(HAVE_ASSIGNMENT_IN_INTERVAL,
                    "El empleado no puede tener dos asignaciones en el proyecto en un mismo intervalo");
        }
        if (interval.getEndDate().isAfter(interval.getStartDate().plus(this.getProject().getTimeProyect()))) {
            throw new AssignmentException(TIME_EXCEEDED, "El tiempo asignado no puede superar al tiempo del proyecto");
        }
        if (!employeeTimeCalculator.isFreeAtInterval(employee, interval.getInterval())) {
            throw new AssignmentException(EMPLOYEE_NOT_FREE, "El empleado no esta libre en el intervalo "
                    + interval.toString(), interval.toString());
        }
        if (this.getProject().validateEffort(interval)) {
            long hoursAssignment = this.getProject().getHoursOfEffort(interval);
            this.getProject().plusEffort(hoursAssignment);
        } else {
            throw new AssignmentException(MAX_EFFORT, "Se ha alcanzado el maximo de horas de esfuerzo del proyecto");
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
                + employee.satisfactionLevelOfSkills(this.getProject().getSkills());
    }

    protected Comparator<Employee> getComparatorBySkills(final IntervalDurationStrategy intervalDurationStrategy) {
        return new Comparator<Employee>() {

            @Override
            public int compare(final Employee employee1, final Employee employee2) {
                return ProjectAssignmentStrategyImpl.this
                        .higherPriority(employee1, employee2, intervalDurationStrategy) ? 1 : -1;
            }
        };

    }

    private IntervalDurationStrategy craeteReducedInterval(final IntervalDurationStrategy interval,
            final int missingDays) {
        // creado solo porque el PMD chilla
        return new IntervalDurationStrategy(new Interval(interval.getStartDate(), interval.getStartDate().plusDays(
                missingDays - 1)));
    }

    @Override
    public Project getProject() {
        return project;
    }

    public void setEmployeeTimeCalculator(final EmployeeTimeCalculator calculator) {
        employeeTimeCalculator = calculator;
    }

    @Override
    public void setProject(final Project project) {
        this.project = project;
    }
}
