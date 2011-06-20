package ar.edu.unq.dopplereffect.service.salaryspec;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.dopplereffect.employees.CareerPlan;
import ar.edu.unq.dopplereffect.employees.CareerPlanLevel;
import ar.edu.unq.dopplereffect.exceptions.ValidationException;
import ar.edu.unq.dopplereffect.persistence.employee.SalarySpecificationRepositoryImpl;
import ar.edu.unq.dopplereffect.salaries.SalarySpecification;
import ar.edu.unq.dopplereffect.service.employee.CareerPlanServiceImpl;

@Service
public class SalarySpecServiceImpl implements SalarySpecService {

    private static final long serialVersionUID = -2063769171841444433L;

    /* ************************ INSTANCE VARIABLES ************************ */

    private SalarySpecificationRepositoryImpl repository;

    private CareerPlanServiceImpl careerPlanService;

    /* *************************** CONSTRUCTORS *************************** */

    /* **************************** ACCESSORS ***************************** */

    public SalarySpecificationRepositoryImpl getRepository() {
        return repository;
    }

    public void setRepository(final SalarySpecificationRepositoryImpl repository) {
        this.repository = repository;
    }

    public CareerPlanServiceImpl getCareerPlanService() {
        return careerPlanService;
    }

    public void setCareerPlanService(final CareerPlanServiceImpl careerPlanService) {
        this.careerPlanService = careerPlanService;
    }

    /* **************************** OPERATIONS **************************** */

    @Override
    @Transactional
    public void newSalarySpecification(final SalarySpecDTO salarySpecDTO) {
        SalarySpecification salarySpec = this.convert(salarySpecDTO);
        this.doValidations(salarySpecDTO, salarySpec);
        this.getRepository().save(salarySpec);
    }

    @Override
    @Transactional
    public void deleteSalarySpecification(final SalarySpecDTO salarySpecDTO) {
        CareerPlanLevel level = this.getCareerPlanService().findFirstLevelWithName(salarySpecDTO.getCareerPlanLevel());
        SalarySpecification sspec = this.getRepository().getByPlanAndLevel(salarySpecDTO.getCareerPlan(), level);
        this.getRepository().delete(sspec);
    }

    @Override
    @Transactional
    public void updateSalarySpecification(final SalarySpecDTO salarySpecDTO) {
        CareerPlanLevel level = this.getCareerPlanService().findFirstLevelWithName(salarySpecDTO.getCareerPlanLevel());
        SalarySpecification sspec = this.getRepository().getByPlanAndLevel(salarySpecDTO.getCareerPlan(), level);
        this.doValidations(salarySpecDTO, sspec);
        this.getRepository().update(sspec);
    }

    @Override
    @Transactional
    public List<SalarySpecDTO> searchAllSalarySpecs() {
        List<SalarySpecDTO> result = new LinkedList<SalarySpecDTO>();
        for (SalarySpecification spec : this.getRepository().searchAll()) {
            result.add(this.convert(spec));
        }
        return result;
    }

    @Override
    @Transactional
    public List<SalarySpecDTO> searchAllByExample(final SalarySpecDTO example) {
        SalarySpecification spec = this.convert(example);
        List<SalarySpecDTO> result = new LinkedList<SalarySpecDTO>();
        for (SalarySpecification sspec : this.getRepository().searchByExample(spec)) {
            result.add(this.convert(sspec));
        }
        return result;
    }

    @Override
    @Transactional
    public List<SalarySpecDTO> searchByCareerPlanAndLevel(final CareerPlan plan, final String level) {
        CareerPlanLevel careerPlanLevel = this.getCareerPlanService().findFirstLevelWithName(level);
        return this.convertAll(this.getRepository().searchByCareerPlanAndLevel(plan, careerPlanLevel));
    }

    @Override
    @Transactional
    public boolean existSalarySpecification(final int year, final CareerPlan plan, final String level) {
        CareerPlanLevel careerPlanLevel = this.getCareerPlanService().findFirstLevelWithName(level);
        return this.getRepository().searchByYearCareerPlanAndLevel(year, plan, careerPlanLevel) != null;
    }

    /* ************************* PRIVATE METHODS ************************** */

    private SalarySpecification convert(final SalarySpecDTO salarySpecDTO) {
        CareerPlanLevel careerPlanLevel = this.getCareerPlanService().findFirstLevelWithName(
                salarySpecDTO.getCareerPlanLevel());
        return new SalarySpecification(salarySpecDTO.getYear(), salarySpecDTO.getCareerPlan(), careerPlanLevel,
                salarySpecDTO.getMinSalary(), salarySpecDTO.getMaxSalary(), salarySpecDTO.getPercentages());
    }

    private SalarySpecDTO convert(final SalarySpecification spec) {
        SalarySpecDTO salarySpecDTO = new SalarySpecDTO();
        salarySpecDTO.setYear(spec.getYear());
        salarySpecDTO.setCareerPlan(spec.getPlan());
        salarySpecDTO.setCareerPlanLevel(spec.getLevel().getName());
        salarySpecDTO.setMinSalary(spec.getMinSalary());
        salarySpecDTO.setMaxSalary(spec.getMaxSalary());
        salarySpecDTO.setPercentages(spec.getPercentages());
        return salarySpecDTO;
    }

    private List<SalarySpecDTO> convertAll(final List<SalarySpecification> specs) {
        List<SalarySpecDTO> results = new LinkedList<SalarySpecDTO>();
        for (SalarySpecification spec : specs) {
            results.add(this.convert(spec));
        }
        return results;
    }

    private void validatePercentages(final List<Integer> percentages) {
        if (!percentages.contains(0) || !percentages.contains(100)) {
            throw new ValidationException("validations.percentages.basic");
        }
        for (int perc : percentages) {
            if (perc < 0 || perc > 100) {
                throw new ValidationException("validations.percentages.outOfBounds");
            }
        }
    }

    private void doValidations(final SalarySpecDTO salarySpecDTO, final SalarySpecification salarySpec) {
        this.validatePercentages(salarySpecDTO.getPercentages());
        if (this.getRepository().checkForExistentSalarySpec(salarySpec)) {
            throw new ValidationException("validations.existent");
        }
        if (salarySpecDTO.getMinSalary() >= salarySpecDTO.getMaxSalary()) {
            throw new ValidationException("validations.salary");
        }
    }
}