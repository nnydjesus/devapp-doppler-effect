package ar.edu.unq.dopplereffect.bean;

/**
 * Representa aquellos datos que identifican y caracterizan a una persona, como
 * por ejemplo su nombre o su direccion.
 */
public class PersonalData {

    /* ************************ INSTANCE VARIABLES ************************ */

    private String firstName;

    private String address;

    private String locality;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(final String locality) {
        this.locality = locality;
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

}