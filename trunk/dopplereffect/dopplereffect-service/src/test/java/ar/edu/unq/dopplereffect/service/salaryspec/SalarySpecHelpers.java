package ar.edu.unq.dopplereffect.service.salaryspec;

import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;

import ar.edu.unq.dopplereffect.employees.CareerPlan;
import ar.edu.unq.dopplereffect.employees.CareerPlanLevel;
import ar.edu.unq.dopplereffect.persistence.employee.SalarySpecificationRepositoryImpl;
import ar.edu.unq.dopplereffect.service.employee.CareerPlanServiceImpl;

public class SalarySpecHelpers {

    private SalarySpecHelpers() {
    }

    public static SalarySpecDTO createValidSalarySpecDTOFor(final SalarySpecServiceImpl service) {
        SalarySpecDTO result = new SalarySpecDTO();
        result.setYear(2011);
        result.setCareerPlan(CareerPlan.TESTER);
        String careerPlanLevel = "Junior";
        result.setCareerPlanLevel(careerPlanLevel);
        CareerPlanServiceImpl cplServiceMock = mock(CareerPlanServiceImpl.class);
        SalarySpecificationRepositoryImpl sspecRepoImpl = mock(SalarySpecificationRepositoryImpl.class);
        service.setRepository(sspecRepoImpl);
        service.setCareerPlanService(cplServiceMock);
        CareerPlanLevel cpl = mock(CareerPlanLevel.class);
        when(cplServiceMock.findFirstLevelWithName(careerPlanLevel)).thenReturn(cpl);
        result.setMinSalary(3000);
        result.setMaxSalary(4500);
        List<Integer> percs = new LinkedList<Integer>();
        percs.add(0);
        percs.add(100);
        result.setPercentages(percs);
        return result;
    }

}
