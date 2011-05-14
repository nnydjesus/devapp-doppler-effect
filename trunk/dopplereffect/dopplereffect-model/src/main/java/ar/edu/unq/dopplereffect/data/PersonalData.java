package ar.edu.unq.dopplereffect.data;

import ar.edu.unq.dopplereffect.entity.Entity;

/**
 * Representa aquellos datos que identifican y caracterizan a una persona, como
 * por ejemplo su nombre o su direccion.
 */
public class PersonalData extends Entity {
    private static final long serialVersionUID = 1L;

    /* ************************ INSTANCE VARIABLES ************************ */

    private String firstName;

    private String phoneNumber;

    private String email;

    private Address address;

    /* *************************** CONSTRUCTORS *************************** */

    public PersonalData(final String firstName, final Address address) {
        super();
        this.firstName = firstName;
        this.address = address;
    }

    public PersonalData(final String firstName, final String phoneNumber, final String email, final Address address) {
        super();
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    public PersonalData() {
        // Usado unicamente por Hibernate
        super();
    }

    /* **************************** ACCESSORS ***************************** */

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(final Address address) {
        this.address = address;
    }

    /* **************************** OPERATIONS **************************** */
}