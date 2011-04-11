package ar.edu.unq.dopplereffect.bean;

import java.util.Date;

public class LeaveRequestBuilder {

    private transient LeaveRequest leaveRequest;

    public LeaveRequestBuilder() {
        leaveRequest = new LeaveRequest();
    }

    public LeaveRequest build() {
        return leaveRequest;
    }

    public LeaveRequestBuilder withStartDate(final Date date) {
        leaveRequest.setStartDate(date);
        return this;
    }

    public LeaveRequestBuilder withEndDate(final Date date) {
        leaveRequest.setEndDate(date);
        return this;
    }

    public LeaveRequestBuilder withType(final LeaveRequestType leaveRequestType) {
        leaveRequest.setType(leaveRequestType);
        return this;
    }
}
