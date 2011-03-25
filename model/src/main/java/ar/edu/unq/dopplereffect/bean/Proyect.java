package ar.edu.unq.dopplereffect.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.edu.unq.dopplereffect.exception.UserException;

/**
 * TODO: description
 */
public class Proyect {

    private String name;

    private InformationClient informationClient;

    private int consideredTime;

    private int remainingTime;

    private List<Skill> skils;

    /**
     * Los empleados que estan asignados al proyecto. Cada uno de los empleados
     * se pueden asignar por tiempos diferentes
     */
    private Map<Empleado, Integer> assignedEmployee = new HashMap<Empleado, Integer>();

    public Proyect(final String name, final int consideredTime) {
        this.name = name;
        this.consideredTime = consideredTime;
        remainingTime = consideredTime;
    }

    public Proyect() {
    }

    public InformationClient getInformationClient() {
        return informationClient;
    }

    public void setInformationClient(final InformationClient informationClient) {
        this.informationClient = informationClient;
    }

    public int getConsideredTime() {
        return consideredTime;
    }

    public void setConsideredTime(final int consideredTime) {
        this.consideredTime = consideredTime;
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

    public int getRemainingTime() {
        return remainingTime;
    }

    public void setAssignedEmployee(final Map<Empleado, Integer> assignedEmployee) {
        this.assignedEmployee = assignedEmployee;
    }

    public Map<Empleado, Integer> getAssignedEmployee() {
        return assignedEmployee;
    }

    public void manualAssignment(final Empleado empleado, final int time) {
        if (remainingTime - time >= 0) {
            assignedEmployee.put(empleado, time);
            remainingTime -= time;
        } else
            throw new UserException("No se puede asignar mas horas de la que considero");

    }

    public void addSkill(final Skill skill) {
        skils.add(skill);
    }

}
