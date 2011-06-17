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
        if (startDate == null) {
            return null;
        }
        return (Date) startDate.clone();
    }

    public void setStartDate(final Date startDate) {
        this.startDate = (Date) startDate.clone();
    }

    public Date getEndDate() {
        if (endDate == null) {
            return null;
        }
        return (Date) endDate.clone();
    }

    public void setEndDate(final Date endDate) {
        this.endDate = (Date) endDate.clone();
    }

    public EmployeeViewDTO getEmployee() {
        return employee;
    }

    public void setEmployee(final EmployeeViewDTO employee) {
        this.employee = employee;
    }
}
