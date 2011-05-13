package ar.edu.unq.dopplereffect.time;

import org.joda.time.DateTime;
import org.joda.time.Interval;

import ar.edu.unq.dopplereffect.entity.IEntity;
import ar.edu.unq.dopplereffect.leaverequests.LeaveRequest;

/**
 * Tipos de duracion de las licencias, como por ejemplo licencias de un solo
 * dia, o licencias de un intervalo.
 */
public interface DurationStrategy extends IEntity {

    /**
     * Chequea que una fecha dada este incluida en las fechas descriptas por la
     * estrategia de duracion.
     * 
     * @param day
     *            la fecha que se desea verificar.
     * @return <code>true</code> si esta incluida, <code>false</code> en caso
     *         contrario.
     */
    boolean includesDay(DateTime day);

    /**
     * Retorna la cantidad de dias que la duracion de licencia abarca.
     */
    int getAmountOfDays();

    /**
     * Retorna el año al cual la duracion de la licencia pertenece.
     */
    int getYear();

    /**
     * Verifica si la duracion se superpone con un intervalo de tiempo dado.
     * Cada implementacion definira la superposicion a su manera.
     * 
     * @param interval
     *            el intervalo que se desea verificar.
     * @return <code>true</code> si existe una superposicion, <code>false</code>
     *         en caso contrario.
     */
    boolean overlapsInterval(Interval interval);

    /**
     * Verifica si el objeto receptor se superpone con otra duracion recibida
     * como parametro.
     * 
     * @param leaveReq
     *            la otra duracion.
     * @return <code>true</code> si se superponen, <code>false</code> en caso
     *         contrario.
     */
    boolean overlapsWith(LeaveRequest leaveReq);

    boolean overlapsInterval(IntervalDurationStrategy intervalDuration);

    boolean overlapsInterval(OneDayDurationStrategy oneDayDuration);
}
