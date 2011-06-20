package ar.edu.unq.dopplereffect.persistence;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;

import ar.edu.unq.dopplereffect.builders.Builder;
import ar.edu.unq.dopplereffect.builders.employees.AddressBuilder;
import ar.edu.unq.dopplereffect.builders.employees.CareerDataBuilder;
import ar.edu.unq.dopplereffect.builders.employees.EmployeeBuilder;
import ar.edu.unq.dopplereffect.builders.employees.PersonalDataBuilder;
import ar.edu.unq.dopplereffect.builders.leaverequests.HolidayLeaveRequestBuilder;
import ar.edu.unq.dopplereffect.builders.leaverequests.HolidayLeaveRequestTypeBuilder;
import ar.edu.unq.dopplereffect.builders.leaverequests.IntervalDurationStrategyBuilder;
import ar.edu.unq.dopplereffect.builders.leaverequests.LeaveRequestBuilder;
import ar.edu.unq.dopplereffect.builders.leaverequests.LeaveRequestCustomTypeBuilder;
import ar.edu.unq.dopplereffect.builders.leaverequests.OneDayDurationStrategyBuilder;
import ar.edu.unq.dopplereffect.builders.projects.ProjectBuilder;
import ar.edu.unq.dopplereffect.builders.projects.SkillBuilder;
import ar.edu.unq.dopplereffect.builders.salaries.SalarySpecificationBuilder;
import ar.edu.unq.dopplereffect.entity.Entity;
import ar.edu.unq.dopplereffect.persistence.repositories.HibernatePersistentRepository;
import ar.edu.unq.dopplereffect.persistence.util.SpringPersistenceTest;

public class GenericPersistenceTest extends SpringPersistenceTest {

    @Resource(name = "repo.generic")
    private HibernatePersistentRepository<Object> repository;

    @Test
    public void testSaveAllEntities() {
        List<Builder<? extends Entity>> builders = this.getAllBuilders();
        for (Builder<? extends Entity> builder : builders) {
            Object object = builder.build();
            try {
                repository.save(object);
            } catch (Exception e) {
                Assert.fail("la entidad no pudo persistirse: " + e.getMessage());
            }
        }
    }

    @SuppressWarnings("unchecked")
    private List<Builder<? extends Entity>> getAllBuilders() {
        // @formatter:off
        return Arrays.asList(
            new EmployeeBuilder(),
            new SalarySpecificationBuilder(),
            new ProjectBuilder(),
            new SkillBuilder(),
            new LeaveRequestBuilder(),
            new LeaveRequestCustomTypeBuilder(),
            new HolidayLeaveRequestTypeBuilder(),
            new HolidayLeaveRequestBuilder(),
            new IntervalDurationStrategyBuilder(),
            new OneDayDurationStrategyBuilder(),
            new AddressBuilder(),
            new CareerDataBuilder(),
            new PersonalDataBuilder()
        );
        // @formatter:on
    }
}
