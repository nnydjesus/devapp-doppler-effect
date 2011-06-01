package ar.edu.unq.dopplereffect.persistence.repositories;

import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.unq.dopplereffect.employees.CareerPlan;
import ar.edu.unq.dopplereffect.employees.CareerPlanLevel;
import ar.edu.unq.dopplereffect.persistence.util.SpringPersistenceTest;
import ar.edu.unq.dopplereffect.salaries.SalarySpecification;

public class SalarySpecificationPersistenceTest extends SpringPersistenceTest {

    @Autowired
    private SalarySpecificationRepositoryImpl salarySpecRepo;

    @Test
    public void testSaveAndRetrieve() {
        // CREATE
        CareerPlan plan = CareerPlan.DESIGNER;
        CareerPlanLevel level = new CareerPlanLevel("Junior");
        SalarySpecification example = new SalarySpecification(2011, plan, level, 0, 25, 50, 75, 100);
        // SAVE
        salarySpecRepo.save(example);
        // GET
        SalarySpecification getSSpec = salarySpecRepo.getByPlanAndLevel(plan, level);
        // ASSERT
        Assert.assertEquals(2011, getSSpec.getYear());
        Assert.assertEquals(plan, getSSpec.getPlan());
        Assert.assertEquals(level, getSSpec.getLevel());
        List<Integer> expPercentages = Arrays.asList(0, 25, 50, 75, 100);
        Assert.assertEquals(expPercentages, getSSpec.getPercentages());
    }
}
