package ar.edu.unq.dopplereffect.bean;

import ar.edu.unq.dopplereffect.bean.enums.LevelSkill;
import ar.edu.unq.dopplereffect.bean.enums.TypeSkill;

/**
 */
public class Skill {
    private TypeSkill type;

    private LevelSkill level;

    public void setLevel(final LevelSkill level) {
        this.level = level;
    }

    public LevelSkill getLevel() {
        return level;
    }

    public void setType(final TypeSkill type) {
        this.type = type;
    }

    public TypeSkill getType() {
        return type;
    }

}
