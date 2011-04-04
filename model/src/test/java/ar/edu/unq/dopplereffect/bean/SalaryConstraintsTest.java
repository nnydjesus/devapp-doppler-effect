package ar.edu.unq.dopplereffect.bean;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import ar.edu.unq.dopplereffect.bean.enums.CareerPlan;
import ar.edu.unq.dopplereffect.bean.enums.CareerPlanLevel;
import ar.edu.unq.dopplereffect.exception.UserException;

public class SalaryConstraintsTest {

    private SalaryConstraints createConsistentSalary() {
        return new SalaryConstraints(2011, mock(CareerPlan.class), mock(CareerPlanLevel.class));
    }

    private SalaryConstraints createConsistentSalary(final int minS, final int maxS) {
        SalaryConstraints salaryConst = this.createConsistentSalary();
        salaryConst.setMinSalary(minS);
        salaryConst.setMaxSalary(maxS);
        return salaryConst;
    }

    @Test
    public void testGetSueldoMinimo() {
        SalaryConstraints base = this.createConsistentSalary();
        base.setMinSalary(3000);
        Assert.assertEquals("El sueldo minimo debe ser 3000", 3000, base.getMinSalary());
    }

    @Test
    public void testGetSueldoMaximo() {
        SalaryConstraints base = this.createConsistentSalary();
        base.setMaxSalary(4500);
        Assert.assertEquals("El sueldo maximo debe ser 4500", 4500, base.getMaxSalary());
    }

    @Test
    public void testGetSalaryForExistingPercentage() {
        SalaryConstraints base = this.createConsistentSalary(3000, 4500);
        base.setPercentages(new int[] { 0, 33, 66, 100 });
        Assert.assertEquals("El sueldo para 0% debe ser 3000", 3000, base.getSalary(0));
        Assert.assertEquals("El sueldo para 33% debe ser 3495", 3495, base.getSalary(33));
        Assert.assertEquals("El sueldo para 66% debe ser 3990", 3990, base.getSalary(66));
        Assert.assertEquals("El sueldo para 100% debe ser 4500", 4500, base.getSalary(100));
    }

    @Test(expected = UserException.class)
    public void testGetSalaryForNonValidPercentage() {
        SalaryConstraints base = this.createConsistentSalary(3000, 4500);
        base.setPercentages(new int[] { 0, 33, 66, 100 });
        base.getSalary(17);
        base.getSalary(28);
        base.getSalary(96);
    }

    @Test
    public void testHasPercentage() {
        SalaryConstraints base = this.createConsistentSalary(3000, 4500);
        base.setPercentages(new int[] { 0, 33, 66, 100 });
        Assert.assertTrue("0% debe ser valido para la banda [0, 33, 66, 100]", base.hasPercentage(0));
        Assert.assertTrue("33% debe ser valido para la banda [0, 33, 66, 100]", base.hasPercentage(33));
        Assert.assertTrue("66% debe ser valido para la banda [0, 33, 66, 100]", base.hasPercentage(66));
        Assert.assertTrue("100% debe ser valido para la banda [0, 33, 66, 100]", base.hasPercentage(100));
    }

    @Test
    public void testHasntPercentage() {
        SalaryConstraints base = this.createConsistentSalary(3000, 4500);
        base.setPercentages(new int[] { 0, 33, 66, 100 });
        Assert.assertFalse("10% NO debe ser valido para la banda [0, 33, 66, 100]", base.hasPercentage(10));
        Assert.assertFalse("50% NO debe ser valido para la banda [0, 33, 66, 100]", base.hasPercentage(50));
        Assert.assertFalse("78% NO debe ser valido para la banda [0, 33, 66, 100]", base.hasPercentage(78));
        Assert.assertFalse("99% NO debe ser valido para la banda [0, 33, 66, 100]", base.hasPercentage(99));
    }

    @Test
    public void percentageChangeAffectsEmployee() {
        SalaryConstraints base = this.createConsistentSalary();
        base.setPercentages(new int[] { 0, 33, 66, 100 });
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
        SalaryConstraints base = this.createConsistentSalary();
        base.setPercentages(new int[] { 0, 50, 100 });
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
        int[] newPercentages = { 33, 66, 100 };
        this.createConsistentSalary().changePecentages(newPercentages, new HashSet<Employee>());
    }

    @Test(expected = UserException.class)
    public void percentagesChangeWithout100() {
        int[] newPercentages = { 0, 25, 50, 75 };
        this.createConsistentSalary().changePecentages(newPercentages, new HashSet<Employee>());
    }
}