package ar.edu.unq.dopplereffect.calendar;

import org.joda.time.DateTime;

import ar.edu.unq.dopplereffect.assignments.Assignable;

/**
 * TODO: description
 */
public interface Calendareable {

    Assignable getAssignableForDay(DateTime date);

}
