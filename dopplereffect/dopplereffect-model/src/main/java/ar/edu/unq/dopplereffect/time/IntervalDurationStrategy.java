package ar.edu.unq.dopplereffect.time;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Interval;

import ar.edu.unq.dopplereffect.entity.Entity;
import ar.edu.unq.dopplereffect.leaverequests.LeaveRequest;

/**
 * Tipo de duracion que consiste en un intervalo, en el que su inicio, su fin y
 * todos sus dias intermedios estan contemplados. Aplicable a licencias y
 * asignaciones de proyectos.
 */
public class IntervalDurationStrategy extends Entity implements DurationStrategy, Iterable<DateTime> {

    private static final long serialVersionUID = 546936361450624108L;

    /* ************************ INSTANCE VARIABLES ************************ */

    private Interval interval;

    /* *************************** CONSTRUCTORS *************************** */

    public IntervalDurationStrategy(final Interval interval) {
        super();
        this.interval = interval;
    }

    public IntervalDurationStrategy(final DateTime start, final DateTime end) {
        this(new Interval(start, end));
    }

    public IntervalDurationStrategy() {
        super();
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

    public void setStartDate(final DateTime dateTime) {
        this.setInterval(new Interval(dateTime, this.getEndDate()));
    }

    public DateTime getEndDate() {
        return this.getInterval().getEnd();
    }

    public void setEndDate(final DateTime dateTime) {
        this.setInterval(new Interval(this.getStartDate(), dateTime));
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
                || this.getEndDate().equals(oneDayDuration.getDate());
    }

    @Override
    public int getSuperpositionDaysWith(final IntervalDurationStrategy intervalDS) {
        if (this.overlapsInterval(intervalDS)) {
            if (this.getStartDate().isBefore(intervalDS.getStartDate())) {
                DateTime minDate = this.getEndDate().isBefore(intervalDS.getEndDate()) ? this.getEndDate() : intervalDS
                        .getEndDate();
                return Days.daysBetween(intervalDS.getStartDate(), minDate).getDays() + 1;
            } else {
                DateTime minDate = intervalDS.getEndDate().isBefore(this.getEndDate()) ? intervalDS.getEndDate() : this
                        .getEndDate();
                return Days.daysBetween(this.getStartDate(), minDate).getDays() + 1;
            }
        } else {
            return 0;
        }
    }

    @Override
    public Iterator<DateTime> iterator() {
        return this.getAllDates().iterator();
    }

    /**
     * Retorna todas las fechas (instancias de {@link DateTime}) comprendidas en
     * el intervalo.
     */
    public List<DateTime> getAllDates() {
        List<DateTime> result = new LinkedList<DateTime>();
        DateTime current = new DateTime(this.getStartDate()); // es mutable
        while (!current.equals(this.getEndDate())) {
            result.add(current);
            current = current.plusDays(1);
        }
        result.add(new DateTime(this.getEndDate())); // DateTime es mutable
        return result;
    }

    @Override
    public DateTime getFirstDate() {
        return this.getStartDate();
    }

    /* ****************** EQUALS, HASHCODE, TOSTRING ********************** */

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + (interval == null ? 0 : interval.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        IntervalDurationStrategy other = (IntervalDurationStrategy) obj;
        if (interval == null) {
            if (other.interval != null) {
                return false;
            }
        } else if (!interval.equals(other.interval)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getStartDate().toString() + " ~ " + this.getEndDate().toString();
    }

}
