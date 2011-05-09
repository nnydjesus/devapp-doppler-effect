package ar.edu.unq.dopplereffect.projects;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections15.CollectionUtils;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;

import ar.edu.unq.dopplereffect.employees.Employee;
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

    private Period timeProyect;

    private List<Skill> skills;

    private Integer maxEffort;

    private Integer currentEffort;

    private List<ProjectAssignment> assignedEmployees = new ArrayList<ProjectAssignment>();

    private ProjectAssignmentStrategy projectAssignmentStrategy;

    /* *************************** CONSTRUCTORS *************************** */

    public Project(final ProjectAssignmentStrategy strategy) {
        this();
        this.setProjectAssignmentStrategy(strategy);
        this.getProjectAssignmentStrategy().setProject(this);
    }

    public Project() {
        super();
        this.setCurrentEffort(0);
    }

    /* **************************** OPERATIONS **************************** */

    /**
     * Lleva a cabo una asignacion, para lo cual recibe el empleado a asignar y
     * el intervalo de la asignacion.
     */
    public void manualAssignment(final Employee employee, final IntervalDurationStrategy interval) {
        this.getProjectAssignmentStrategy().manualAssignment(employee, interval);
    }

    /**
     * @param date
     *            es el dia en el que se quiere asignar y abrir el proyecto, pro
     *            ejemplo: yo creo el proyecto ahora y qiero que se haga la
     *            asignacion automatica, pero lo voy abrir dentro de 2 semanas,
     *            entonces quiero que se realize todas las verificaciones a
     *            partir de esa fecha
     * 
     */
    public void automaticAssignment(final List<Employee> employees, final DateTime date) {
        IntervalDurationStrategy intervalDurationStrategy = new IntervalDurationStrategy(new Interval(date,
                date.plus(this.getTimeProyect())));
        this.getProjectAssignmentStrategy().automaticAssignment(employees, intervalDurationStrategy);
    }

    protected boolean validateEffort(final IntervalDurationStrategy interval) {
        int hoursAssignment = this.getHoursOfEffort(interval);
        return maxEffort >= this.getCurrentEffort() + hoursAssignment;
    }

    protected int getHoursOfEffort(final IntervalDurationStrategy interval) {
        return ProjectHelper.daysToHoursEffort(interval.getAmountOfDays());
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

    public void setConsideredEffort(final Period consideredEffort) {
        this.setTimeProyect(consideredEffort);
    }

    public Integer getCurrentEffort() {
        return currentEffort;
    }

    public void setMaxEffort(final Integer maxEffort) {
        this.maxEffort = maxEffort;
    }

    public Integer getMaxEffort() {
        return maxEffort;
    }

    public void setTimeProyect(final Period timeProyect) {
        this.timeProyect = timeProyect;
    }

    public Period getTimeProyect() {
        return timeProyect;
    }

    public void setAssignedEmployees(final List<ProjectAssignment> assignedEmployees) {
        this.assignedEmployees = assignedEmployees;
    }

    public void setProjectAssignmentStrategy(final ProjectAssignmentStrategy projectAssignmentStrategy) {
        this.projectAssignmentStrategy = projectAssignmentStrategy;
    }

    public ProjectAssignmentStrategy getProjectAssignmentStrategy() {
        return projectAssignmentStrategy;
    }

    public void setCurrentEffort(final Integer currentEffort) {
        this.currentEffort = currentEffort;
    }

    public void plusEffort(final int hoursAssignment) {
        this.setCurrentEffort(this.getCurrentEffort() + hoursAssignment);
    }
}
