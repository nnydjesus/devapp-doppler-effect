package ar.edu.unq.dopplereffect.leaverequests;

import ar.edu.unq.dopplereffect.bean.Employee;

/**
 * TODO
 */
public interface LeaveRequestType {

    /**
     * TODO
     */
    boolean isValidFor(LeaveRequest leaveRequest, Employee employee);

}
