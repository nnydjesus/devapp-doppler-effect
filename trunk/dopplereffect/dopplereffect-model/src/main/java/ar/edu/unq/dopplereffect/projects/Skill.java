package ar.edu.unq.dopplereffect.projects;

/**
 * Representa aquellos requerimientos (en materia de conocimientos/habilidades)
 * de un proyecto.
 */
public class Skill {

    /* ************************ INSTANCE VARIABLES ************************ */

    private SkillType type;

    private SkillLevel level;

    /* *************************** CONSTRUCTORS *************************** */

    public Skill(final SkillType type, final SkillLevel level) {
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
