package ar.edu.unq.dopplereffect.bean;

/**
 * TODO: description
 */
public class EmployeeData extends PersonalData {

    private int dni;

    private String lastName;

    public void setDni(final int dni) {
        this.dni = dni;
    }

    public int getDni() {
        return dni;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
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
        EmployeeData other = (EmployeeData) obj;
        if (dni != other.dni) {
            return false;
        }
        return true;
    }
}
