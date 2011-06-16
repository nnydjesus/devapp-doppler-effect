package ar.edu.unq.dopplereffect.builders.leaverequests;

import ar.edu.unq.dopplereffect.leaverequests.LeaveRequest;
import ar.edu.unq.dopplereffect.leaverequests.LeaveRequestType;
import ar.edu.unq.dopplereffect.time.DurationStrategy;

public class LeaveRequestBuilder {

    protected transient LeaveRequestType type = new LeaveRequestCustomTypeBuilder().build();

    protected transient DurationStrategy durationStrategy = new IntervalDurationStrategyBuilder().build();

    public LeaveRequestBuilder withType(final LeaveRequestType leaveRequestType) {
        type = leaveRequestType;
        return this;
    }

    public LeaveRequestBuilder withDurationStrategy(final DurationStrategy strategy) {
        durationStrategy = strategy;
        return this;
    }

    public LeaveRequest build() {
        return new LeaveRequest(type, durationStrategy);
    }
}
