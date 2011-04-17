package ar.edu.unq.dopplereffect.bean;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.Interval;

/**
 */
public class ProjectAssignment implements Assignable {
    private Employee employee;

    private List<Interval> intervals = new ArrayList<Interval>();

    public ProjectAssignment(final Employee anEmployee) {
        employee = anEmployee;
    }

    /**
     * Los asignables se asignan en un intervalo de tiempo,
     * {@code overlapsAssignment } verifica que no haya intersecciones entre su
     * intervalo asignado y el intervalo por parametro
     */
    public boolean overlapsAssignment(final Interval interval) {
        for (Interval assignment : intervals) {
            if (assignment.overlaps(interval))
                return true;
        }
        return false;
    }

    public boolean containsInterval(final Interval interval) {
        return intervals.contains(interval);
    }

    public boolean isAssigned(final Employee anEmployee) {
        return employee.equals(anEmployee);
    }

    public void setIntervals(final List<Interval> intervals) {
        this.intervals = intervals;
    }

    public void addInterval(final Interval interval) {
        intervals.add(interval);
    }

    public List<Interval> getIntervals() {
        return intervals;
    }

    public void setEmployee(final Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }

}
