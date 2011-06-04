package ar.edu.unq.dopplereffect.calendar;

import java.util.List;

import org.joda.time.DateTime;

import ar.edu.unq.dopplereffect.assignments.Assignable;
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
    public Matrix<Employee, DateTime, Assignable> getCalendar(final List<Employee> employees) {
        final Matrix<Employee, DateTime, Assignable> matrix = new Matrix<Employee, DateTime, Assignable>();
        CalendarStrategy day;
        for (Employee employee : employees) {
            day = this.getStrategy().cloneStrategy();
            for (int i = 0; i < this.getStrategy().getTotalDays(); i++) {
                matrix.put(employee, day.getDay(), employee.getAssignableForDay(day.getDay()));
                day.plus();
            }
        }
        return matrix;
    }

}
