package ar.edu.unq.dopplereffect.bean.enums;

/**
 * Tipos para los skills
 */
public class TypeSkill {

    private String name;

    public TypeSkill(final String name) {
        this.setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
