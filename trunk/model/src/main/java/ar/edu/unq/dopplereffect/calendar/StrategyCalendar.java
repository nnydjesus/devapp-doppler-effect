package ar.edu.unq.dopplereffect.calendar;

import java.util.List;

import ar.edu.unq.dopplereffect.bean.Employee;

public interface StrategyCalendar {

    Matrix<?, ?, ?> getCalendar(final List<Employee> employee);
}
