package ar.edu.unq.dopplereffect.employees;

import org.joda.time.DateTime;

import ar.edu.unq.dopplereffect.entity.Entity;

/**
 * Esta clase provee los datos de empleo del empleado, como por ejemplo su plan
 * de carrera, su nivel y su porcentaje de sueldo.
 */
public class CareerData extends Entity {
    private static final long serialVersionUID = 1L;

    /* ************************ INSTANCE VARIABLES ************************ */

    private DateTime joinDate;

    private CareerPlan careerPlan;

    private CareerPlanLevel level;

    private int percentage;

    /* *************************** CONSTRUCTORS *************************** */

    public CareerData() {
        // solo debe ser utilizado por Hibernate
        super();
    }

    public CareerData(final DateTime joinDate, final CareerPlan careerPlan, final CareerPlanLevel level,
            final int percentage) {
        super();
        this.joinDate = joinDate;
        this.careerPlan = careerPlan;
        this.level = level;
        this.percentage = percentage;
    }

    /* **************************** ACCESSORS ***************************** */

    public DateTime getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(final DateTime joinDate) {
        this.joinDate = joinDate;
    }

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

    public String getLevelName() {
        return this.getLevel().getName();
    }

    public void setLevelName(final String levelName) {
        this.getLevel().setName(levelName);
    }

    /* **************************** OPERATIONS **************************** */
}
