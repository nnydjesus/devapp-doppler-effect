package ar.edu.unq.dopplereffect.employees;

/**
 * Representa el nivel (conocimientos y experiencia) que tiene un empleado. Por
 * ejemplo: Junior, Senior, etc.
 */
public class CareerPlanLevel {

    /* ************************ INSTANCE VARIABLES ************************ */

    private String name;

    /* *************************** CONSTRUCTORS *************************** */

    public CareerPlanLevel(final String name) {
        this.name = name;
    }

    /* **************************** ACCESSORS ***************************** */

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    /* **************************** OPERATIONS **************************** */
}
