package ar.edu.unq.dopplereffect.leaverequests;

import org.joda.time.DateTime;
import org.joda.time.Interval;

/**
 * Tipos de duracion de las licencias, como por ejemplo licencias de un solo
 * dia, o licencias de un intervalo.
 */
public abstract class LeaveRequestDurationStrategy {

    /**
     * TODO
     */
    public abstract boolean includesDay(DateTime day);

    /**
     * TODO
     */
    public abstract int getAmountOfDays();

    /**
     * TODO
     */
    public abstract boolean overlapsInterval(Interval interv);

    /**
     * TODO
     */
    public abstract boolean overlapsWith(LeaveRequest leaveReq);

    protected abstract boolean overlapsWithIntervalDuration(IntervalDurationStrategy intervalDuration);

    protected abstract boolean overlapsWithOneDayDuration(OneDayDurationStrategy oneDayDuration);

    public abstract int getYear();

}
