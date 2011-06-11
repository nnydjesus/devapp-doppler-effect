package ar.edu.unq.dopplereffect.service.leaverequest;

import ar.edu.unq.dopplereffect.service.DTO;

public class LeaveRequestDetailDTO implements DTO {

    private static final long serialVersionUID = -4482868060744676731L;

    private String reason;

    public String getReason() {
        return reason;
    }

    public void setReason(final String reason) {
        this.reason = reason;
    }
}
