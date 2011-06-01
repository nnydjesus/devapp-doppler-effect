package ar.edu.unq.dopplereffect.data;

import ar.edu.unq.dopplereffect.entity.Entity;

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
        // solo usado por Hibernate
        super();
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
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + (city == null ? 0 : city.hashCode());
        result = prime * result + number;
        result = prime * result + (street == null ? 0 : street.hashCode());
        return result;
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
        Address other = (Address) obj;
        if (city == null) {
            if (other.city != null) {
                return false;
            }
        } else if (!city.equals(other.city)) {
            return false;
        }
        if (number != other.number) {
            return false;
        }
        if (street == null) {
            if (other.street != null) {
                return false;
            }
        } else if (!street.equals(other.street)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Address [street=" + street + ", number=" + number + ", city=" + city + "]";
    }
}
