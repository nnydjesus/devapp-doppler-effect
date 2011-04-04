package ar.edu.unq.dopplereffect.bean;

/**
 * Representa aquellos datos que identifican y caracterizan a una persona, como
 * por ejemplo su nombre o su direccion.
 */
public class PersonalData {

    private int dni;

    private String firstName;

    private String lastName;

    private String address;

    private String locality;

    private String phoneNumber;

    private String email;

    public int getDni() {
        return dni;
    }

    public void setDni(final int dni) {
        this.dni = dni;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
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

    @Override
    public int hashCode() {
        int prime = 31;
        return prime + dni;
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
        PersonalData other = (PersonalData) obj;
        if (dni != other.dni) {
            return false;
        }
        return true;
    }
}