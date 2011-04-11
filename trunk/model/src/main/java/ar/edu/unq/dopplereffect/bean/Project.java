package ar.edu.unq.dopplereffect.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.Interval;
import org.joda.time.Period;

import ar.edu.unq.dopplereffect.exception.UserException;

/**
 * TODO: description
 */
public class Project {

    private String name;

    private InformationClient informationClient;

    private Period consideredEffor;

    private List<Skill> skils;

    private boolean actidated;

    private Map<Employee, Interval> assignedEmployee = new HashMap<Employee, Interval>();

    public Project(final String name) {
        this();
        this.name = name;
    }

    public Project() {
        super();
    }

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

    public void setAssignedEmployee(final Map<Employee, Interval> assignedEmployee) {
        this.assignedEmployee = assignedEmployee;
    }

    public Map<Employee, Interval> getAssignedEmployee() {
        return assignedEmployee;
    }

    // FIXME renombrarme
    public void manualAssignment(final Employee employee, final Interval interval) {
        this.validateAssignment(employee, interval);
        assignedEmployee.put(employee, interval);
    }

    protected void validateAssignment(final Employee employee, final Interval interval) {
        if (this.isAssigned(employee)) {
            if (assignedEmployee.get(employee).overlaps(interval)) {
                throw new UserException(
                        "El empleado no puede tener dos asignaciones en el proyecto en un mismo intervalo");
                // FIXME cambiar el mensaje
            }
        }

        if (interval.getEnd().isAfter(interval.getStart().plus(consideredEffor))) {
            throw new UserException("El tiempo asignado no puede superar al tiempo del proyecto");
        }
    }

    public void addSkill(final Skill skill) {
        skils.add(skill);
    }

    public boolean isAssigned(final Employee employee) {
        return assignedEmployee.containsKey(employee);
    }

    public void activated() {
        actidated = true;
    }

    public boolean isActidated() {
        return actidated;
    }

    public void setConsideredEffor(final Period consideredEffor) {
        this.consideredEffor = consideredEffor;
    }

    public Period getConsideredEffor() {
        return consideredEffor;
    }

}
