package ar.edu.unq.dopplereffect.service.employee;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.unq.dopplereffect.exceptions.ValidationException;
import ar.edu.unq.dopplereffect.service.util.SpringServiceTest;

public class EmployeeServiceTest extends SpringServiceTest {

    @Autowired
    private EmployeeServiceImpl service;

    /*---- TESTS DE VALIDACIONES ----*/

    @Test
    public void testNewEmployeeInvalidWithBlankFirstNameOrLastName() {
        EmployeeDTO employee = EmployeeHelpers.getValidEmployeeDTO();
        employee.setFirstName("");
        try {
            service.newEmployee(employee);
            Assert.fail();
        } catch (ValidationException exc) {
            exc.getMessage();
        }
        employee.setFirstName("Pepe");
        employee.setLastName("  ");
        try {
            service.newEmployee(employee);
            Assert.fail();
        } catch (ValidationException exc) {
            exc.getMessage();
        }
    }

    @Test
    public void testNewEmployeeInvalidWithNullFirstNameOrLastName() {
        EmployeeDTO employee = EmployeeHelpers.getValidEmployeeDTO();
        employee.setFirstName(null);
        try {
            service.newEmployee(employee);
            Assert.fail();
        } catch (ValidationException exc) {
            exc.getMessage();
        }
        employee.setFirstName("Pepe");
        employee.setLastName(null);
        try {
            service.newEmployee(employee);
            Assert.fail();
        } catch (ValidationException exc) {
            exc.getMessage();
        }
    }

    @Test
    public void testValidateEmployeeWrongMail() {
        EmployeeDTO employee = EmployeeHelpers.getValidEmployeeDTO();
        employee.setEmail("");
        try {
            service.newEmployee(employee);
            Assert.fail();
        } catch (ValidationException exc) {
            exc.getMessage();
        }
    }

    @Test
    public void testValidateEmployeeRightMail() {
        EmployeeDTO employee = EmployeeHelpers.getValidEmployeeDTO();
        try {
            service.newEmployee(employee);
        } catch (ValidationException exc) {
            Assert.fail(exc.getMessage());
        }
    }
}
