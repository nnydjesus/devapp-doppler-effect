package ar.edu.unq.dopplereffect.project;

import ar.edu.unq.dopplereffect.entity.Entity;

/**
 * Representa aquellos requerimientos (en materia de conocimientos/habilidades)
 * de un proyecto.
 */
public class Skill extends Entity {
    private static final long serialVersionUID = 1L;

    /* ************************ INSTANCE VARIABLES ************************ */
    private SkillType type;

    private SkillLevel level;

    /* *************************** CONSTRUCTORS *************************** */

    public Skill() {
        super();
    }

    public Skill(final SkillType type, final SkillLevel level) {
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

    public SkillType getType() {
        return type;
    }

    public void setType(final SkillType type) {
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

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + (level == null ? 0 : level.hashCode());
        result = prime * result + (type == null ? 0 : type.hashCode());
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
        Skill other = (Skill) obj;
        if (level != other.level) {
            return false;
        }
        if (type == null) {
            if (other.type != null) {
                return false;
            }
        } else if (!type.equals(other.type)) {
            return false;
        }
        return true;
    }

}
