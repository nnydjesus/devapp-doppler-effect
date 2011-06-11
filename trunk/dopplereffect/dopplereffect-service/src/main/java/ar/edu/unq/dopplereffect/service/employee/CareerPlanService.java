package ar.edu.unq.dopplereffect.service.employee;

import java.util.List;

import ar.edu.unq.dopplereffect.employees.CareerPlanLevel;
import ar.edu.unq.dopplereffect.service.Service;

public interface CareerPlanService extends Service {

    /**
     * Retorna el primer nivel de plan de carrera con el nombre dado.
     */
    CareerPlanLevel findFirstLevelWithName(String careerPlanLevelName);

    /**
     * Retorna todos los nombres de los niveles de plan de carrera.
     */
    List<String> searchAllLevels();

}
