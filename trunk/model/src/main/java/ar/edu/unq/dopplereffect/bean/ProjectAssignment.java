package ar.edu.unq.dopplereffect.bean;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Interval;

/**
 * 
 */
public class ProjectAssignment implements Assignable {

    /* ************************ INSTANCE VARIABLES ************************ */

    private Employee employee;

    private List<Interval> intervals = new ArrayList<Interval>();

    /* *************************** CONSTRUCTORS *************************** */

    public ProjectAssignment(final Employee anEmployee) {
        employee = anEmployee;
    }

    /* **************************** ACCESSORS ***************************** */

    public List<Interval> getIntervals() {
        return intervals;
    }

    public void setIntervals(final List<Interval> intervals) {
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
        for (Interval interval : this.getIntervals()) {
            if (interval.contains(date)) {
                return true;
            }
        }
        return false;
    }

    public void addInterval(final Interval interval) {
        intervals.add(interval);
    }

    public boolean isAssigned(final Employee anEmployee) {
        return employee.equals(anEmployee);
    }

    public boolean containsInterval(final Interval interval) {
        return intervals.contains(interval);
    }

    /**
     * Los asignables se asignan en un intervalo de tiempo,
     * {@code overlapsAssignment } verifica que no haya intersecciones entre su
     * intervalo asignado y el intervalo por parametro
     */
    @Override
    public boolean overlapsAssignment(final Interval interval) {
        for (Interval assignment : intervals) {
            if (assignment.overlaps(interval)) {
                return true;
            }
        }
        return false;
    }
}
