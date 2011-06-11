package ar.edu.unq.dopplereffect.service.project;

import java.util.LinkedList;
import java.util.List;

import ar.edu.unq.dopplereffect.service.DTO;

public class ProjectDTO implements DTO {

    private static final long serialVersionUID = -8252682533584334062L;

    private String name;

    private Long maxEffort;

    private List<SkillDTO> skills = new LinkedList<SkillDTO>();

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

}
