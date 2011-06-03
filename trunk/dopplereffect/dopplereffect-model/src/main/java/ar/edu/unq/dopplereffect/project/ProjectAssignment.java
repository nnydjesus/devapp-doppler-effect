package ar.edu.unq.dopplereffect.project;

import java.util.HashSet;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.Interval;

import ar.edu.unq.dopplereffect.assignments.Assignable;
import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.entity.Entity;
import ar.edu.unq.dopplereffect.time.IntervalDurationStrategy;

/**
 * Asignacion de un empleado a un proyecto. Posee una coleccion de intervalos en
 * los cuales vale la asignacion.
 */
public class ProjectAssignment extends Entity implements Assignable {
    private static final long serialVersionUID = 1L;

    /* ************************ INSTANCE VARIABLES ************************ */

    private Employee employee;

    private Set<IntervalDurationStrategy> intervals = new HashSet<IntervalDurationStrategy>();

    /* *************************** CONSTRUCTORS *************************** */

    public ProjectAssignment(final Employee anEmployee) {
        super();
        employee = anEmployee;
    }

    public ProjectAssignment() {
        super();
    }

    /* **************************** ACCESSORS ***************************** */

    public Set<IntervalDurationStrategy> getIntervals() {
        return intervals;
    }

    public void setIntervals(final Set<IntervalDurationStrategy> intervals) {
        this.intervals = intervals;
    }

    @Override
    public Employee getEmployee() {
        return employee;
    }

    @Override
    public void setEmployee(final Employee employee) {
        this.employee = employee;
    }

    /* **************************** OPERATIONS **************************** */

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isLeaveRequest() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean includesDay(final DateTime date) {
        for (IntervalDurationStrategy interval : this.getIntervals()) {
            if (interval.includesDay(date)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Agrega un intervalo a la asignacion.
     * 
     * @param interval
     *            el intervalo a agregar.
     */
    public void addInterval(final IntervalDurationStrategy interval) {
        this.getIntervals().add(interval);
    }

    /**
     * Verifica que la asignacion se corresponda con un empleado dado.
     * 
     * @param anEmployee
     *            el empleado.
     * @return <code>true</code> si la asignacion es del empleado,
     *         <code>false</code> en caso contrario.
     */
    public boolean isAssigned(final Employee anEmployee) {
        return this.getEmployee().equals(anEmployee);
    }

    /**
     * Verifica que el objeto receptor contenga (dentro de alguno de sus
     * intervalos) a otro intervalo recibido como parametro.
     * 
     * @param interval
     *            el intervalo que se desea comprobar.
     * @return <code>true</code> si contiene al intervalo, <code>false</code> en
     *         caso contrario.
     */
    public boolean containsInterval(final IntervalDurationStrategy interval) {
        return this.getIntervals().contains(interval);
    }

    /**
     * Los asignables se asignan en un intervalo de tiempo,
     * {@code overlapsAssignment } verifica que no haya intersecciones entre su
     * intervalo asignado y el intervalo por parametro
     */
    @Override
    public boolean overlapsAssignment(final IntervalDurationStrategy interval) {
        return this.overlapsAssignment(interval.getInterval());
    }

    @Override
    public int getSuperpositionDaysWith(final IntervalDurationStrategy intervalDS) {
        int result = 0;
        for (IntervalDurationStrategy interval : this.getIntervals()) {
            result += interval.getSuperpositionDaysWith(intervalDS);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean overlapsAssignment(final Interval interval) {
        for (IntervalDurationStrategy assignment : this.getIntervals()) {
            if (assignment.overlapsInterval(interval)) {
                return true;
            }
        }
        return false;
    }
}
