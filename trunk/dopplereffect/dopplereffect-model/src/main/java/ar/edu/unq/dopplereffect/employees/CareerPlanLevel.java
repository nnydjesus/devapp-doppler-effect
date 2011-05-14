package ar.edu.unq.dopplereffect.employees;

import java.io.Serializable;

/**
 * Representa el nivel (conocimientos y experiencia) que tiene un empleado. Por
 * ejemplo: Junior, Senior, etc.
 */
public class CareerPlanLevel implements Serializable {
    private static final long serialVersionUID = 1L;

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
