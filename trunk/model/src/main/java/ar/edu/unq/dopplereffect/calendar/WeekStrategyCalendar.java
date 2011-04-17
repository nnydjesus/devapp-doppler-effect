package ar.edu.unq.dopplereffect.calendar;

import java.util.Arrays;
import java.util.List;

import org.joda.time.Days;
import org.joda.time.Months;
import org.joda.time.Years;

import ar.edu.unq.dopplereffect.bean.Employee;

/**
 * Representa una calendario semanal para de los empleados
 */
public class WeekStrategyCalendar extends AbstractStrategyCalendar {

    public WeekStrategyCalendar(final CalendarWrapper week) {
        super(week);
    }

    @Override
    public Matrix<Employee, Integer, String> getCalendar(final List<Employee> employee) {
        // logger.info("getCalendar\n");
        final Matrix<Employee, Integer, String> matrix = new Matrix<Employee, Integer, String>();

        for (Employee employee2 : employee) {
            for (int i = 0; i < 7; i++) {
                int dayOfWeek = ((WeekdayWrapper) this.getWeekday()).getDay().getDayOfWeek();
                matrix.put(employee2, dayOfWeek + i, " L ");
            }
        }
        return matrix;
    }

    public static void main(final String[] args) {
        final WeekStrategyCalendar weekStrategyCalendar = new WeekStrategyCalendar(new WeekdayWrapper(Years.years(2011),
                Months.FOUR, Days.FOUR));
        final List<Employee> employees = Arrays.asList(new Employee(), new Employee(null, null));
        weekStrategyCalendar.getCalendar(employees).loggerMatrix();
        weekStrategyCalendar.next();
        weekStrategyCalendar.getCalendar(employees).loggerMatrix();
    }
}
