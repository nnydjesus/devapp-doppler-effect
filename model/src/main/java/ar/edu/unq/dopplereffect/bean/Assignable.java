package ar.edu.unq.dopplereffect.bean;

import org.joda.time.DateTime;
import org.joda.time.Interval;

/**
 * Representa asignaciones a empleados, tanto licencias como participaciones en
 * proyectos son representados con asignaciones.
 */
public interface Assignable {

    /**
     * Indica si la asignacion es una licencia.
     */
    boolean isLeaveRequest();

    /**
     * Indica si la asignacion contiene a una determinada fecha.
     */
    boolean includesDay(DateTime date);

    /**
     * Los asignables se asignan en un intervalo de tiempo,
     * {@code overlapsAssignment } verifica que no haya intersecciones entre su
     * intervalo asignado y el intervalo por parametro
     */
    boolean overlapsAssignment(Interval interval);
}
