package ar.edu.unq.dopplereffect.salaries;

import java.util.HashSet;
import java.util.Set;

import ar.edu.unq.dopplereffect.employees.CareerPlan;
import ar.edu.unq.dopplereffect.employees.CareerPlanLevel;
import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.exception.UserException;

/**
 * Encargado de gestionar los sueldos de los empleados, y de cambiar de plan de
 * carrera a los mismos.
 */
public class SalariesController {

    /* ************************ INSTANCE VARIABLES ************************ */

    private Set<SalarySpecification> salarySpecifications;

    /* *************************** CONSTRUCTORS *************************** */

    public SalariesController() {
        salarySpecifications = new HashSet<SalarySpecification>();
    }

    /* **************************** ACCESSORS ***************************** */

    public Set<SalarySpecification> getSalarySpecifications() {
        return salarySpecifications;
    }

    public void setSalarySpecifications(final Set<SalarySpecification> salarySpecifications) {
        this.salarySpecifications = salarySpecifications;
    }

    /* **************************** OPERATIONS **************************** */

    /**
     * Agrega una especificacion de sueldo.
     * 
     * @param spec
     *            la especificacion a agregar.
     */
    public void addSalarySpecification(final SalarySpecification spec) {
        this.getSalarySpecifications().add(spec);
    }

    /**
     * Busca la especificacion del sueldo para un plan de carrera y un nivel
     * dados. Si no la encuentra, lanza una excepcion.
     * 
     * @param plan
     *            el plan de carrera que se desea buscar.
     * @param level
     *            el nivel (de plan de carrera) que se desea buscar.
     * @return un objeto que representa la especificacion del sueldo.
     * @throws UserException
     *             si no existe una especificacion para los parametros dados.
     */
    public SalarySpecification searchSalarySpecification(final CareerPlan plan, final CareerPlanLevel level) {
        for (SalarySpecification spec : this.getSalarySpecifications()) {
            if (spec.getPlan().equals(plan) && spec.getLevel().equals(level)) {
                return spec;
            }
        }
        throw new UserException("La especificacion del salario no existe para los parametros dados");
    }

    /**
     * Dado un empleado, retorna su salario correspondiente. Lanza una excepcion
     * si no es posible obtenerlo.
     * 
     */
    public int getSalary(final Employee employee) {
        SalarySpecification spec = this.searchSalarySpecification(employee.getCareerPlan(), employee.getLevel());
        return spec.getSalary(employee.getPercentage());
    }
}
