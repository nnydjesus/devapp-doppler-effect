package ar.edu.unq.dopplereffect.projects;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections15.CollectionUtils;
import org.joda.time.Period;

import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.exceptions.UserException;
import ar.edu.unq.dopplereffect.time.IntervalDurationStrategy;

/**
 */
public class Project {

    /* ************************ INSTANCE VARIABLES ************************ */

    private String name;

    private ClientData clientData;

    private Period consideredEffort;

    private List<Skill> skils;

    private List<ProjectAssignment> assignedEmployees = new ArrayList<ProjectAssignment>();

    /* *************************** CONSTRUCTORS *************************** */

    public Project(final String name) {
        this();
        this.name = name;
    }

    public Project() {
        super();
    }

    /* **************************** OPERATIONS **************************** */

    public void manualAssignment(final Employee employee, final IntervalDurationStrategy interval) {
        this.validateAssignment(employee, interval);
        final ProjectAssignment projectAssignment = this.findOrCreateAssignment(employee);
        projectAssignment.addInterval(interval);
        employee.addAssignment(projectAssignment);
    }

    protected void validateAssignment(final Employee employee, final IntervalDurationStrategy interval) {
        final ProjectAssignment assignment = this.getAssignment(employee);
        if (assignment != null && assignment.overlapsAssignment(interval)) {
            throw new UserException("El empleado no puede tener dos asignaciones en el proyecto en un mismo intervalo");
        }
        if (interval.getEndDate().isAfter(interval.getStartDate().plus(consideredEffort))) {
            throw new UserException("El tiempo asignado no puede superar al tiempo del proyecto");
        }
        if (!employee.isFreeAtInterval(interval.getInterval())) {
            throw new UserException("El empleado no esta libre en el intervalo " + interval);
        }
    }

    public boolean isAssigned(final Employee employee) {
        return this.getAssignment(employee) != null;
    }

    public void addSkill(final Skill skill) {
        skils.add(skill);
    }

    /**
     * Busca la asignacion de un usuario
     */
    public ProjectAssignment getAssignment(final Employee employee) {
        return CollectionUtils.find(assignedEmployees, new ProjectAssignmentPredicate(employee));
    }

    /**
     * Busca la asignacion de un empleado, en caso de que no tenga crea una y se
     * la guarda
     */
    public ProjectAssignment findOrCreateAssignment(final Employee employee) {
        ProjectAssignment projectAssignment = this.getAssignment(employee);
        if (projectAssignment == null) {
            projectAssignment = new ProjectAssignment(employee);
            assignedEmployees.add(projectAssignment);
        }
        return projectAssignment;
    }

    public boolean isAssignedInInterval(final Employee employee, final IntervalDurationStrategy interval) {
        return this.isAssigned(employee) && this.getAssignment(employee).containsInterval(interval);
    }

    /* **************************** ACCESSORS ***************************** */

    public ClientData getClientData() {
        return clientData;
    }

    public void setClientData(final ClientData clientData) {
        this.clientData = clientData;
    }

    public List<Skill> getSkils() {
        return skils;
    }

    public void setSkils(final List<Skill> skils) {
        this.skils = skils;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAssignedEmployees(final List<ProjectAssignment> assignedEmployees) {
        this.assignedEmployees = assignedEmployees;
    }

    public List<ProjectAssignment> getAssignedEmployees() {
        return assignedEmployees;
    }

    public void setConsideredEffort(final Period consideredEffort) {
        this.consideredEffort = consideredEffort;
    }

    public Period getConsideredEffort() {
        return consideredEffort;
    }

}
