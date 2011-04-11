package ar.edu.unq.dopplereffect.bean;

import ar.edu.unq.dopplereffect.bean.enums.LevelSkill;
import ar.edu.unq.dopplereffect.bean.enums.TypeSkill;

/**
 */
public class Skill {

    /* ************************ INSTANCE VARIABLES ************************ */

    private TypeSkill type;

    private LevelSkill level;

    /* *************************** CONSTRUCTORS *************************** */

    /* **************************** ACCESSORS ***************************** */

    public LevelSkill getLevel() {
        return level;
    }

    public void setLevel(final LevelSkill level) {
        this.level = level;
    }

    public TypeSkill getType() {
        return type;
    }

    public void setType(final TypeSkill type) {
        this.type = type;
    }

}
