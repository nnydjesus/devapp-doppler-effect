package ar.edu.unq.dopplereffect.time;

import org.joda.time.DateTime;
import org.joda.time.Interval;

import ar.edu.unq.dopplereffect.leaverequests.LeaveRequest;

/**
 * Tipo de duracion de licencia de un unico dia.
 */
public class OneDayDurationStrategy implements DurationStrategy {

    /* ************************ INSTANCE VARIABLES ************************ */

    private DateTime date;

    /* *************************** CONSTRUCTORS *************************** */

    public OneDayDurationStrategy(final DateTime aDate) {
        super();
        date = aDate;
    }

    /* **************************** ACCESSORS ***************************** */

    public DateTime getDate() {
        return date;
    }

    public void setDate(final DateTime date) {
        this.date = date;
    }

    /* **************************** OPERATIONS **************************** */

    @Override
    public boolean includesDay(final DateTime day) {
        return this.getDate().equals(day);
    }

    @Override
    public int getAmountOfDays() {
        return 1;
    }

    @Override
    public boolean overlapsInterval(final Interval interv) {
        return interv.contains(this.getDate()) || interv.getEnd().equals(this.getDate());
    }

    @Override
    public int getYear() {
        return this.getDate().getYear();
    }

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
