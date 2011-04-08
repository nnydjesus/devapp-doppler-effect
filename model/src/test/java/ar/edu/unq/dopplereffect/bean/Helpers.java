package ar.edu.unq.dopplereffect.bean;

import org.junit.Assert;

public class Helpers {

    private Helpers() {
    }

    public static void assertSalaryHasPercentages(final SalaryConstraints base, final int... percentages) {
        for (int p : percentages) {
            Assert.assertTrue(base.hasPercentage(p));
        }
    }

    public static void assertSalaryHasntPercentages(final SalaryConstraints base, final int... percentages) {
        for (int p : percentages) {
            Assert.assertFalse(base.hasPercentage(p));
        }
    }

    public static void assertGetSalary(final SalaryConstraints base, final int[] percentages, final int[] values) {
        for (int i = 0; i < values.length; i++) {
            Assert.assertEquals(values[i], base.getSalary(percentages[i]));
        }
    }
}
