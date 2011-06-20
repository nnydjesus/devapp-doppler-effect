package ar.edu.unq.dopplereffect.service.salaryspec;

import java.util.List;

import ar.edu.unq.dopplereffect.employees.CareerPlan;
import ar.edu.unq.dopplereffect.service.Service;

public interface SalarySpecService extends Service {

    void newSalarySpecification(final SalarySpecDTO salarySpecDTO);

    void deleteSalarySpecification(SalarySpecDTO salarySpecDTO);

    void updateSalarySpecification(SalarySpecDTO salarySpecDTO);

    List<SalarySpecDTO> searchAllSalarySpecs();

    List<SalarySpecDTO> searchAllByExample(SalarySpecDTO example);

    List<SalarySpecDTO> searchByCareerPlanAndLevel(CareerPlan plan, String Level);

    boolean existSalarySpecification(int year, CareerPlan plan, String level);

}
