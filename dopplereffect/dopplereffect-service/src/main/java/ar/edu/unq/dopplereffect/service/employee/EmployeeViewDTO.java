package ar.edu.unq.dopplereffect.service.employee;

import java.util.HashSet;
import java.util.Set;

import org.joda.time.DateTime;

import ar.edu.unq.dopplereffect.assignments.Assignable;
import ar.edu.unq.dopplereffect.calendar.Calendareable;

public class EmployeeViewDTO implements IEmployeeDTO, Calendareable {

    private static final long serialVersionUID = -2377606763449769912L;

    private String firstName;

    private String lastName;

    private int dni;

    private Set<Assignable> assignments = new HashSet<Assignable>();

    @Override
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    @Override
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

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + (assignments == null ? 0 : assignments.hashCode());
        result = prime * result + dni;
        result = prime * result + (firstName == null ? 0 : firstName.hashCode());
        result = prime * result + (lastName == null ? 0 : lastName.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        EmployeeViewDTO other = (EmployeeViewDTO) obj;
        if (assignments == null) {
            if (other.assignments != null) {
                return false;
            }
        } else if (!assignments.equals(other.assignments)) {
            return false;
        }
        if (dni != other.dni) {
            return false;
        }
        if (firstName == null) {
            if (other.firstName != null) {
                return false;
            }
        } else if (!firstName.equals(other.firstName)) {
            return false;
        }
        if (lastName == null) {
            if (other.lastName != null) {
                return false;
            }
        } else if (!lastName.equals(other.lastName)) {
            return false;
        }
        return true;
    }

}
