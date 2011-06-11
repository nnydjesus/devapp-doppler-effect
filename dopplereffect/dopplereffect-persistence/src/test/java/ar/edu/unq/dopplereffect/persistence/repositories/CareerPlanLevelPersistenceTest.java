package ar.edu.unq.dopplereffect.persistence.repositories;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.unq.dopplereffect.employees.CareerPlanLevel;
import ar.edu.unq.dopplereffect.persistence.employee.CareerPlanLevelRepositoryImpl;
import ar.edu.unq.dopplereffect.persistence.util.SpringPersistenceTest;

public class CareerPlanLevelPersistenceTest extends SpringPersistenceTest {

    @Autowired
    private CareerPlanLevelRepositoryImpl careerPlanLevelRepo;

    @Test
    public void testSaveAndRetrieve() {
        // CREATE
        CareerPlanLevel junior = new CareerPlanLevel("Junior");
        CareerPlanLevel semiSenior = new CareerPlanLevel("SemiSenior");
        CareerPlanLevel senior = new CareerPlanLevel("Senior");
        // SET RELATIONSHIPS
        junior.setNext(semiSenior);
        semiSenior.setNext(senior);
        // SAVE
        careerPlanLevelRepo.save(junior); // el resto se guarda por cascade
        // GET
        CareerPlanLevel getJunior = careerPlanLevelRepo.getByName("Junior");
        CareerPlanLevel getSemiSenior = careerPlanLevelRepo.getByName("SemiSenior");
        CareerPlanLevel getSenior = careerPlanLevelRepo.getByName("Senior");
        // ASSERT
        Assert.assertNull(getJunior.getPrevious());
        Assert.assertNull(getSenior.getNext());
        Assert.assertEquals("SemiSenior", getJunior.getNext().getName());
        Assert.assertEquals("Senior", getSemiSenior.getNext().getName());
        Assert.assertEquals("Junior", getSemiSenior.getPrevious().getName());
        Assert.assertEquals("SemiSenior", getSenior.getPrevious().getName());
    }
}
