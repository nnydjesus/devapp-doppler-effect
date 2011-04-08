package ar.edu.unq.dopplereffect.bean;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import ar.edu.unq.dopplereffect.exception.UserException;

public class SalaryConstraintsTest {

    private static final int[] P_0_33_66_100 = { 0, 33, 66, 100 };

    private static final int MIN_SALARY = 3000;

    private static final int MAX_SALARY = 4500;

    @Test
    public void testGetMinSalary() {
        SalaryConstraints base = new SalaryConstraintsBuilder().withMinSalary(MIN_SALARY).build();
        Assert.assertEquals("El sueldo minimo debe ser 3000", MIN_SALARY, base.getMinSalary());
    }

    @Test
    public void testGetMaxSalary() {
        SalaryConstraints base = new SalaryConstraintsBuilder().withMaxSalary(MAX_SALARY).build();
        Assert.assertEquals("El sueldo maximo debe ser 4500", MAX_SALARY, base.getMaxSalary());
    }

    @Test
    @SuppressWarnings("PMD")
    public void testGetSalaryForExistingPercentage() {
        // @formatter:off
        SalaryConstraints base = new SalaryConstraintsBuilder()
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
        SalaryConstraints base = new SalaryConstraintsBuilder()
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
        SalaryConstraints base = new SalaryConstraintsBuilder()
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
        SalaryConstraints base = new SalaryConstraintsBuilder()
            .withMinSalary(MIN_SALARY)
            .withMaxSalary(MAX_SALARY)
            .withPercentages(P_0_33_66_100)
            .build();
        // @formatter:on
        Helpers.assertSalaryHasntPercentages(base, 10, 50, 78, 99);
    }

    @Test
    public void percentageChangeAffectsEmployee() {
        SalaryConstraints base = new SalaryConstraintsBuilder().withPercentages(P_0_33_66_100).build();
        Employee affected = mock(Employee.class);
        when(affected.getPercentage()).thenReturn(33).thenReturn(50);
        Set<Employee> employees = new HashSet<Employee>();
        employees.add(affected);
        int[] newPercentages = { 0, 50, 100 };
        base.changePecentages(newPercentages, employees);
        verify(affected).changeSalaryPercentage(newPercentages);
        Assert.assertEquals("El porcentaje del empleado debe haber cambiado de 33 a 50", 50, affected.getPercentage());
    }

    @Test
    public void precentageChangeDoesNotAffectEmployee() {
        SalaryConstraints base = new SalaryConstraintsBuilder().withPercentages(new int[] { 0, 50, 100 }).build();
        Employee notAffected = mock(Employee.class);
        when(notAffected.getPercentage()).thenReturn(50);
        Set<Employee> employees = new HashSet<Employee>();
        employees.add(notAffected);
        int[] newPercentages = { 0, 25, 50, 75, 100 };
        base.changePecentages(newPercentages, employees);
        Assert.assertEquals("El porcentaje del empleado debe permanecer en 50", 50, notAffected.getPercentage());
    }

    @Test(expected = UserException.class)
    public void percentagesChangeWithout0() {
        SalaryConstraints base = new SalaryConstraintsBuilder().build();
        int[] newPercentages = { 33, 66, 100 };
        base.changePecentages(newPercentages, new HashSet<Employee>());
    }

    @Test(expected = UserException.class)
    public void percentagesChangeWithout100() {
        SalaryConstraints base = new SalaryConstraintsBuilder().build();
        int[] newPercentages = { 0, 25, 50, 75 };
        base.changePecentages(newPercentages, new HashSet<Employee>());
    }
}