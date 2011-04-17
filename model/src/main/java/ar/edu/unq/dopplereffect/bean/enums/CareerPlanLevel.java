package ar.edu.unq.dopplereffect.bean.enums;

/**
 * Representa el nivel (conocimientos y experiencia) que tiene un empleado. Por
 * ejemplo: Junior, Senior, etc.
 */
public class CareerPlanLevel {

    private String name;

    private CareerPlanLevel previous;

    private CareerPlanLevel next;

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
}
