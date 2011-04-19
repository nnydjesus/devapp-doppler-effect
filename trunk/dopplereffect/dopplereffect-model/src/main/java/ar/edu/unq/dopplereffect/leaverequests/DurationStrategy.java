package ar.edu.unq.dopplereffect.leaverequests;

import org.joda.time.DateTime;
import org.joda.time.Interval;

/**
 * Tipos de duracion de las licencias, como por ejemplo licencias de un solo
 * dia, o licencias de un intervalo.
 */
public abstract class DurationStrategy {

    /**
     * Chequea que una fecha dada este incluida en las fechas descriptas por la
     * estrategia de duracion.
     * 
     * @param day
     *            la fecha que se desea verificar.
     * @return <code>true</code> si esta incluida, <code>false</code> en caso
     *         contrario.
     */
    public abstract boolean includesDay(DateTime day);

    /**
     * Retorna la cantidad de dias que la duracion de licencia abarca.
     */
    public abstract int getAmountOfDays();

    /**
     * Retorna el año al cual la duracion de la licencia pertenece.
     */
    public abstract int getYear();

    /**
     * Verifica si la duracion se superpone con un intervalo de tiempo dado.
     * Cada implementacion definira la superposicion a su manera.
     * 
     * @param interv
     *            el intervalo que se desea verificar.
     * @return <code>true</code> si existe una superposicion, <code>false</code>
     *         en caso contrario.
     */
    public abstract boolean overlapsInterval(Interval interv);

    /**
     * Verifica que el objeto receptor se superpone con otra duracion recibida
     * como parametro.
     * 
     * @param leaveReq
     *            la otra duracion.
     * @return <code>true</code> si se superponen, <code>false</code> en caso
     *         contrario.
     */
    public abstract boolean overlapsWith(LeaveRequest leaveReq);

    protected abstract boolean overlapsInterval(IntervalDurationStrategy intervalDuration);

    protected abstract boolean overlapsInterval(OneDayDurationStrategy oneDayDuration);
}
