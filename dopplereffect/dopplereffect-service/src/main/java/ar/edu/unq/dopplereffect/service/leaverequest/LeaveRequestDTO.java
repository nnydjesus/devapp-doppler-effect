package ar.edu.unq.dopplereffect.service.leaverequest;

import java.util.Date;

import ar.edu.unq.dopplereffect.service.DTO;
import ar.edu.unq.dopplereffect.service.employee.EmployeeViewDTO;

public class LeaveRequestDTO implements DTO {

    private static final long serialVersionUID = 7162010733415192899L;

    private EmployeeViewDTO employee;

    private String reason;

    private String durationType;

    private Date startDate;

    private Date endDate;

    public String getReason() {
        return reason;
    }

    public void setReason(final String reason) {
        this.reason = reason;
    }

    public String getDurationType() {
        return durationType;
    }

    public void setDurationType(final String durationType) {
        this.durationType = durationType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(final Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(final Date endDate) {
        this.endDate = endDate;
    }

    public EmployeeViewDTO getEmployee() {
        return employee;
    }

    public void setEmployee(final EmployeeViewDTO employee) {
        this.employee = employee;
    }
}
