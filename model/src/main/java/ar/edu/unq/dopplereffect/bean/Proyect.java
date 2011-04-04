package ar.edu.unq.dopplereffect.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO: description
 */
public class Proyect {

    private String name;

    private Client informationClient;

    private int consideredTime;

    private List<Skill> skils;

    private boolean actidated;

    private Map<Empleado, Integer> assignedEmployee = new HashMap<Empleado, Integer>();

    public Proyect(final String name) {
        this.name = name;
    }

    public Proyect() {
    }

    public Client getInformationClient() {
        return informationClient;
    }

    public void setInformationClient(final Client informationClient) {
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

    public void setAssignedEmployee(final Map<Empleado, Integer> assignedEmployee) {
        this.assignedEmployee = assignedEmployee;
    }

    public Map<Empleado, Integer> getAssignedEmployee() {
        return assignedEmployee;
    }

    public void manualAssignment(final Empleado empleado, final int time) {
        // if (remainingTime - time >= 0) {
        // assignedEmployee.put(empleado, time);
        // remainingTime -= time;
        // } else
        // throw new
        // UserException("No se puede asignar mas horas de la que considero");

    }

    public void addSkill(final Skill skill) {
        skils.add(skill);
    }

    protected void setActidated(final boolean actidated) {
        this.actidated = actidated;
    }

    public void activated() {
        actidated = true;

    }

    public boolean isActidated() {
        return actidated;
    }

    public void setConsideredTime(final int mounth, final int days) {
        this.setConsideredTime((mounth * 160 + days * 8));
    }

    public void setConsideredTime(final int consideredTime) {
        this.consideredTime = consideredTime;
    }

    public int getConsideredTime() {
        return consideredTime;
    }

}
