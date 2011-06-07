package ar.edu.unq.dopplereffect.builders.employees;

import org.joda.time.DateTime;

import ar.edu.unq.dopplereffect.employees.CareerPlan;
import ar.edu.unq.dopplereffect.employees.CareerPlanLevel;
import ar.edu.unq.dopplereffect.employees.Employee;

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
        employee.getCareerData().setPercentage(percentage);
        return this;
    }

    public EmployeeBuilder withJoinDate(final DateTime date) {
        employee.getCareerData().setJoinDate(date);
        return this;
    }

    public EmployeeBuilder withCareerPlan(final CareerPlan plan) {
        employee.getCareerData().setCareerPlan(plan);
        return this;
    }

    public EmployeeBuilder withCareerPlanLevel(final CareerPlanLevel level) {
        employee.getCareerData().setLevel(level);
        return this;
    }

    public Employee build() {
        return employee;
    }
}
