package ar.edu.unq.dopplereffect.leaverequests;

public class LeaveRequestTypeBuilder {

    private transient LeaveRequestCustomType leaveRequestType;

    public LeaveRequestTypeBuilder() {
        leaveRequestType = new LeaveRequestCustomType();
    }

    public LeaveRequestTypeBuilder withMinLimit(final int limit) {
        leaveRequestType.setMinLimit(limit);
        return this;
    }

    public LeaveRequestTypeBuilder withMaxLimit(final int limit) {
        leaveRequestType.setMaxLimit(limit);
        return this;
    }

    public LeaveRequestTypeBuilder withReason(final String reason) {
        leaveRequestType.setReason(reason);
        return this;
    }

    public LeaveRequestTypeBuilder withMaxDaysInYear(final int daysInYear) {
        leaveRequestType.setMaxDaysInYear(daysInYear);
        return this;
    }

    public LeaveRequestCustomType build() {
        return leaveRequestType;
    }
}
