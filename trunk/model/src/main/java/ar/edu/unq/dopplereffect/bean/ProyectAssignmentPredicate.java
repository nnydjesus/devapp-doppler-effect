package ar.edu.unq.dopplereffect.bean;

import org.apache.commons.collections15.Predicate;

/**
 * Predicate para saber si un empleado esta en esa asignacion asignacion util
 * para {@link CollectionUtils}
 */
public class ProyectAssignmentPredicate implements Predicate<ProjectAssignment> {

    private Employee employee;

    public ProyectAssignmentPredicate(final Employee anEmployee) {
        employee = anEmployee;
    }

    @Override
    public boolean evaluate(final ProjectAssignment assignment) {
        return assignment.isAssigned(employee);
    }

}
