package ar.edu.unq.dopplereffect.bean;

import org.joda.time.DateTime;
import org.joda.time.Days;

/**
 * Representa pedidos de licencia adquiridos por los empleados.
 */
public class LeaveRequest {

    /* ************************ INSTANCE VARIABLES ************************ */

    private DateTime startDate;

    private DateTime endDate;

    private LeaveRequestType type;

    /* *************************** CONSTRUCTORS *************************** */

    /* **************************** ACCESSORS ***************************** */

    public DateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(final DateTime date) {
        startDate = date;
    }

    public DateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(final DateTime date) {
        endDate = date;
    }

    public LeaveRequestType getType() {
        return type;
    }

    public void setType(final LeaveRequestType leaveRequestType) {
        type = leaveRequestType;
    }

    /* **************************** OPERATIONS **************************** */

    /**
     * Verifica que una fecha dada este contemplada por la licencia.
     * 
     * @param date
     *            la fecha a verificar.
     * @return <code>true</code> si esta dentro de la licencia (se incluyen la
     *         fecha de inicio y fin), <code>false</code> en caso contrario.
     */
    public boolean includesDay(final DateTime date) {
        boolean isInTheStart = date.equals(this.getStartDate());
        boolean isInTheEnd = date.equals(this.getEndDate());
        boolean isInTheMiddle = date.isAfter(this.getStartDate()) && date.isBefore(this.getEndDate());
        return isInTheStart || isInTheEnd || isInTheMiddle;
    }

    /**
     * @return la cantidad de dias que cubre la licencia (incluidos dias
     *         laborables y no laborables).
     */
    public int getAmountOfDays() {
        return Days.daysBetween(this.getStartDate(), this.getEndDate()).getDays() + 1;
    }

    /**
     * Verifica si la licencia puede aplicarse a un empleado dado. Depende de
     * varios parametros, como por ejemplo la cantidad de licencias que ya se
     * tomo, o bien la duracion de la licencia.
     * 
     * @param el
     *            empleado que pide la licencia.
     * @return <code>true</code> si puede tomarse la licencia,
     *         <code>false</code> en caso contrario.
     */
    public boolean isValidFor(final Employee employee) {
        return this.getType().isValidFor(this, employee);
    }
}
