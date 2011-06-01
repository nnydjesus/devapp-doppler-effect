package ar.edu.unq.dopplereffect.project;

/**
 * Niveles de los Skills.
 * 
 * Invariante de representacion: deben estar ordenados del mas principiante al
 * mas experto.
 * 
 */
public enum SkillLevel {

    BEGINNER, MEDIUM, EXPERT;

    /**
     * Retorna <code>true</code> si el nivel es mejor o igual a uno dado como
     * parametro, <code>false</code> en caso contrario.
     */
    public boolean betterOrEqual(final SkillLevel level) {
        return this.ordinal() >= level.ordinal();
    }

    /**
     * Retorna un porcentaje que indica cuanto satisface el nivel a otro nivel
     * pasado como parametro.
     */
    public int satisfactionPercentage(final SkillLevel level) {
        return (this.ordinal() + 1) * 100 / (level.ordinal() + 1);
    }
}
