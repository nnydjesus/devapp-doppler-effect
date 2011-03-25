package ar.edu.unq.dopplereffect.bean;

import java.util.List;


/**
 * TODO: description
 */
public class Proyect {

    private String name;

    private InformationClient informationClient;

    private int consideredTime;

    private List<Skill> skils;

    private List<Empleado> assignedPeople;

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

    public List<Empleado> getAssignedPeople() {
        return assignedPeople;
    }

    public void setAssignedPeople(final List<Empleado> assignedPeople) {
        this.assignedPeople = assignedPeople;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
