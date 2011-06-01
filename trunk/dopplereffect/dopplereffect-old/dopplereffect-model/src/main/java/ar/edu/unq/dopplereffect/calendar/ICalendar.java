package ar.edu.unq.dopplereffect.calendar;

import java.util.List;

import ar.edu.unq.dopplereffect.employees.Employee;

public interface ICalendar {

    Matrix<?, ?, ?> getCalendar(final List<Employee> employee);
}
