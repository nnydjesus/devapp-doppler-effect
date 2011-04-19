package ar.edu.unq.dopplereffect.leaverequests;

import org.joda.time.DateTime;
import org.joda.time.Interval;

import ar.edu.unq.dopplereffect.bean.Assignable;
import ar.edu.unq.dopplereffect.employees.Employee;

/**
 * Representa pedidos de licencia adquiridos por los empleados.
 */
public class LeaveRequest implements Assignable {

    /* ************************ INSTANCE VARIABLES ************************ */

    private LeaveRequestType type;

    private LeaveRequestDurationStrategy durationStrategy;

    /* *************************** CONSTRUCTORS *************************** */

    /* **************************** ACCESSORS ***************************** */

    public LeaveRequestType getType() {
        return type;
    }

    public void setType(final LeaveRequestCustomType leaveRequestType) {
        type = leaveRequestType;
    }

    public LeaveRequestDurationStrategy getDurationStrategy() {
        return durationStrategy;
    }

    public void setDurationStrategy(final LeaveRequestDurationStrategy durationStrategy) {
        this.durationStrategy = durationStrategy;
    }

    /* **************************** OPERATIONS **************************** */

    @Override
    public boolean isLeaveRequest() {
        return true;
    }

    /**
     * Verifica si el tiempo definido de la licencia se superpone en algun
     * instante con un intervalo pasado como parametro. Dias bordes (inicio y
     * fin) son ambos contemplados.
     */
    @Override
    public boolean overlapsAssignment(final Interval interv) {
        return this.getDurationStrategy().overlapsInterval(interv);
    }

    /**
     * Verifica que una fecha dada este contemplada por la licencia.
     * 
     * @param date
     *            la fecha a verificar.
     * @return <code>true</code> si esta dentro de la licencia (se incluyen la
     *         fecha de inicio y fin), <code>false</code> en caso contrario.
     */
    @Override
    public boolean includesDay(final DateTime date) {
        return this.getDurationStrategy().includesDay(date);
    }

    /**
     * @return la cantidad de dias que cubre la licencia (incluidos dias
     *         laborables y no laborables).
     */
    public int getAmountOfDays() {
        return this.getDurationStrategy().getAmountOfDays();
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
        return !this.overlapOtherLeaveRequest(employee) && this.getType().isValidFor(this, employee);
    }

    /**
     * Verifica si la licencia se superpone con alguna licencia que el empleado
     * (pasado como parametro) ya tenia.
     * 
     * @param leaveRequest
     *            la licencia a verificar.
     * @return <code>true</code> si la licencia se superpone, <code>false</code>
     *         en caso contrario.
     */
    public boolean overlapOtherLeaveRequest(final Employee employee) {
        for (LeaveRequest req : employee.getLeaveRequests()) {
            if (this.overlapsWith(req)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica si la licencia se superpone con otra licencia pasada como
     * parametro. Se incluyen dias de inicio y fin para ambas.
     * 
     * @param leaveReq
     *            la licencia a verificar.
     * @return <code>true</code> si se superponen, <code>false</code> en caso
     *         contrario.
     */
    public boolean overlapsWith(final LeaveRequest leaveReq) {
        return this.getDurationStrategy().overlapsWith(leaveReq);
    }

    /**
     * Retorna el año al que pertenece la licencia.
     */
    public int getYear() {
        return this.getDurationStrategy().getYear();
    }
}
