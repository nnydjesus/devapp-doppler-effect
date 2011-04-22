package ar.edu.unq.dopplereffect.time;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Interval;

import ar.edu.unq.dopplereffect.leaverequests.LeaveRequest;

/**
 * Tipo de duracion que consiste en un intervalo, en el que su inicio, su fin y
 * todos sus dias intermedios estan contemplados. Aplicable a licencias y
 * asignaciones de proyectos.
 */
public class IntervalDurationStrategy implements DurationStrategy {

    /* ************************ INSTANCE VARIABLES ************************ */

    private Interval interval;

    /* *************************** CONSTRUCTORS *************************** */

    public IntervalDurationStrategy(final Interval interval) {
        super();
        this.interval = interval;
    }

    /* **************************** ACCESSORS ***************************** */

    public Interval getInterval() {
        return interval;
    }

    public void setInterval(final Interval interval) {
        this.interval = interval;
    }

    public DateTime getStartDate() {
        return this.getInterval().getStart();
    }

    public DateTime getEndDate() {
        return this.getInterval().getEnd();
    }

    /* **************************** OPERATIONS **************************** */

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean includesDay(final DateTime day) {
        return this.getInterval().contains(day) || this.getEndDate().equals(day);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getAmountOfDays() {
        return Days.daysBetween(this.getStartDate(), this.getEndDate()).getDays() + 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean overlapsInterval(final Interval interv) {
        return interv.contains(this.getStartDate().minusDays(1)) || interv.contains(this.getEndDate());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getYear() {
        return this.getStartDate().getYear();
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
        return !(intervalDuration.getEndDate().isBefore(this.getStartDate()) || this.getEndDate().isBefore(
                intervalDuration.getStartDate()));
    }

    @Override
    public boolean overlapsInterval(final OneDayDurationStrategy oneDayDuration) {
        return this.getInterval().contains(oneDayDuration.getDate())
                || this.getInterval().getEnd().equals(oneDayDuration.getDate());
    }
}
