package ar.edu.unq.dopplereffect.bean;

import ar.edu.unq.dopplereffect.configuration.Configuration;

/**
 * Licencia por vacaciones.
 */
public class HolidayLeaveRequest extends LeaveRequestType {

    /* *************************** CONSTRUCTORS *************************** */

    public HolidayLeaveRequest(final Configuration config) {
        super(config);
    }

    /* **************************** OPERATIONS **************************** */

    @Override
    public boolean isValidFor(final LeaveRequest leaveReq, final Employee employee) {
        return leaveReq.getAmountOfDays() >= this.getConfiguration().holidayMinimumPeriod();
    }
}
