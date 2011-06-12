package ar.edu.unq.dopplereffect.calendar;

import java.util.List;

import org.joda.time.DateTime;

import ar.edu.unq.dopplereffect.assignments.Assignable;

/**
 * Representa una calendario semanal para de los empleados
 */
public class Calendar<T extends Calendareable> extends AbstractCalendar<T> {

    /* ************************ INSTANCE VARIABLES ************************ */
    /* *************************** CONSTRUCTORS *************************** */

    private static final long serialVersionUID = 1L;

    public Calendar(final CalendarStrategy week) {
        super(week);
    }

    /* **************************** ACCESSORS ***************************** */
    /* **************************** OPERATIONS **************************** */

    @Override
    public Matrix<T, DateTime, Assignable> getCalendar(final List<T> calendareables) {
        final Matrix<T, DateTime, Assignable> matrix = new Matrix<T, DateTime, Assignable>();
        CalendarStrategy day;
        for (T calendareable : calendareables) {
            day = this.getStrategy().cloneStrategy();
            for (int i = 0; i < this.getStrategy().getTotalDays(); i++) {
                matrix.put(calendareable, day.getDay(), calendareable.getAssignableForDay(day.getDay()));
                day.plus();
            }
        }
        return matrix;
    }

}
