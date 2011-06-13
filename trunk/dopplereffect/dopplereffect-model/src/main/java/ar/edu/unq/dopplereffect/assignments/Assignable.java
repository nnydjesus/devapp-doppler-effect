package ar.edu.unq.dopplereffect.assignments;

import org.joda.time.DateTime;
import org.joda.time.Interval;

import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.entity.IEntity;
import ar.edu.unq.dopplereffect.time.IntervalDurationStrategy;

/**
 * Representa asignaciones a empleados, tanto licencias como participaciones en
 * proyectos son representados con asignaciones.
 */
public interface Assignable extends IEntity {

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

    /**
     * Similar a {@code overlapsAssignment } pero verifica que no haya
     * interseccion con un {@link IntervalDurationStrategy}.
     */
    boolean overlapsAssignment(IntervalDurationStrategy interval);

    /**
     * Retorna la cantidad de dias que la asignacion se superpone con un
     * {@link IntervalDurationStrategy} pasado como parametro.
     */
    int getSuperpositionDaysWith(IntervalDurationStrategy intervalDurationStrategy);

    /**
     * @return el empleado al cual le corresponde esta asignacion.
     */
    Employee getEmployee();

    /**
     * Asocia un empleado a esta asignacion.
     * 
     * @param employee
     *            el empleado al que le pertenece la asignacion.
     */
    void setEmployee(Employee employee);
}
