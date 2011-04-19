package ar.edu.unq.dopplereffect.salaries;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import ar.edu.unq.dopplereffect.bean.Helpers;
import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.exception.UserException;

public class SalaryConstraintTest {

    // @formatter:off
    private static final List<Integer> 
        P_0_25_50_75_100 = Arrays.asList(0, 25, 50, 75, 100),
        P_0_50_100       = Arrays.asList(0, 50, 100),
        P_0_33_66_100    = Arrays.asList(0, 33, 66, 100);
    // @formatter:on

    private static final int MIN_SALARY = 3000;

    private static final int MAX_SALARY = 4500;

    @Test
    public void testGetMinSalary() {
        SalarySpecification base = new SalaryConstraintBuilder().withMinSalary(MIN_SALARY).build();
        Assert.assertEquals("El sueldo minimo debe ser 3000", MIN_SALARY, base.getMinSalary());
    }

    @Test
    public void testGetMaxSalary() {
        SalarySpecification base = new SalaryConstraintBuilder().withMaxSalary(MAX_SALARY).build();
        Assert.assertEquals("El sueldo maximo debe ser 4500", MAX_SALARY, base.getMaxSalary());
    }

    @Test
    @SuppressWarnings("PMD")
    public void testGetSalaryForExistingPercentage() {
        // @formatter:off
        SalarySpecification base = new SalaryConstraintBuilder()
            .withMinSalary(MIN_SALARY)
            .withMaxSalary(MAX_SALARY)
            .withPercentages(P_0_33_66_100)
            .build();
        // @formatter:on
        int[] values = { 3000, 3495, 3990, 4500 };
        Helpers.assertGetSalary(base, P_0_33_66_100, values);
    }

    @Test(expected = UserException.class)
    public void testGetSalaryForNonValidPercentage() {
        // @formatter:off
        SalarySpecification base = new SalaryConstraintBuilder()
            .withMinSalary(MIN_SALARY)
            .withMaxSalary(MAX_SALARY)
            .withPercentages(P_0_33_66_100)
            .build();
        // @formatter:on
        base.getSalary(17);
        base.getSalary(28);
        base.getSalary(96);
    }

    @Test
    @SuppressWarnings("PMD")
    public void testHasPercentage() {
        // @formatter:off
        SalarySpecification base = new SalaryConstraintBuilder()
            .withMinSalary(MIN_SALARY)
            .withMaxSalary(MAX_SALARY)
            .withPercentages(P_0_33_66_100)
            .build();
        // @formatter:on
        Helpers.assertSalaryHasPercentages(base, P_0_33_66_100);
    }

    @Test
    @SuppressWarnings("PMD")
    public void testHasntPercentage() {
        // @formatter:off
        SalarySpecification base = new SalaryConstraintBuilder()
            .withMinSalary(MIN_SALARY)
            .withMaxSalary(MAX_SALARY)
            .withPercentages(P_0_33_66_100)
            .build();
        // @formatter:on
        Helpers.assertSalaryHasntPercentages(base, 10, 50, 78, 99);
    }

    @Test
    public void percentageChangeAffectsEmployee() {
        SalarySpecification base = new SalaryConstraintBuilder().withPercentages(P_0_33_66_100).build();
        Employee affected = mock(Employee.class);
        when(affected.getPercentage()).thenReturn(33).thenReturn(50);
        Set<Employee> employees = new HashSet<Employee>();
        employees.add(affected);
        base.changePecentages(P_0_50_100, employees);
        verify(affected).changeSalaryPercentage(P_0_50_100);
        Assert.assertEquals("El porcentaje del empleado debe haber cambiado de 33 a 50", 50, affected.getPercentage());
    }

    @Test
    public void precentageChangeDoesNotAffectEmployee() {
        SalarySpecification base = new SalaryConstraintBuilder().withPercentages(P_0_50_100).build();
        Employee notAffected = mock(Employee.class);
        when(notAffected.getPercentage()).thenReturn(50);
        Set<Employee> employees = new HashSet<Employee>();
        employees.add(notAffected);
        base.changePecentages(P_0_25_50_75_100, employees);
        Assert.assertEquals("El porcentaje del empleado debe permanecer en 50", 50, notAffected.getPercentage());
    }

    @Test(expected = UserException.class)
    public void percentagesChangeWithout0() {
        SalarySpecification base = new SalaryConstraintBuilder().build();
        List<Integer> newPercentages = Arrays.asList(33, 66, 100);
        base.changePecentages(newPercentages, new HashSet<Employee>());
    }

    @Test(expected = UserException.class)
    public void percentagesChangeWithout100() {
        SalarySpecification base = new SalaryConstraintBuilder().build();
        List<Integer> newPercentages = Arrays.asList(0, 25, 50, 75);
        base.changePecentages(newPercentages, new HashSet<Employee>());
    }
}