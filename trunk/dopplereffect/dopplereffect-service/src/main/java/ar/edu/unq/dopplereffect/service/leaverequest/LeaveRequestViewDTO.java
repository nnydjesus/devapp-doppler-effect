package ar.edu.unq.dopplereffect.service.leaverequest;

import java.util.Date;

import ar.edu.unq.dopplereffect.service.DTO;
import ar.edu.unq.dopplereffect.service.employee.EmployeeViewDTO;

public class LeaveRequestViewDTO implements DTO {

    private static final long serialVersionUID = 2113462952827076192L;

    private int amountOfDays;

    private Date startDate;

    private EmployeeViewDTO employee;

    private String reason;

    public int getAmountOfDays() {
        return amountOfDays;
    }

    public void setAmountOfDays(final int amountOfDays) {
        this.amountOfDays = amountOfDays;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(final Date startDate) {
        this.startDate = startDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(final String reason) {
        this.reason = reason;
    }

    public EmployeeViewDTO getEmployee() {
        return employee;
    }

    public void setEmployee(final EmployeeViewDTO employee) {
        this.employee = employee;
    }
}
