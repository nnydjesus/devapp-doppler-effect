package ar.edu.unq.dopplereffect.employees;

import java.util.LinkedList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Interval;

import ar.edu.unq.dopplereffect.assignments.Assignable;
import ar.edu.unq.dopplereffect.leaverequests.LeaveRequest;
import ar.edu.unq.dopplereffect.leaverequests.LeaveRequestType;
import ar.edu.unq.dopplereffect.time.IntervalDurationStrategy;

/**
 * Responsable de calcular datos de las licencias de los empleados, como por
 * ejemplo la cantidad de dias que pidio un determinado empleado en un año.
 */
public class EmployeeTimeCalculator {

    /* **************************** OPERATIONS **************************** */

    /**
     * Calcula la cantidad de dias que el empleado pidio en un año, para un
     * determinado tipo de licencia.
     * 
     * @param employee
     *            el empleado
     * @param type
     *            el tipo de licencia por el que se desea buscar.
     * @param year
     *            el año por el que se desea averiguar.
     * @return la cantidad de dias que el empleado pidio hasta el momento.
     */
    public int daysRequestedInYear(final Employee employee, final LeaveRequestType type, final int year) {
        int days = 0;
        for (LeaveRequest leaveReq : employee.getLeaveRequests()) {
            if (leaveReq.getType().getReason().equals(type.getReason()) && year == leaveReq.getYear()) {
                days += leaveReq.getAmountOfDays();
            }
        }
        return days;
    }

    /**
     * Retorna <code>true</code> si el empleado esta libre en la fecha dada,
     * <code>false</code> en caso contrario.
     */
    public boolean isFreeAtDate(final Employee employee, final DateTime date) {
        for (Assignable assignable : employee.getAssignments()) {
            if (assignable.includesDay(date)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Verifica si el empleado esta libre en el intervalo dado.
     * 
     * @param employee
     *            el empleado en cuestion
     * @param interval
     *            el intervalo que se desea verificar.
     * @return <code>true</code> si en ninguna de las fechas descriptas por el
     *         intervalo el empleado esta asignado, <code>false</code> en caso
     *         contrario.
     */
    public boolean isFreeAtInterval(final Employee employee, final Interval interval) {
        for (Assignable assignable : employee.getAssignments()) {
            // si alguna asignacion se pisa, entonces no esta libre
            if (assignable.overlapsAssignment(interval)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Retorna aquellos {@link IntervalDurationStrategy} que resultan de la
     * "resta" entre el intervalo dado como parametro, y las asignaciones que
     * tenga el empleado. <br />
     * Por ejemplo, si el intervalo dado es del 1/6 al 12/6, y el empleado esta
     * asignado del 3/6 al 5/6, y del 7/6 al 9/6, entonces el resultado deberian
     * ser los siguientes 3 intervalos: del 1/6 al 2/6, del 6/6 al 6/6 (un solo
     * dia), y del 10/6 al 12/6.
     */
    public List<IntervalDurationStrategy> getAvailableIntervals(final Employee employee,
            final IntervalDurationStrategy intervalDS) {
        List<IntervalDurationStrategy> intervals = new LinkedList<IntervalDurationStrategy>();
        DateTime currentIntervalStart = null;
        boolean isCurrentIntervalStartSet = false;
        for (DateTime current : intervalDS) {
            if (this.isFreeAtDate(employee, current)) {
                if (!isCurrentIntervalStartSet) { // si no hay un intervalo
                    currentIntervalStart = current; // nuevo, se lo crea
                    isCurrentIntervalStartSet = true;
                }
            } else { // si ya habia empezado un intervalo
                if (isCurrentIntervalStartSet) {
                    // si habia un intervalo empezado, se lo termina
                    // contando el dia anterior
                    DateTime currentIntervalEnd = current.minusDays(1);
                    // y se lo agrega a la lista de intervalos
                    intervals.add(this.createIntervalDurationStrategy(currentIntervalStart, currentIntervalEnd));
                    // se resetea el intervalo actual
                    isCurrentIntervalStartSet = false;
                }
            }
        }
        return intervals;
    }

    /**
     * Retorna un porcentaje que indica en que medida el empleado satisface un
     * intervalo dado. Si esta totalmente libre durante el intervalo, entonces
     * satisface en un 100%. Si no, el porcentaje se va restando, si no
     * satisface ningun dia del intervalo, el porcentaje es 0.
     */
    public int availabilityLevel(final Employee employee, final IntervalDurationStrategy intervalDS) {
        int totalDays = intervalDS.getAmountOfDays();
        int freeDays = totalDays - this.calculateSuperpositionDays(employee, intervalDS);
        return freeDays * 100 / totalDays;
    }

    /* ************************* PRIVATE METHODS ************************** */

    private int calculateSuperpositionDays(final Employee employee, final IntervalDurationStrategy ids) {
        int result = 0;
        for (Assignable assignable : employee.getAssignments()) {
            result += assignable.getSuperpositionDaysWith(ids);
        }
        return result;
    }

    private IntervalDurationStrategy createIntervalDurationStrategy(final DateTime start, final DateTime end) {
        // definido solo porque chilla el PMD
        return new IntervalDurationStrategy(start, end);
    }
}
