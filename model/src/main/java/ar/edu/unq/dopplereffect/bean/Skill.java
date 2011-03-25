package ar.edu.unq.dopplereffect.bean;

import ar.edu.unq.dopplereffect.bean.enums.Level;
import ar.edu.unq.dopplereffect.bean.enums.Type;

/**
 */
public class Skill {
    private Type type;

    private Level level;

    public void setLevel(final Level level) {
        this.level = level;
    }

    public Level getLevel() {
        return level;
    }

    public void setType(final Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

}
