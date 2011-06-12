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

    @Override
    public String toString() {
        return name + " - " + level;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + (level == null ? 0 : level.hashCode());
        result = prime * result + (name == null ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        SkillDTO other = (SkillDTO) obj;
        if (level != other.level) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }

}
