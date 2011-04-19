package ar.edu.unq.dopplereffect.bean;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections15.CollectionUtils;
import org.apache.log4j.Logger;
import org.joda.time.Interval;
import org.joda.time.Period;

import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.exception.UserException;

/**
 */
public class Project {

    private static final Logger LOGGER = Logger.getLogger(Project.class);

    /* ************************ INSTANCE VARIABLES ************************ */

    private String name;

    private InformationClient informationClient;

    private Period consideredEffor;

    private List<Skill> skils;

    private boolean actidated;

    private List<ProjectAssignment> assignedEmployee = new ArrayList<ProjectAssignment>();

    /* *************************** CONSTRUCTORS *************************** */

    public Project(final String name) {
        this();
        this.name = name;
    }

    public Project() {
        super();
    }

    /* **************************** ACCESSORS ***************************** */

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<Skill> getSkils() {
        return skils;
    }

    public void setSkils(final List<Skill> skils) {
        this.skils = skils;
    }

    public Period getConsideredEffor() {
        return consideredEffor;
    }

    public void setConsideredEffor(final Period consideredEffor) {
        this.consideredEffor = consideredEffor;
    }

    public List<ProjectAssignment> getAssignedEmployee() {
        return assignedEmployee;
    }

    public void setAssignedEmployee(final List<ProjectAssignment> assignedEmployee) {
        this.assignedEmployee = assignedEmployee;
    }

    public InformationClient getInformationClient() {
        return informationClient;
    }

    public void setInformationClient(final InformationClient informationClient) {
        this.informationClient = informationClient;
    }

    /* **************************** OPERATIONS **************************** */

    // FIXME renombrarme
    public void manualAssignment(final Employee employee, final Interval interval) {
        this.validateAssignment(employee, interval);
        this.findOrCreateAssignment(employee).addInterval(interval);
    }

    protected void validateAssignment(final Employee employee, final Interval interval) {
        LOGGER.info("\n validando en la asignacion el empleado:  " + employee + " con este intervalo: " + interval);
        ProjectAssignment assignment = this.getAssignment(employee);
        if (assignment != null && assignment.overlapsAssignment(interval)) {
            throw new UserException("El empleado no puede tener dos asignaciones en el proyecto en un mismo intervalo");
        }
        if (interval.getEnd().isAfter(interval.getStart().plus(consideredEffor))) {
            throw new UserException("El tiempo asignado no puede superar al tiempo del proyecto");
        }
    }

    public boolean isAssigned(final Employee employee) {
        return this.getAssignment(employee) != null;
    }

    public void addSkill(final Skill skill) {
        skils.add(skill);
    }

    public ProjectAssignment getAssignment(final Employee employee) {
        return CollectionUtils.find(assignedEmployee, new ProyectAssignmentPredicate(employee));
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

    public boolean isAssignedInInverval(final Employee employee, final Interval interval) {
        return this.isAssigned(employee) && this.getAssignment(employee).containsInterval(interval);
    }

    public void activated() {
        actidated = true;
    }

    public boolean isActidated() {
        return actidated;
    }
}
