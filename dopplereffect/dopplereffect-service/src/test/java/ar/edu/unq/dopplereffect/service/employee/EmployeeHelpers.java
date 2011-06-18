package ar.edu.unq.dopplereffect.service.employee;

import java.util.Date;

import ar.edu.unq.dopplereffect.employees.CareerPlan;

public class EmployeeHelpers {

    private EmployeeHelpers() {
    }

    public static final EmployeeDTO getValidEmployeeDTO() {
        EmployeeDTO result = new EmployeeDTO();
        result.setFirstName("Juan");
        result.setLastName("Perez");
        result.setDni(12345678);
        result.setAddressStreet("Av. XXX");
        result.setAddressNumber(123);
        result.setAddressCity("Berazategui");
        result.setCareerPlan(CareerPlan.TESTER);
        result.setEmail("a@b.com");
        result.setPercentage(0);
        result.setPhoneNumber("11-4321-8765");
        result.setCareerPlanLevel("X");
        result.setJoinDate(new Date());
        return result;
    }
}
