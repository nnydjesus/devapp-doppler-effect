package ar.edu.unq.dopplereffect.bean;

import ar.edu.unq.dopplereffect.bean.enums.Level;
import ar.edu.unq.dopplereffect.bean.enums.Type;

/**
 * TODO: description
 */
public class Skill {
    private Type type;

    private Level level;

    public void setLevel(Level level) {
        this.level = level;
    }

    public Level getLevel() {
        return level;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

}
