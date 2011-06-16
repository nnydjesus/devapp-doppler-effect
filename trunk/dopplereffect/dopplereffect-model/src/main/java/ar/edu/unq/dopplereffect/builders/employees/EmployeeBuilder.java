package ar.edu.unq.dopplereffect.builders.employees;

import org.joda.time.DateTime;

import ar.edu.unq.dopplereffect.builders.Builder;
import ar.edu.unq.dopplereffect.employees.CareerData;
import ar.edu.unq.dopplereffect.employees.CareerPlan;
import ar.edu.unq.dopplereffect.employees.CareerPlanLevel;
import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.employees.EmployeeData;

public class EmployeeBuilder implements Builder<Employee> {

    protected transient EmployeeData employeeData = new EmployeeDataBuilder().build();

    protected transient CareerData careerData = new CareerDataBuilder().build();

    public EmployeeBuilder withFirstName(final String firstName) {
        employeeData.setFirstName(firstName);
        return this;
    }

    public EmployeeBuilder withLastName(final String lastName) {
        employeeData.setLastName(lastName);
        return this;
    }

    public EmployeeBuilder withDNI(final int dni) {
        employeeData.setDni(dni);
        return this;
    }

    public EmployeeBuilder withPercentage(final int percentage) {
        careerData.setPercentage(percentage);
        return this;
    }

    public EmployeeBuilder withJoinDate(final DateTime date) {
        careerData.setJoinDate(date);
        return this;
    }

    public EmployeeBuilder withCareerPlan(final CareerPlan plan) {
        careerData.setCareerPlan(plan);
        return this;
    }

    public EmployeeBuilder withCareerPlanLevel(final CareerPlanLevel level) {
        careerData.setLevel(level);
        return this;
    }

    @Override
    public Employee build() {
        return new Employee(employeeData, careerData);
    }
}
