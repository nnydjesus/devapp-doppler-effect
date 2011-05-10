package ar.edu.unq.dopplereffect.employees;

import ar.edu.unq.dopplereffect.data.Address;
import ar.edu.unq.dopplereffect.data.PersonalData;

/**
 * Datos personales propios del empleado.
 */
public class EmployeeData extends PersonalData {

    /* ************************ INSTANCE VARIABLES ************************ */

    private int dni;

    private String lastName;

    /* *************************** CONSTRUCTORS *************************** */

    public EmployeeData(final int dni, final String firstName, final String lastName, final Address address) {
        super(firstName, address);
        this.dni = dni;
        this.lastName = lastName;
    }

    public EmployeeData(final int dni, final String firstName, final String lastName, final String phoneNumber,
            final String email, final Address address) {
        super(firstName, phoneNumber, email, address);
        this.dni = dni;
        this.lastName = lastName;
    }

    public EmployeeData() {
        // solo utilizado por Hibernate
        super();
    }

    /* **************************** ACCESSORS ***************************** */

    public int getDni() {
        return dni;
    }

    public void setDni(final int dni) {
        this.dni = dni;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    /* **************************** OPERATIONS **************************** */

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
