package ar.edu.unq.dopplereffect.calendar;

import java.io.Serializable;
import java.util.List;

import ar.edu.unq.dopplereffect.employees.Employee;

public interface ICalendar extends Serializable {

    Matrix<?, ?, ?> getCalendar(final List<Employee> employee);
}
