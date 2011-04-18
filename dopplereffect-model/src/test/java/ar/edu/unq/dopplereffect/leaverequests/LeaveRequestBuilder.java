package ar.edu.unq.dopplereffect.leaverequests;

import org.joda.time.DateTime;
import org.joda.time.Interval;

public class LeaveRequestBuilder {

    private transient LeaveRequest leaveRequest;

    public LeaveRequestBuilder() {
        leaveRequest = new LeaveRequest();
    }

    public LeaveRequestBuilder withInterval(final DateTime start, final DateTime end) {
        // precondicion: no debe utilizarse junto con withOnlyDate()
        leaveRequest.setDurationStrategy(new IntervalDurationStrategy(new Interval(start, end)));
        return this;
    }

    public LeaveRequestBuilder withOnlyDate(final DateTime date) {
        // precondicion: no debe utilizarse junto con withInterval()
        leaveRequest.setDurationStrategy(new OneDayDurationStrategy(date));
        return this;
    }

    public LeaveRequestBuilder withType(final LeaveRequestCustomType leaveRequestType) {
        leaveRequest.setType(leaveRequestType);
        return this;
    }

    public LeaveRequestBuilder withDurationStrategy(final LeaveRequestDurationStrategy strategy) {
        leaveRequest.setDurationStrategy(strategy);
        return this;
    }

    public LeaveRequest build() {
        return leaveRequest;
    }
}
