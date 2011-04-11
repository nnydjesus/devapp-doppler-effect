package ar.edu.unq.dopplereffect.bean;

import ar.edu.unq.dopplereffect.bean.enums.CareerPlan;
import ar.edu.unq.dopplereffect.bean.enums.CareerPlanLevel;

/**
 * Esta clase provee los datos de empleo del empleado, como por ejemplo su plan
 * de carrera, su nivel y su porcentaje de sueldo.
 */
public class CareerData {

    /* ************************ INSTANCE VARIABLES ************************ */

    private CareerPlan careerPlan;

    private CareerPlanLevel level;

    private int percentage;

    /* *************************** CONSTRUCTORS *************************** */

    /* **************************** ACCESSORS ***************************** */

    public CareerPlan getCareerPlan() {
        return careerPlan;
    }

    public void setCareerPlan(final CareerPlan careerPlan) {
        this.careerPlan = careerPlan;
    }

    public CareerPlanLevel getLevel() {
        return level;
    }

    public void setLevel(final CareerPlanLevel level) {
        this.level = level;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(final int percentage) {
        this.percentage = percentage;
    }
}
