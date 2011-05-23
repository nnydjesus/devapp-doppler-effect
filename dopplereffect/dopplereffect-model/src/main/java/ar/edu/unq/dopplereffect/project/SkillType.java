package ar.edu.unq.dopplereffect.project;

import ar.edu.unq.dopplereffect.entity.Entity;

/**
 * Tipos para los skills
 */
public class SkillType extends Entity {

    private static final long serialVersionUID = 1L;

    private String name;

    public SkillType(final String name) {
        super();
        this.setName(name);
    }

    public SkillType() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        return prime * result + (name == null ? 0 : name.hashCode());
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
        SkillType other = (SkillType) obj;
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
