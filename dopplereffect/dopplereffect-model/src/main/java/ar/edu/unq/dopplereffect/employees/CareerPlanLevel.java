package ar.edu.unq.dopplereffect.employees;

import java.io.Serializable;

import ar.edu.unq.dopplereffect.entity.Entity;

/**
 * Representa el nivel (conocimientos y experiencia) que tiene un empleado, el
 * cual se aplica al plan de carrera que posean. Por ejemplo: Junior, Senior,
 * etc.
 */
public class CareerPlanLevel extends Entity implements Serializable {

    private static final long serialVersionUID = -7028720655092866753L;

    /* ************************ INSTANCE VARIABLES ************************ */

    /**
     * El nombre que se le otorga al nivel de plan de carrera. Por ejemplo,
     * "Junior".
     */
    private String name;

    /**
     * El nivel de plan de carrera previo. <code>null</code> si no posee previo,
     * por ser el nivel mas basico (el primero).
     */
    private CareerPlanLevel previous;

    /**
     * El nivel de plan de carrera siguiente. <code>null</code> si no posee
     * siguiente, por ser el nivel mas avanzado (el ultimo).
     */
    private CareerPlanLevel next;

    /* *************************** CONSTRUCTORS *************************** */

    public CareerPlanLevel(final String name) {
        this(name, null, null);
    }

    public CareerPlanLevel(final String name, final CareerPlanLevel previous, final CareerPlanLevel next) {
        super();
        this.name = name;
        this.setPrevious(previous);
        this.setNext(next);
    }

    public CareerPlanLevel() {
        super(); // Usado solo por Hibernate
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

    /**
     * Asigna el nivel previo. Es reciproco, con lo cual tambien asigna el
     * siguiente al nivel recibido como parametro.
     */
    public void setPrevious(final CareerPlanLevel previous) {
        this.setNewPrevious(previous);
        if (this.getPrevious() != null) {
            previous.setNewNext(this);
        }
    }

    public CareerPlanLevel getNext() {
        return next;
    }

    /**
     * Asigna el nivel siguiente. Es reciproco, con lo cual tambien asigna el
     * previo al nivel recibido como parametro.
     */
    public void setNext(final CareerPlanLevel next) {
        this.setNewNext(next);
        if (this.getNext() != null) {
            next.setNewPrevious(this);
        }
    }

    /* **************************** OPERATIONS **************************** */

    /* ************************* PRIVATE METHODS ************************** */

    private void setNewPrevious(final CareerPlanLevel newPrev) {
        previous = newPrev;
    }

    private void setNewNext(final CareerPlanLevel newNext) {
        next = newNext;
    }

    /* ****************** EQUALS, HASHCODE, TOSTRING ********************** */

    @Override
    public int hashCode() {
        int prime = 31;
        return prime + (name == null ? 0 : name.hashCode());
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        CareerPlanLevel other = (CareerPlanLevel) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
