package ar.edu.unq.dopplereffect.data;

import ar.edu.unq.dopplereffect.entity.Entity;

/**
 * Representan direcciones, las cuales tienen un nombre de calle, una altura y
 * una ciudad.
 */
public class Address extends Entity {

    private static final long serialVersionUID = 1L;

    /* ************************ INSTANCE VARIABLES ************************ */

    private String street;

    private int number;

    private String city;

    /* *************************** CONSTRUCTORS *************************** */

    public Address(final String street, final int number, final String city) {
        super();
        this.street = street;
        this.number = number;
        this.city = city;
    }

    public Address() {
        super(); // solo usado por Hibernate
    }

    /* **************************** ACCESSORS ***************************** */

    public String getStreet() {
        return street;
    }

    public void setStreet(final String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(final int number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    /* ****************** EQUALS, HASHCODE, TOSTRING ********************** */

    @Override
    public String toString() {
        return this.getStreet() + ", " + this.getNumber() + ", " + this.getCity();
    }
}
