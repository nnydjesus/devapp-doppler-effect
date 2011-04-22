package ar.edu.unq.dopplereffect.projects;

import org.apache.commons.collections15.Predicate;

import ar.edu.unq.dopplereffect.employees.Employee;

/**
 * Predicate para saber si un empleado esta en esa asignacion asignacion util
 * para {@link CollectionUtils}
 */
public class ProjectAssignmentPredicate implements Predicate<ProjectAssignment> {
    /* ************************ INSTANCE VARIABLES ************************ */

    private Employee employee;

    /* *************************** CONSTRUCTORS *************************** */

    /* **************************** OPERATIONS **************************** */

    public ProjectAssignmentPredicate(final Employee anEmployee) {
        this.setEmployee(anEmployee);
    }

    @Override
    public boolean evaluate(final ProjectAssignment assignment) {
        return assignment.isAssigned(this.getEmployee());
    }

    public void setEmployee(final Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }

}
