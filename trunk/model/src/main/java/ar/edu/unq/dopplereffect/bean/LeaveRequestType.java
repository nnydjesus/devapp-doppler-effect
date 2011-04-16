package ar.edu.unq.dopplereffect.bean;

/**
 * Representa un tipo de licencia particular. Un tipo de licencia posee un tope
 * minimo (es decir, por cuantos dias se puede dividir la licencia), un tope
 * maximo (cual es la maxima cantidad de dias que se puede tomar de una vez), y
 * la cantidad total de dias por año que se puede tomar. Ademas posee una razon,
 * un motivo que identifica a cada tipo de licencia.
 */
public class LeaveRequestType {

    /* ************************ INSTANCE VARIABLES ************************* */

    private String reason;

    private int maxDaysInYear;

    private int minLimit;

    private int maxLimit;

    /* *************************** CONSTRUCTORS **************************** */

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
    public boolean isValidFor(final LeaveRequest leaveReq, final Employee employee) {
        boolean satisfiesMinimum = this.isSpecifiedMinimum() ? leaveReq.getAmountOfDays() >= this.getMinLimit() : true;
        boolean satisfiesMaximum = this.isSpecifiedMaximum() ? leaveReq.getAmountOfDays() <= this.getMaxLimit() : true;
        boolean employeeCanRequestMoreDays = this.isSpecifiedMaxDaysInAYear() ? employee.daysRequestedInYear(this,
                leaveReq.getStartDate().getYear()) + leaveReq.getAmountOfDays() <= this.getMaxDaysInYear() : true;
        return satisfiesMinimum && satisfiesMaximum && employeeCanRequestMoreDays;
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
}
