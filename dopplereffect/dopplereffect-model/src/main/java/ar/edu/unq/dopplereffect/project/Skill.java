package ar.edu.unq.dopplereffect.project;

import ar.edu.unq.dopplereffect.entity.Entity;

/**
 * Representa aquellos requerimientos (en materia de conocimientos/habilidades)
 * de un proyecto.
 */
public class Skill extends Entity {
    private static final long serialVersionUID = 1L;

    /* ************************ INSTANCE VARIABLES ************************ */
    private String type;

    private SkillLevel level;

    /* *************************** CONSTRUCTORS *************************** */

    public Skill() {
        this("", null);
    }

    public Skill(final String type, final SkillLevel level) {
        super();
        this.level = level;
        this.type = type;
    }

    /* **************************** ACCESSORS ***************************** */

    public SkillLevel getLevel() {
        return level;
    }

    public void setLevel(final SkillLevel level) {
        this.level = level;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    /* **************************** OPERATIONS **************************** */

    /**
     * Retorna <code>true</code> si el skill es mejor o igual a un skill dado
     * como parametro.
     */
    public boolean betterOrEqual(final Skill skill) {
        return this.getType().equals(skill.getType()) && this.getLevel().betterOrEqual(skill.getLevel());
    }
}
