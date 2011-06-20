package ar.edu.unq.dopplereffect.service.employee;

import java.util.HashSet;
import java.util.Set;

import org.joda.time.DateTime;

import ar.edu.unq.dopplereffect.assignments.Assignable;
import ar.edu.unq.dopplereffect.calendar.Calendareable;
import ar.edu.unq.dopplereffect.service.DTO;

public class EmployeeViewDTO implements DTO, Calendareable {

    private static final long serialVersionUID = -2377606763449769912L;

    private String firstName;

    private String lastName;

    private int dni;

    private Set<Assignable> assignments = new HashSet<Assignable>();

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(final int dni) {
        this.dni = dni;
    }

    @Override
    public String toString() {
        return this.getFirstName() + " " + this.getLastName();
    }

    @Override
    public Assignable getAssignableForDay(final DateTime date) {
        for (Assignable assignable : this.getAssignments()) {
            if (assignable.includesDay(date)) {
                return assignable;
            }
        }
        return null;

    }

    public Set<Assignable> getAssignments() {
        return assignments;
    }

    public void setAssignments(final Set<Assignable> set) {
        assignments = set;
    }

}
