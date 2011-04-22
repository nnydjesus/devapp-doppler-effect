package ar.edu.unq.dopplereffect.leaverequests;

import org.apache.commons.collections15.OrderedMap;
import org.apache.commons.collections15.map.ListOrderedMap;

import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.exception.UserException;

/**
 * Pedido de vacaciones, que contempla la antiguedad del empleado a la hora de
 * validar.
 */
public class HolidayLeaveRequest extends LeaveRequestCustomType {

    /* ************************ INSTANCE VARIABLES ************************ */

    private OrderedMap<Integer, Integer> configuration;

    /* *************************** CONSTRUCTORS *************************** */

    public HolidayLeaveRequest(final int minLimit, final int maxLimit) {
        super();
        configuration = new ListOrderedMap<Integer, Integer>();
        this.setReason("Holiday");
        this.setMinLimit(minLimit);
        this.setMaxLimit(maxLimit);
    }

    /* **************************** ACCESSORS ***************************** */

    public OrderedMap<Integer, Integer> getConfiguration() {
        return configuration;
    }

    public void setConfiguration(final OrderedMap<Integer, Integer> configuration) {
        this.configuration = configuration;
    }

    /* **************************** OPERATIONS **************************** */

    @Override
    public boolean isValidFor(final LeaveRequest leaveReq, final Employee employee) {
        return super.isValidFor(leaveReq, employee) && this.satisfiesConfiguration(leaveReq, employee);
    }

    /**
     * Configura los dias correspondientes de vacaciones a cada año de
     * antiguedad.
     */
    public void configure(final int minYear, final int correspondingDays) {
        this.getConfiguration().put(minYear, correspondingDays);
    }

    /**
     * Dado un año de antiguedad, retorna los dias correspondientes de
     * vacaciones.
     */
    public int getCorrespondingDays(final int year) {
        for (int y : this.getConfiguration().keySet()) {
            if (y == year) {
                return this.getConfiguration().get(y);
            }
            if (y > year) {
                return this.getConfiguration().get(this.getConfiguration().previousKey(y));
            }
        }
        if (this.getConfiguration().isEmpty()) {
            throw new UserException("No existe configuracion para el año dado");
        } else {
            return this.getConfiguration().get(this.getConfiguration().lastKey());
        }
    }

    /* ************************* PRIVATE METHODS ************************** */

    private boolean satisfiesConfiguration(final LeaveRequest leaveReq, final Employee employee) {
        int correspondingDays = this.getCorrespondingDays(leaveReq.getYear());
        int daysThatCanRequest = correspondingDays - employee.daysRequestedInYear(this, leaveReq.getYear());
        return leaveReq.getAmountOfDays() <= daysThatCanRequest;
    }
}
