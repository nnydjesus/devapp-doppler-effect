package ar.edu.unq.dopplereffect.calendar;

import org.joda.time.DateTime;

import ar.edu.unq.dopplereffect.assignments.Assignable;

/**
 * Representa a aquellos objetos de los cuales se los puede mostrar en un
 * calendario.
 */
public interface Calendareable {

    /**
     * @param date
     *            la fecha sobre la cual se desea saber si hay una asignacion.
     * @return una asignacion correspondiente a una fecha dada.
     */
    Assignable getAssignableForDay(DateTime date);

}
