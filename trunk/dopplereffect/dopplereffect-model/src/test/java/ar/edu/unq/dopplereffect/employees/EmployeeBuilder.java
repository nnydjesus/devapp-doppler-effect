package ar.edu.unq.dopplereffect.employees;

import org.joda.time.DateTime;

public class EmployeeBuilder {

    private transient Employee employee;

    public EmployeeBuilder() {
        employee = new Employee();
    }

    public EmployeeBuilder withFirstName(final String firstName) {
        employee.setFirstName(firstName);
        return this;
    }

    public EmployeeBuilder withLastName(final String lastName) {
        employee.setLastName(lastName);
        return this;
    }

    public EmployeeBuilder withDNI(final int dni) {
        employee.setDni(dni);
        return this;
    }

    public EmployeeBuilder withPercentage(final int percentage) {
        employee.setPercentage(percentage);
        return this;
    }

    public EmployeeBuilder withJoinDate(final DateTime date) {
        employee.setJoinDate(date);
        return this;
    }

    public Employee build() {
        return employee;
    }
}
