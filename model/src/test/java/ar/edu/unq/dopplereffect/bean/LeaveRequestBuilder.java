package ar.edu.unq.dopplereffect.bean;

import org.joda.time.DateTime;

public class LeaveRequestBuilder {

    private transient LeaveRequest leaveRequest;

    public LeaveRequestBuilder() {
        leaveRequest = new LeaveRequest();
    }

    public LeaveRequest build() {
        return leaveRequest;
    }

    public LeaveRequestBuilder withStartDate(final DateTime date) {
        leaveRequest.setStartDate(date);
        return this;
    }

    public LeaveRequestBuilder withEndDate(final DateTime date) {
        leaveRequest.setEndDate(date);
        return this;
    }

    public LeaveRequestBuilder withType(final LeaveRequestType leaveRequestType) {
        leaveRequest.setType(leaveRequestType);
        return this;
    }
}
