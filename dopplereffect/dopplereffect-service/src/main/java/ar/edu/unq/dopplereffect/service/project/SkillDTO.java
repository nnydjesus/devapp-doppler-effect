package ar.edu.unq.dopplereffect.service.project;

import ar.edu.unq.dopplereffect.projects.SkillLevel;
import ar.edu.unq.dopplereffect.service.DTO;

public class SkillDTO implements DTO {

    private static final long serialVersionUID = -4386848285350051780L;

    private String name;

    private SkillLevel level;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public SkillLevel getLevel() {
        return level;
    }

    public void setLevel(final SkillLevel level) {
        this.level = level;
    }

}
