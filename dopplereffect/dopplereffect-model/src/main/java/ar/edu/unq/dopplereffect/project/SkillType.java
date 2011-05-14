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

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
