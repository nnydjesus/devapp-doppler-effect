package ar.edu.unq.dopplereffect.employees;

import static ar.edu.unq.dopplereffect.employees.CareerPlan.TESTER;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import ar.edu.unq.dopplereffect.exception.UserException;
import ar.edu.unq.dopplereffect.salaries.SalarySpecification;

public class EmployeesControllerTest {

    private static final CareerPlanLevel JUNIOR = new CareerPlanLevel("Junior");

    private static final CareerPlanLevel SENIOR = new CareerPlanLevel("Senior");

    @Test(expected = UserException.class)
    public void testSearchForNonExistingSalarySpecification() {
        EmployeesController controller = new EmployeesController();
        controller.searchSalarySpecification(TESTER, JUNIOR);
    }

    @Test
    public void testSearchSalarySpecification() {
        EmployeesController controller = new EmployeesController();
        SalarySpecification spec = this.mockSalarySpecification();
        controller.addSalarySpecification(spec);
        SalarySpecification expected = controller.searchSalarySpecification(TESTER, JUNIOR);
        assertEquals("la especificacion del salario no es la correcta", expected, spec);
    }

    @Test
    public void testGetSalary() { // NOPMD
        EmployeesController controller = new EmployeesController();
        SalarySpecification spec = this.mockSalarySpecification();
        controller.addSalarySpecification(spec);
        Employee empl = mock(Employee.class);
        when(empl.getPercentage()).thenReturn(50);
        when(empl.getCareerPlan()).thenReturn(TESTER);
        when(empl.getLevel()).thenReturn(JUNIOR);
        controller.getSalary(empl);
        verify(spec).getSalary(50);
    }

    @Test
    public void testChangePercentages() { // NOPMD
        EmployeesController controller = new EmployeesController();
        SalarySpecification spec = this.mockSalarySpecification();
        controller.addSalarySpecification(spec);
        List<Integer> percentages = new LinkedList<Integer>();
        controller.changePercentages(TESTER, JUNIOR, percentages);
        verify(spec).changePercentages(percentages, new HashSet<Employee>());
    }

    @Test
    public void testNewLevelInFirstPlace() {
        EmployeesController controller = new EmployeesController();
        CareerPlanLevel newLevel = new CareerPlanLevel("Pasante");
        controller.newLevel(newLevel);
        assertEquals("el nivel agregado deberia estar en primer lugar", newLevel, controller.getLevels().get(0));
    }

    @Test
    public void testNewLevelInTheEnd() {
        EmployeesController controller = new EmployeesController();
        CareerPlanLevel newLevel = new CareerPlanLevel("Capo");
        List<CareerPlanLevel> levels = new LinkedList<CareerPlanLevel>();
        levels.add(JUNIOR);
        levels.add(SENIOR);
        controller.changeLevelsOrder(levels);
        controller.newLevel(newLevel, SENIOR);
        assertEquals("el nuevo nivel debe estar al final", newLevel, controller.getLevels().get(2));
    }

    @Test
    public void testNewLevelInTheMiddle() {
        EmployeesController controller = new EmployeesController();
        CareerPlanLevel newLevel = new CareerPlanLevel("SemiSenior");
        List<CareerPlanLevel> levels = new LinkedList<CareerPlanLevel>();
        levels.add(JUNIOR);
        levels.add(SENIOR);
        controller.changeLevelsOrder(levels);
        controller.newLevel(newLevel, JUNIOR);
        assertEquals("el nuevo nivel debe estar en segundo lugar", newLevel, controller.getLevels().get(1));
    }

    private SalarySpecification mockSalarySpecification() {
        SalarySpecification spec = mock(SalarySpecification.class);
        when(spec.getPlan()).thenReturn(TESTER);
        when(spec.getLevel()).thenReturn(JUNIOR);
        return spec;
    }
}
