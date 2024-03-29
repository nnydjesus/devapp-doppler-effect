package ar.edu.unq.dopplereffect.salaries;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import ar.edu.unq.dopplereffect.employees.CareerPlan;
import ar.edu.unq.dopplereffect.employees.CareerPlanLevel;
import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.entity.Entity;
import ar.edu.unq.dopplereffect.exceptions.UserException;

/**
 * Representa aquellos parametros que sirven para determinar el sueldo de un
 * empleado, como por ejemplo el monto minimo y maximo, y el plan de carrera.
 */
public class SalarySpecification extends Entity {

    private static final long serialVersionUID = 5210798123849191719L;

    /* ************************ INSTANCE VARIABLES ************************ */

    private int year;

    private CareerPlan plan;

    private CareerPlanLevel level;

    private List<Integer> percentages;

    private float minSalary;

    private float maxSalary;

    /* *************************** CONSTRUCTORS *************************** */

    /**
     * Constructor.
     * 
     * @param year
     *            el anio en el que estos parametros del sueldo tienen validez.
     * @param plan
     *            el plan de carrera al que se aplican los parametros del
     *            sueldo.
     * @param level
     *            el nivel del empleado al cual se le aplican los parametros del
     *            sueldo.
     */
    public SalarySpecification(final int year, final CareerPlan plan, final CareerPlanLevel level) {
        super();
        this.year = year;
        this.plan = plan;
        this.level = level;
    }

    public SalarySpecification(final int year, final CareerPlan plan, final CareerPlanLevel level,
            final Integer... percentages) {
        super();
        this.year = year;
        this.plan = plan;
        this.level = level;
        this.percentages = new LinkedList<Integer>();
        Collections.addAll(this.percentages, percentages);
    }

    public SalarySpecification(final int year, final CareerPlan plan, final CareerPlanLevel level,
            final float minSalary, final float maxSalary, final List<Integer> percentages) {
        super();
        this.year = year;
        this.plan = plan;
        this.level = level;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.percentages = percentages;
    }

    public SalarySpecification() {
        super();
    }

    /* **************************** ACCESSORS ***************************** */

    public int getYear() {
        return year;
    }

    public void setYear(final int year) {
        this.year = year;
    }

    public CareerPlanLevel getLevel() {
        return level;
    }

    public void setLevel(final CareerPlanLevel level) {
        this.level = level;
    }

    public CareerPlan getPlan() {
        return plan;
    }

    public void setPlan(final CareerPlan plan) {
        this.plan = plan;
    }

    public List<Integer> getPercentages() {
        return percentages;
    }

    public void setPercentages(final List<Integer> percentages) {
        this.percentages = percentages;
    }

    public float getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(final float minSalary) {
        this.minSalary = minSalary;
    }

    public float getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(final float maxSalary) {
        this.maxSalary = maxSalary;
    }

    /* **************************** OPERATIONS **************************** */

    /**
     * Verifica si un porcentaje dado es valido para la banda.
     * 
     * @param percentage
     *            el porcentaje que se desea comprobar.
     * @return <code>true</code> si el porcentaje es valido, <code>false</code>
     *         en caso contrario.
     */
    public boolean hasPercentage(final int percentage) {
        for (int porc : this.getPercentages()) {
            if (porc == percentage) {
                return true;
            }
        }
        return false;
    }

    /**
     * Calcula el sueldo para un porcentaje dado. El valor calculado debe estar
     * comprendido entre el rango dado por el sueldo minimo y el maximo. El
     * porcentaje ademas debe ser valido, es decir, debe pertenecer a la banda
     * definida.
     * 
     * @param percentage
     *            el porcentaje con el cual se regula el monto del sueldo.
     */
    public float getSalary(final int percentage) {
        if (this.hasPercentage(percentage)) {
            return this.getMinSalary() + (float) percentage / 100 * (this.getMaxSalary() - this.getMinSalary());
        } else {
            throw new UserException("El porcentaje no figura dentro de la banda");
        }
    }

    /**
     * Cambia la banda del sueldo estipulado. Si es necesario actualiza
     * empleados para que queden consistentes acorde a la nueva banda.
     * 
     * @param newPercentages
     *            una lista de porcentajes que representan a la nueva banda.
     * @param employees
     *            los empleados que pueden estar afectados y deben actualizarse
     *            sus sueldos.
     */
    public void changePercentages(final List<Integer> newPercentages, final Set<Employee> employees) {
        Collections.sort(newPercentages);
        if (newPercentages.get(0) != 0 || newPercentages.get(newPercentages.size() - 1) != 100) {
            throw new UserException("La banda debe contener 0 y 100");
        }
        this.setPercentages(newPercentages);
        for (Employee empl : employees) {
            if (this.affectsPercentagesChange(empl)) {
                empl.changeSalaryPercentage(this.getPercentages());
            }
        }
    }

    /* ************************* PRIVATE METHODS ************************** */

    private boolean affectsPercentagesChange(final Employee employee) {
        // si el porcentaje del empleado no esta en la banda,
        // entonces esta afectado por el cambio de banda
        return !this.hasPercentage(employee.getPercentage());
    }
}
