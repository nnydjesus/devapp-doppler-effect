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
}
