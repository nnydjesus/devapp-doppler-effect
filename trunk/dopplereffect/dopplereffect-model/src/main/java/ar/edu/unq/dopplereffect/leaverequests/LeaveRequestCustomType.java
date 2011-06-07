package ar.edu.unq.dopplereffect.leaverequests;

import java.util.LinkedList;
import java.util.List;

import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.employees.EmployeeTimeCalculator;
import ar.edu.unq.dopplereffect.entity.Entity;
import ar.edu.unq.dopplereffect.exceptions.UserException;

/**
 * Representa un tipo de licencia personalizado. Un tipo de licencia posee un
 * tope minimo (es decir, por cuantos dias se puede dividir la licencia), un
 * tope maximo (cual es la maxima cantidad de dias que se puede tomar de una
 * vez), y la cantidad total de dias por año que se puede tomar. Ademas posee
 * una razon, un motivo que identifica a cada tipo de licencia.
 */
public class LeaveRequestCustomType extends Entity implements LeaveRequestType {
    private static final long serialVersionUID = 1L;

    /* ************************ INSTANCE VARIABLES ************************* */

    private String reason;

    private int maxDaysInYear;

    private int minLimit;

    private int maxLimit;

    private List<LeaveRequestDaysConfiguration> configurations;

    private transient EmployeeTimeCalculator employeeTimeCalculator;

    /* *************************** CONSTRUCTORS **************************** */

    public LeaveRequestCustomType() {
        this("", 0, 0, 0);
    }

    public LeaveRequestCustomType(final String reason) {
        this(reason, 0, 0, 0);
    }

    public LeaveRequestCustomType(final String reason, final int maxDaysInYear, final int minLimit, final int maxLimit) {
        this(reason, maxDaysInYear, minLimit, maxLimit, new EmployeeTimeCalculator());
    }

    public LeaveRequestCustomType(final String reason, final int maxDaysInYear, final int minLimit, final int maxLimit,
            final EmployeeTimeCalculator calculator) {
        super();
        this.reason = reason;
        this.maxDaysInYear = maxDaysInYear;
        this.minLimit = minLimit;
        this.maxLimit = maxLimit;
        configurations = new LinkedList<LeaveRequestDaysConfiguration>();
        employeeTimeCalculator = calculator;
    }

    /* **************************** ACCESSORS ***************************** */

    public int getMinLimit() {
        return minLimit;
    }

    public void setMinLimit(final int minLimit) {
        this.minLimit = minLimit;
    }

    public int getMaxLimit() {
        return maxLimit;
    }

    public void setMaxLimit(final int maxLimit) {
        this.maxLimit = maxLimit;
    }

    @Override
    public String getReason() {
        return reason;
    }

    public void setReason(final String reason) {
        this.reason = reason;
    }

    public int getMaxDaysInYear() {
        return maxDaysInYear;
    }

    public void setMaxDaysInYear(final int maxDaysInYear) {
        this.maxDaysInYear = maxDaysInYear;
    }

    public List<LeaveRequestDaysConfiguration> getConfigurations() {
        return configurations;
    }

    public void setConfigurations(final List<LeaveRequestDaysConfiguration> configurations) {
        this.configurations = configurations;
    }

    public void setEmployeeTimeCalculator(final EmployeeTimeCalculator calculator) {
        employeeTimeCalculator = calculator;
    }

    /* **************************** OPERATIONS **************************** */

    /**
     * Verifica si la licencia puede aplicarse a un empleado dado (ya sea por la
     * configuracion de la licencia o por licencias previas del empleado).
     * 
     * @param leaveReq
     *            la licencia de la cual obtiene ciertos datos.
     * @param employee
     *            el empleado sobre el cual se desea realizar la validacion.
     * @return <code>true</code> si la licencia es valida, <code>false</code> en
     *         caso contrario.
     */
    @Override
    public boolean isValidFor(final LeaveRequest leaveReq, final Employee employee) {
        boolean satisfiesMinimum = this.isSpecifiedMinimum() ? leaveReq.getAmountOfDays() >= this.getMinLimit() : true;
        boolean satisfiesMaximum = this.isSpecifiedMaximum() ? leaveReq.getAmountOfDays() <= this.getMaxLimit() : true;
        int daysRequestedInYear = employeeTimeCalculator.daysRequestedInYear(employee, this, leaveReq.getYear());
        boolean employeeCanRequestMoreDays = this.isSpecifiedMaxDaysInAYear() ? daysRequestedInYear
                + leaveReq.getAmountOfDays() <= this.getMaxDaysInYear() : true;
        return satisfiesMinimum && satisfiesMaximum && employeeCanRequestMoreDays
                && this.satisfiesConfiguration(leaveReq, employee);
    }

    public void initialConfig(final int days) {
        this.configure(0, days);
    }

    /**
     * Configura los dias correspondientes permitidos maximos de licencia a cada
     * año de antiguedad.
     */
    public void configure(final int minYear, final int correspondingDays) {
        LeaveRequestDaysConfiguration newConfig = new LeaveRequestDaysConfiguration(minYear, correspondingDays);
        for (int i = 0; i < this.getConfigurations().size(); i++) {
            if (this.getConfigurations().get(i).getMinYear() == minYear) {
                // si ya existe la conf, la reemplaza
                this.getConfigurations().set(i, newConfig);
            }
            if (this.getConfigurations().get(i).getMinYear() > minYear) {
                // inserta de manera ordenada
                this.getConfigurations().add(i, newConfig);
            }
        }
        // si no hay ninguna, se agrega directamente
        this.getConfigurations().add(newConfig);
    }

    /**
     * Dado un año de antiguedad, retorna los dias correspondientes de
     * vacaciones que corresponden. Precondicion: asume que la configuracion de
     * dias se encuentra ordenada, satisfecho por el metodo configure();
     */
    public int getCorrespondingDays(final int year) {
        LeaveRequestDaysConfiguration previous = null;
        for (LeaveRequestDaysConfiguration config : this.getConfigurations()) {
            if (config.getMinYear() == year) {
                // si el año coincide exactamente
                return config.getCorrespondingDays();
            }
            if (config.getMinYear() > year) {
                // si se paso, retorna el anterior
                if (previous == null) {
                    throw new UserException("No existe configuracion para el año dado");
                } else {
                    return previous.getCorrespondingDays();
                }
            }
            previous = config;
        }
        if (this.getConfigurations().isEmpty()) {
            throw new UserException("No existe configuracion para el año dado");
        } else {
            return this.getConfigurations().get(this.getConfigurations().size() - 1).getCorrespondingDays();
        }
    }

    @Override
    public String toString() {
        return this.getReason();
    }

    /* ************************* PRIVATE METHODS ************************** */

    private boolean isSpecifiedMinimum() {
        return this.getMinLimit() != 0;
    }

    private boolean isSpecifiedMaximum() {
        return this.getMaxLimit() != 0;
    }

    private boolean isSpecifiedMaxDaysInAYear() {
        return this.getMaxDaysInYear() != 0;
    }

    private boolean satisfiesConfiguration(final LeaveRequest leaveReq, final Employee employee) {
        if (this.getConfigurations().isEmpty()) {
            return true;
        }
        int correspondingDays = this.getCorrespondingDays(leaveReq.getYear());
        int daysRequested = employeeTimeCalculator
                .daysRequestedInYear(employee, leaveReq.getType(), leaveReq.getYear());
        int daysThatCanRequest = correspondingDays - daysRequested;
        return leaveReq.getAmountOfDays() <= daysThatCanRequest;
    }
}
