package ar.edu.unq.dopplereffect.bean;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Interval;

import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.time.IntervalDurationStrategy;

/**
 * Asignacion de un empleado a un proyecto. Posee una coleccion de intervalos en
 * los cuales vale la asignacion.
 */
public class ProjectAssignment implements Assignable {

    /* ************************ INSTANCE VARIABLES ************************ */

    private Employee employee;

    private List<IntervalDurationStrategy> intervals = new ArrayList<IntervalDurationStrategy>();

    /* *************************** CONSTRUCTORS *************************** */

    public ProjectAssignment(final Employee anEmployee) {
        employee = anEmployee;
    }

    /* **************************** ACCESSORS ***************************** */

    public List<IntervalDurationStrategy> getIntervals() {
        return intervals;
    }

    public void setIntervals(final List<IntervalDurationStrategy> intervals) {
        this.intervals = intervals;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(final Employee employee) {
        this.employee = employee;
    }

    /* **************************** OPERATIONS **************************** */

    @Override
    public boolean isLeaveRequest() {
        return false;
    }

    @Override
    public boolean includesDay(final DateTime date) {
        // TODO testear!
        for (IntervalDurationStrategy interval : this.getIntervals()) {
            if (interval.includesDay(date))
                return true;
        }
        return false;
    }

    public void addInterval(final IntervalDurationStrategy interval) {
        intervals.add(interval);
    }

    public boolean isAssigned(final Employee anEmployee) {
        return employee.equals(anEmployee);
    }

    public boolean containsInterval(final IntervalDurationStrategy interval) {
        return intervals.contains(interval);
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
    public boolean overlapsAssignment(final Interval interval) {
        for (IntervalDurationStrategy assignment : intervals) {
            if (assignment.overlapsInterval(interval))
                return true;
        }
        return false;
    }
}
