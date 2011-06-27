package ar.edu.unq.dopplereffect.service;

import static org.mockito.Mockito.mock;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.unq.dopplereffect.builders.salaries.SalarySpecificationBuilder;
import ar.edu.unq.dopplereffect.persistence.employee.SalarySpecificationRepositoryImpl;
import ar.edu.unq.dopplereffect.salaries.SalarySpecification;
import ar.edu.unq.dopplereffect.service.salaryspec.SalarySpecDTO;
import ar.edu.unq.dopplereffect.service.salaryspec.SalarySpecServiceImpl;
import ar.edu.unq.dopplereffect.service.util.SpringServiceTest;

public class ServiceTransactionalTest extends SpringServiceTest {

    @Autowired
    private SalarySpecServiceImpl salarySpecService;

    @Autowired
    private SalarySpecificationRepositoryImpl repoPosta;

    @Test
    public void testIsTransactional() {
        try {
            SalarySpecification s = new SalarySpecificationBuilder().build();
            SalarySpecificationRepositoryImpl repo = new SalarySpecificationRepositoryImpl() {
                private static final long serialVersionUID = -1284863682412375353L;

                @Override
                public void save(final SalarySpecification object) {
                    repoPosta.save(object);
                    throw new RuntimeException();
                }

                @Override
                public List<SalarySpecification> searchAll() {
                    return repoPosta.searchAll();
                }

            };
            salarySpecService.setRepository(repo);
            SalarySpecDTO salarySpecDTO = mock(SalarySpecDTO.class);
            salarySpecService.newSalarySpecification(salarySpecDTO);
            Assert.fail();
        } catch (RuntimeException e) {
            Assert.assertTrue(salarySpecService.searchAllSalarySpecs().isEmpty());
        }
    }
}
