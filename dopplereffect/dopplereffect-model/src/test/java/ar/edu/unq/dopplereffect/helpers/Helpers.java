package ar.edu.unq.dopplereffect.helpers;

import java.util.List;

import org.junit.Assert;

import ar.edu.unq.dopplereffect.salaries.SalarySpecification;

public class Helpers {

    private Helpers() {
    }

    public static void assertSalaryHasPercentages(final SalarySpecification base, final List<Integer> percentages) {
        for (int p : percentages) {
            Assert.assertTrue(base.hasPercentage(p));
        }
    }

    public static void assertSalaryHasntPercentages(final SalarySpecification base, final int... percentages) {
        for (int p : percentages) {
            Assert.assertFalse(base.hasPercentage(p));
        }
    }

    public static void assertGetSalary(final SalarySpecification base, final List<Integer> percentages, final int[] values) {
        for (int i = 0; i < values.length; i++) {
            Assert.assertEquals(values[i], base.getSalary(percentages.get(i)));
        }
    }
}
