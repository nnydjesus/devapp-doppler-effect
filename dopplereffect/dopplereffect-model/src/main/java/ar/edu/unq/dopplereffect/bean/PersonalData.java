package ar.edu.unq.dopplereffect.bean;

/**
 * Representa aquellos datos que identifican y caracterizan a una persona, como
 * por ejemplo su nombre o su direccion.
 */
public class PersonalData {

    /* ************************ INSTANCE VARIABLES ************************ */

    private String firstName;

    private Address address;

    private String phoneNumber;

    private String email;

    /* *************************** CONSTRUCTORS *************************** */

    /* **************************** ACCESSORS ***************************** */

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(final Address address) {
        this.address = address;
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

    /* **************************** OPERATIONS **************************** */
}