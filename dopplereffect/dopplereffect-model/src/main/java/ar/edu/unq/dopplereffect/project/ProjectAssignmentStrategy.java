package ar.edu.unq.dopplereffect.project;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.joda.time.Interval;

import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.entity.Entity;
import ar.edu.unq.dopplereffect.exceptions.UserException;
import ar.edu.unq.dopplereffect.time.IntervalDurationStrategy;

/**
 */
public class ProjectAssignmentStrategy extends Entity {
    private static final long serialVersionUID = 1L;

    private transient Project project;

    public void manualAssignment(final Project anProject, final Employee employee,
            final IntervalDurationStrategy interval) {
        project = anProject;
        this.internalManualAssignment(employee, interval);

    }

    public void internalManualAssignment(final Employee employee, final IntervalDurationStrategy interval) {
        this.validateAssignment(employee, interval);
        final ProjectAssignment projectAssignment = project.findOrCreateAssignment(employee);
        projectAssignment.addInterval(interval);
        employee.addAssignment(projectAssignment);
    }

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

    protected void tryToAssign(final IntervalDurationStrategy intervalDurationStrategy, final Employee employee) {
        IntervalDurationStrategy availableInterval = employee.getAvailableInterval(intervalDurationStrategy);
        if (project.validateEffort(availableInterval)) {
            this.internalManualAssignment(employee, availableInterval);
        } else {
            int missingDays = ProjectHelper.hoursEffortToDays(project.getMaxEffort() - project.getCurrentEffort());
            if (availableInterval.getAmountOfDays() >= missingDays) {
                IntervalDurationStrategy reducedInterval = new IntervalDurationStrategy(new Interval(
                        availableInterval.getStartDate(), availableInterval.getStartDate().plusDays(missingDays - 1)));
                if (project.validateEffort(reducedInterval)) {
                    this.internalManualAssignment(employee, reducedInterval);
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
        if (!employee.isFreeAtInterval(interval.getInterval())) {
            throw new UserException("El empleado no esta libre en el intervalo " + interval);
        }
        if (project.validateEffort(interval)) {
            int hoursAssignment = project.getHoursOfEffort(interval);
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
        return employee.availabilityLevel(intervalDurationStrategy)
                + employee.sastisfaccionLevelOfSkills(project.getSkills());
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

    public Project getProject() {
        return project;
    }

}
