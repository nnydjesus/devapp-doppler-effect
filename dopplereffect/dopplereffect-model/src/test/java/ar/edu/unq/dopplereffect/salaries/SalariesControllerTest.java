package ar.edu.unq.dopplereffect.salaries;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.Mockito;

import ar.edu.unq.dopplereffect.employees.CareerPlan;
import ar.edu.unq.dopplereffect.employees.CareerPlanLevel;
import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.exception.UserException;

public class SalariesControllerTest {

    @Test(expected = UserException.class)
    public void testSearchForNonExistingSalarySpecification() {
        SalariesController controller = new SalariesController();
        controller.searchSalarySpecification(CareerPlan.TESTER, new CareerPlanLevel("Junior"));
    }

    @Test
    public void testSearchSalarySpecification() {
        SalariesController controller = new SalariesController();
        CareerPlanLevel juniorLevel = new CareerPlanLevel("Junior");
        SalarySpecification spec = Mockito.mock(SalarySpecification.class);
        Mockito.when(spec.getPlan()).thenReturn(CareerPlan.TESTER);
        Mockito.when(spec.getLevel()).thenReturn(juniorLevel);
        controller.addSalarySpecification(spec);
        SalarySpecification expected = controller.searchSalarySpecification(CareerPlan.TESTER, juniorLevel);
        Assert.assertEquals("", expected, spec);
    }

    @Test
    @SuppressWarnings("PMD")
    public void testGetSalary() {
        SalariesController controller = new SalariesController();
        SalarySpecification spec = Mockito.mock(SalarySpecification.class);
        CareerPlanLevel juniorLevel = new CareerPlanLevel("Junior");
        Mockito.when(spec.getPlan()).thenReturn(CareerPlan.TESTER);
        Mockito.when(spec.getLevel()).thenReturn(juniorLevel);
        controller.addSalarySpecification(spec);
        Employee empl = Mockito.mock(Employee.class);
        Mockito.when(empl.getPercentage()).thenReturn(50);
        Mockito.when(empl.getCareerPlan()).thenReturn(CareerPlan.TESTER);
        Mockito.when(empl.getLevel()).thenReturn(juniorLevel);
        controller.getSalary(empl);
        Mockito.verify(spec).getSalary(50);
    }
}
