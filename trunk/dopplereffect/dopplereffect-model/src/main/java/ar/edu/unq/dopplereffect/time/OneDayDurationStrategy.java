package ar.edu.unq.dopplereffect.time;

import org.joda.time.DateTime;
import org.joda.time.Interval;

import ar.edu.unq.dopplereffect.entity.Entity;
import ar.edu.unq.dopplereffect.leaverequests.LeaveRequest;

/**
 * Tipo de duracion de un unico dia. Aplicable a licencias.
 */
public class OneDayDurationStrategy extends Entity implements DurationStrategy {
    private static final long serialVersionUID = 1L;

    /* ************************ INSTANCE VARIABLES ************************ */

    private DateTime date;

    /* *************************** CONSTRUCTORS *************************** */

    public OneDayDurationStrategy(final DateTime aDate) {
        super();
        date = aDate;
    }

    public OneDayDurationStrategy() {
        super();
    }

    /* **************************** ACCESSORS ***************************** */

    public DateTime getDate() {
        return date;
    }

    public void setDate(final DateTime date) {
        this.date = date;
    }

    /* **************************** OPERATIONS **************************** */

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean includesDay(final DateTime day) {
        return this.getDate().equals(day);
    }

    /**
     * {@inheritDoc} En este caso, es siempre 1.
     */
    @Override
    public int getAmountOfDays() {
        return 1;
    }

    /**
     * {@inheritDoc} En este caso se verifica que la fecha este dentro del
     * intervalo, dias de inicio y fin incluidos.
     */
    @Override
    public boolean overlapsInterval(final Interval interv) {
        return interv.contains(this.getDate()) || interv.getEnd().equals(this.getDate());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getYear() {
        return this.getDate().getYear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean overlapsWith(final LeaveRequest leaveReq) {
        return leaveReq.getDurationStrategy().overlapsInterval(this);
    }

    @Override
    public boolean overlapsInterval(final IntervalDurationStrategy intervalDuration) {
        return intervalDuration.overlapsInterval(this);
    }

    @Override
    public boolean overlapsInterval(final OneDayDurationStrategy oneDayDuration) {
        return oneDayDuration.getDate().equals(this.getDate());
    }
}
