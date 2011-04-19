package ar.edu.unq.dopplereffect.calendar;

import java.util.Arrays;
import java.util.List;

import org.joda.time.Days;
import org.joda.time.Months;
import org.joda.time.Years;

import ar.edu.unq.dopplereffect.bean.Assignable;
import ar.edu.unq.dopplereffect.employees.Employee;

/**
 * Representa una calendario semanal para de los empleados
 */
public class StrategyCalendarImpl extends AbstractStrategyCalendar {

    public StrategyCalendarImpl(final CalendarWrapper week) {
        super(week);
    }

    @Override
    public Matrix<Employee, String, Assignable> getCalendar(final List<Employee> employees) {
        final Matrix<Employee, String, Assignable> matrix = new Matrix<Employee, String, Assignable>();
        String printDay;
        for (Employee employee : employees) {
            CalendarWrapper day = this.getWeekday().getCalendarDay();
            for (int i = 0; i < this.getWeekday().getTotalDays(); i++) {
                printDay = this.getWeekday().getPrintDay().printDay(day.getDay());
                matrix.put(employee, printDay, employee.getAssignableForDay(day.getDay()));

                day.plus();
            }
        }
        return matrix;
    }

    public static void main(final String[] args) {

        final StrategyCalendarImpl weekStrategyCalendar = new StrategyCalendarImpl(new MonthWrapper(Years.years(2011),
                Months.FOUR, Days.FOUR));
        final List<Employee> employees = Arrays.asList(new Employee(), new Employee());

        weekStrategyCalendar.getCalendar(employees).loggerMatrix();
        weekStrategyCalendar.next();
        weekStrategyCalendar.getCalendar(employees).loggerMatrix();
    }
}
