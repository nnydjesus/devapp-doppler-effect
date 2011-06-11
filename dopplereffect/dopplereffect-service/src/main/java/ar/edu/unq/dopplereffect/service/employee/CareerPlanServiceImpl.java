package ar.edu.unq.dopplereffect.service.employee;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.dopplereffect.employees.CareerPlanLevel;
import ar.edu.unq.dopplereffect.persistence.employee.CareerPlanLevelRepositoryImpl;

@Service
public class CareerPlanServiceImpl implements CareerPlanService {

    private static final long serialVersionUID = 7179662842543409880L;

    /* ************************ INSTANCE VARIABLES ************************ */

    private CareerPlanLevelRepositoryImpl careerPlanLevelRepository;

    /* **************************** ACCESSORS ***************************** */

    public CareerPlanLevelRepositoryImpl getCareerPlanLevelRepository() {
        return careerPlanLevelRepository;
    }

    public void setCareerPlanLevelRepository(final CareerPlanLevelRepositoryImpl careerPlanLevelRepository) {
        this.careerPlanLevelRepository = careerPlanLevelRepository;
    }

    /* **************************** OPERATIONS **************************** */

    @Override
    @Transactional
    public CareerPlanLevel findFirstLevelWithName(final String careerPlanLevelName) {
        return this.getCareerPlanLevelRepository().getByName(careerPlanLevelName);
    }

    @Override
    @Transactional
    public List<String> searchAllLevels() {
        List<String> results = new LinkedList<String>();
        for (CareerPlanLevel cpl : this.getCareerPlanLevelRepository().searchAll()) {
            results.add(cpl.getName());
        }
        return results;
    }
}
