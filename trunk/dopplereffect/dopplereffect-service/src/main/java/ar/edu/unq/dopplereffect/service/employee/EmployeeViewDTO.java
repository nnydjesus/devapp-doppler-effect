package ar.edu.unq.dopplereffect.service.employee;

import ar.edu.unq.dopplereffect.service.DTO;

public class EmployeeViewDTO implements DTO {

    private static final long serialVersionUID = -2377606763449769912L;

    private String firstName;

    private String lastName;

    private int dni;

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

    public int getDni() {
        return dni;
    }

    public void setDni(final int dni) {
        this.dni = dni;
    }

    @Override
    public String toString() {
        return this.getFirstName() + " " + this.getLastName();
    }

}
