package ar.edu.unq.dopplereffect.service.salaryspec;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.unq.dopplereffect.exceptions.ValidationException;
import ar.edu.unq.dopplereffect.service.util.SpringServiceTest;

public class SalarySpecServiceTest extends SpringServiceTest {

    @Autowired
    private SalarySpecServiceImpl service;

    @Test
    public void testHasBasePercentages() {
        SalarySpecDTO salarySpecDTO = SalarySpecHelpers.createValidSalarySpecDTOFor(service);
        // el metodo anterior guarda 0 y 100 como porcentajes, por lo
        // tanto debe poder crearse sin ningun problema
        try {
            service.newSalarySpecification(salarySpecDTO);
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testHasnt0BasePercentage() {
        SalarySpecDTO salarySpecDTO = SalarySpecHelpers.createValidSalarySpecDTOFor(service);
        salarySpecDTO.getPercentages().remove(0);
        this.salarySpecShouldNotBeValid(salarySpecDTO);
    }

    @Test
    public void testHasnt100BasePercentage() {
        SalarySpecDTO salarySpecDTO = SalarySpecHelpers.createValidSalarySpecDTOFor(service);
        salarySpecDTO.getPercentages().remove((Integer) 100);
        this.salarySpecShouldNotBeValid(salarySpecDTO);
    }

    @Test
    public void testOutOfBoundsPercentage() {
        SalarySpecDTO salarySpecDTO = SalarySpecHelpers.createValidSalarySpecDTOFor(service);
        salarySpecDTO.getPercentages().add(-1);
        this.salarySpecShouldNotBeValid(salarySpecDTO);
    }

    @Test
    public void testOutOfBoundsPercentage2() {
        SalarySpecDTO salarySpecDTO = SalarySpecHelpers.createValidSalarySpecDTOFor(service);
        salarySpecDTO.getPercentages().add(110);
        this.salarySpecShouldNotBeValid(salarySpecDTO);
    }

    @Test
    public void testMinSalaryShouldBeLessThanMaxSalary() {
        SalarySpecDTO salarySpecDTO = SalarySpecHelpers.createValidSalarySpecDTOFor(service);
        salarySpecDTO.setMinSalary(2500);
        salarySpecDTO.setMaxSalary(1000);
        this.salarySpecShouldNotBeValid(salarySpecDTO);
    }

    private void salarySpecShouldNotBeValid(final SalarySpecDTO salarySpecDTO) {
        try {
            service.newSalarySpecification(salarySpecDTO);
            Assert.fail();
        } catch (ValidationException exc) {
            exc.getMessage();
        }
    }
}
