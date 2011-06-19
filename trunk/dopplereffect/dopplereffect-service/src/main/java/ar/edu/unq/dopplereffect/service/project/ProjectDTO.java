package ar.edu.unq.dopplereffect.service.project;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.joda.time.Period;

import ar.edu.unq.dopplereffect.service.DTO;

public class ProjectDTO implements DTO {

    private static final long serialVersionUID = -8252682533584334062L;

    private String name;

    private String clientName;

    private Period timeProject;

    private Long maxEffort;

    private Long currentEffort;

    private List<SkillDTO> skills = new LinkedList<SkillDTO>();

    private List<ProjectAssignmentDTO> assignment = new ArrayList<ProjectAssignmentDTO>();

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Long getMaxEffort() {
        return maxEffort;
    }

    public void setMaxEffort(final Long maxEffort) {
        this.maxEffort = maxEffort;
    }

    public List<SkillDTO> getSkills() {
        return skills;
    }

    public void setSkills(final List<SkillDTO> skills) {
        this.skills = skills;
    }

    public void setCurrentEffort(final Long currentEffort) {
        this.currentEffort = currentEffort;
    }

    public Long getCurrentEffort() {
        return currentEffort;
    }

    public void setClientName(final String clientName) {
        this.clientName = clientName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setTimeProject(final Period timeProject) {
        this.timeProject = timeProject;
    }

    public Period getTimeProject() {
        return timeProject;
    }

    public void setAssignment(final List<ProjectAssignmentDTO> assignment) {
        this.assignment = assignment;
    }

    public List<ProjectAssignmentDTO> getAssignment() {
        return assignment;
    }

}
