package ar.edu.unq.dopplereffect.projects;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections15.CollectionUtils;
import org.joda.time.Period;

import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.exceptions.UserException;
import ar.edu.unq.dopplereffect.time.IntervalDurationStrategy;

/**
 * Representa los proyectos de la empresa. Un proyecto tiene un nombre, datos
 * del cliente, un esfuerzo considerado, una serie de conocimientos necesarios
 * para abordarlo, y por ultimo una lista de asignaciones de empleados.
 */
public class Project {

    /* ************************ INSTANCE VARIABLES ************************ */

    private String name;

    private ClientData clientData;

    private Period consideredEffort;

    private List<Skill> skills;

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

    /**
     * Lleva a cabo una asignacion, para lo cual recibe el empleado a asignar y
     * el intervalo de la asignacion.
     */
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

    /**
     * Verifica si un empleado esta asignado en este proyecto.
     */
    public boolean isAssigned(final Employee employee) {
        return this.getAssignment(employee) != null;
    }

    /**
     * Agrega un skill a la lista de skills del proyecto.
     */
    public void addSkill(final Skill skill) {
        this.getSkills().add(skill);
    }

    /**
     * Busca la asignacion de un empleado dado.
     * 
     * @param employee
     *            el empleado.
     * @return la asignacion correspondiente al empleado, o <code>null</code> si
     *         no existe tal asignacion.
     */
    public ProjectAssignment getAssignment(final Employee employee) {
        return CollectionUtils.find(this.getAssignedEmployees(), new ProjectAssignmentPredicate(employee));
    }

    /**
     * Busca la asignacion de un empleado, en caso de que no tenga crea una y se
     * la guarda.
     */
    public ProjectAssignment findOrCreateAssignment(final Employee employee) {
        ProjectAssignment projectAssignment = this.getAssignment(employee);
        if (projectAssignment == null) {
            projectAssignment = new ProjectAssignment(employee);
            this.getAssignedEmployees().add(projectAssignment);
        }
        return projectAssignment;
    }

    /**
     * Verifica si un empleado esta asignado en un intervalo dado.
     * 
     * @param employee
     *            el empleado que se desea verificar.
     * @param interval
     *            el intervalo que se desea verificar.
     * @return <code>true</code> si esta asignado, <code>false</code> en caso
     *         contrario.
     */
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

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(final List<Skill> skills) {
        this.skills = skills;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<ProjectAssignment> getAssignedEmployees() {
        return assignedEmployees;
    }

    public void setAssignedEmployees(final List<ProjectAssignment> assignedEmployees) {
        this.assignedEmployees = assignedEmployees;
    }

    public Period getConsideredEffort() {
        return consideredEffort;
    }

    public void setConsideredEffort(final Period consideredEffort) {
        this.consideredEffort = consideredEffort;
    }
}
