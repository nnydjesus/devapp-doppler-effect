package ar.edu.unq.dopplereffect.bean;

import org.joda.time.DateTime;
import org.joda.time.Interval;

public class LeaveRequestBuilder {

    private transient LeaveRequest leaveRequest;

    public LeaveRequestBuilder() {
        leaveRequest = new LeaveRequest();
    }

    public LeaveRequest build() {
        return leaveRequest;
    }

    public LeaveRequestBuilder withInterval(final DateTime start, final DateTime end) {
        leaveRequest.setInterval(new Interval(start, end));
        return this;
    }

    public LeaveRequestBuilder withType(final LeaveRequestType leaveRequestType) {
        leaveRequest.setType(leaveRequestType);
        return this;
    }
}
