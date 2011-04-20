package ar.edu.unq.dopplereffect.bean;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections15.CollectionUtils;
import org.joda.time.Period;

import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.exception.UserException;
import ar.edu.unq.dopplereffect.time.IntervalDurationStrategy;

/**
 */
public class Project {

    /* ************************ INSTANCE VARIABLES ************************ */

    private String name;

    private InformationClient informationClient;

    private Period consideredEffor;

    private List<Skill> skils;

    private List<ProjectAssignment> assignedEmployee = new ArrayList<ProjectAssignment>();

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
        ProjectAssignment projectAssignment = this.findOrCreateAssignment(employee);
        projectAssignment.addInterval(interval);
        employee.addAssignment(projectAssignment);
    }

    protected void validateAssignment(final Employee employee, final IntervalDurationStrategy interval) {
        ProjectAssignment assignment = this.getAssignment(employee);
        if (assignment != null && assignment.overlapsAssignment(interval))
            throw new UserException("El empleado no puede tener dos asignaciones en el proyecto en un mismo intervalo");
        if (interval.getEndDate().isAfter(interval.getStartDate().plus(consideredEffor)))
            throw new UserException("El tiempo asignado no puede superar al tiempo del proyecto");
        if (!employee.isFreeAtInterval(interval.getInterval()))
            throw new UserException("El empleado no esta libre en el intervalo " + interval);
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
        return CollectionUtils.find(assignedEmployee, new ProjectAssignmentPredicate(employee));
    }

    /**
     * Busca la asignacion de un empleado, en caso de que no tenga crea una y se
     * la guarda
     */
    public ProjectAssignment findOrCreateAssignment(final Employee employee) {
        ProjectAssignment projectAssignment = this.getAssignment(employee);
        if (projectAssignment == null) {
            projectAssignment = new ProjectAssignment(employee);
            assignedEmployee.add(projectAssignment);
        }
        return projectAssignment;
    }

    public boolean isAssignedInInverval(final Employee employee, final IntervalDurationStrategy interval) {
        return this.isAssigned(employee) && this.getAssignment(employee).containsInterval(interval);
    }

    /* **************************** ACCESSORS ***************************** */

    public InformationClient getInformationClient() {
        return informationClient;
    }

    public void setInformationClient(final InformationClient informationClient) {
        this.informationClient = informationClient;
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

    public void setAssignedEmployee(final List<ProjectAssignment> assignedEmployee) {
        this.assignedEmployee = assignedEmployee;
    }

    public List<ProjectAssignment> getAssignedEmployee() {
        return assignedEmployee;
    }

    public void setConsideredEffor(final Period consideredEffor) {
        this.consideredEffor = consideredEffor;
    }

    public Period getConsideredEffor() {
        return consideredEffor;
    }

}
