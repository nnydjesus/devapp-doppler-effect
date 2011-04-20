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
public class Calendar extends AbstractCalendar {

    /* ************************ INSTANCE VARIABLES ************************ */
    /* *************************** CONSTRUCTORS *************************** */

    public Calendar(final CalendarStrategy week) {
        super(week);
    }

    /* **************************** ACCESSORS ***************************** */
    /* **************************** OPERATIONS **************************** */

    @Override
    public Matrix<Employee, String, Assignable> getCalendar(final List<Employee> employees) {
        final Matrix<Employee, String, Assignable> matrix = new Matrix<Employee, String, Assignable>();
        String printDay;
        for (Employee employee : employees) {
            CalendarStrategy day = this.getStrategy().cloneStrategy();
            for (int i = 0; i < this.getStrategy().getTotalDays(); i++) {
                printDay = PrintDay.printDay(day.getDay());
                matrix.put(employee, printDay, employee.getAssignableForDay(day.getDay()));

                day.plus();
            }
        }
        return matrix;
    }

    public static void main(final String[] args) {

        final Calendar weekStrategyCalendar = new Calendar(new MonthStrategy(Years.years(2011), Months.FOUR,
                Days.FOUR));
        final List<Employee> employees = Arrays.asList(new Employee(), new Employee());

        weekStrategyCalendar.getCalendar(employees).loggerMatrix();
        weekStrategyCalendar.next();
        weekStrategyCalendar.getCalendar(employees).loggerMatrix();
    }
}
