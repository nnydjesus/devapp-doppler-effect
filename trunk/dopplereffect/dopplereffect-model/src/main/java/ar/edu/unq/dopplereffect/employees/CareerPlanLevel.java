package ar.edu.unq.dopplereffect.employees;

/**
 * Representa el nivel (conocimientos y experiencia) que tiene un empleado. Por
 * ejemplo: Junior, Senior, etc.
 */
public class CareerPlanLevel {

    /* ************************ INSTANCE VARIABLES ************************ */

    private String name;

    private CareerPlanLevel previous;

    private CareerPlanLevel next;

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

    public CareerPlanLevel getPrevious() {
        return previous;
    }

    public void setPrevious(final CareerPlanLevel previous) {
        this.previous = previous;
    }

    public CareerPlanLevel getNext() {
        return next;
    }

    public void setNext(final CareerPlanLevel next) {
        this.next = next;
    }

    /* **************************** OPERATIONS **************************** */
}