package ar.edu.unq.dopplereffect.builders.employees;

import ar.edu.unq.dopplereffect.employees.EmployeeData;

public class EmployeeDataBuilder extends PersonalDataBuilder {

    protected transient String lastName = "last name";

    protected transient int dni = 12345678;

    public EmployeeDataBuilder withLastName(final String theLastName) {
        lastName = theLastName;
        return this;
    }

    public EmployeeDataBuilder withDni(final int theDni) {
        dni = theDni;
        return this;
    }

    @Override
    public EmployeeData build() {
        return new EmployeeData(dni, firstName, lastName, phoneNumber, email, address);
    }
}
