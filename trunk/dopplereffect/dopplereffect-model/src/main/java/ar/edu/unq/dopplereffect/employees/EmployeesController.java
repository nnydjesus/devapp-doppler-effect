package ar.edu.unq.dopplereffect.employees;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ar.edu.unq.dopplereffect.exceptions.UserException;
import ar.edu.unq.dopplereffect.salaries.SalarySpecification;

/**
 * Encargado de gestionar los sueldos de los empleados, y de cambiar de plan de
 * carrera a los mismos.
 */
public class EmployeesController {

    /* ************************ INSTANCE VARIABLES ************************ */

    private Set<Employee> employees;

    private Set<SalarySpecification> salarySpecifications;

    /* *************************** CONSTRUCTORS *************************** */

    public EmployeesController() {
        salarySpecifications = new HashSet<SalarySpecification>();
        employees = new HashSet<Employee>();
    }

    /* **************************** ACCESSORS ***************************** */

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(final Set<Employee> employees) {
        this.employees = employees;
    }

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
     * Agrega un empleado.
     * 
     * @param employee
     *            el empleado a agregar.
     */
    public void addEmployee(final Employee employee) {
        this.getEmployees().add(employee);
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
     */
    public float getSalary(final Employee employee) {
        SalarySpecification spec = this.searchSalarySpecification(employee.getCareerPlan(), employee.getLevel());
        return spec.getSalary(employee.getPercentage());
    }

    /**
     * Cambia los porcentajes de una combinacion plan de carrera - nivel dada.
     * Actualiza los porcentajes de aquellos empleados que resulten afectados.
     * 
     * @param careerPlan
     *            el plan de carrera al que se desea cambiar el porcentaje.
     * @param level
     *            el nivel (de plan de carrera) al que se desea cambiar el
     *            porcentaje.
     * @param percentages
     *            los nuevos porcentajes.
     */
    public void changePercentages(final CareerPlan careerPlan, final CareerPlanLevel level,
            final List<Integer> percentages) {
        SalarySpecification spec = this.searchSalarySpecification(careerPlan, level);
        spec.changePercentages(percentages, this.getEmployees());
    }

    /**
     * Le cambia el nivel a un empleado, por ejemplo de Junior a SemiSenior. Si
     * el sueldo luego del cambio es menor al que tenia anteriormente, se lanza
     * una excepcion.
     * 
     * @param employee
     *            el empleado al que se desea cambiarle el nivel.
     * @param level
     *            el nivel nuevo que se desea asignarle al empleado.
     * @param percentage
     *            el porcentaje nuevo para el empleado
     */
    public void changeCareerPlanLevel(final Employee employee, final CareerPlanLevel level, final int percentage) {
        this.changeCareerPlan(employee, employee.getCareerPlan(), level, percentage);
    }

    /**
     * Le cambia el nivel a un empleado, por ejemplo de Junior a SemiSenior.
     * Asigna como nuevo porcentaje 0. Si el sueldo luego del cambio es menor al
     * que tenia anteriormente, se lanza una excepcion.
     * 
     * @param employee
     *            el empleado al que se desea cambiarle el nivel.
     * @param level
     *            el nivel nuevo que se desea asignarle al empleado.
     */
    public void changeCareerPlanLevel(final Employee employee, final CareerPlanLevel level) {
        this.changeCareerPlanLevel(employee, level, 0);
    }

    /**
     * Cambia el plan de carrera y nivel de un empleado. Asigna como nuevo
     * porcentaje 0. Si el sueldo luego del cambio es menor al que tenia
     * anteriormente, se lanza una excepcion.
     * 
     * @param employee
     *            el empleado al que se desea cambiar de plan.
     * @param careerPlan
     *            el nuevo plan de carrera que se desea asignar.
     * @param level
     *            el nuevo nivel que se desea asignar.
     */
    public void changeCareerPlan(final Employee employee, final CareerPlan careerPlan, final CareerPlanLevel level) {
        this.changeCareerPlan(employee, careerPlan, level, 0);
    }

    /**
     * Cambia el plan de carrera y nivel de un empleado. Si el sueldo luego del
     * cambio es menor al que tenia anteriormente, se lanza una excepcion.
     * 
     * @param employee
     *            el empleado al que se desea cambiar de plan.
     * @param careerPlan
     *            el nuevo plan de carrera que se desea asignar.
     * @param level
     *            el nuevo nivel que se desea asignar.
     */
    public void changeCareerPlan(final Employee employee, final CareerPlan careerPlan, final CareerPlanLevel level,
            final int percentage) {
        employee.changeCareerPlan(this, careerPlan, level, percentage);
    }
}