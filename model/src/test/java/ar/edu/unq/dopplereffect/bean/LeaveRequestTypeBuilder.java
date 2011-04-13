package ar.edu.unq.dopplereffect.bean;

public class LeaveRequestTypeBuilder {
    private transient LeaveRequestType leaveRequestType;

    public LeaveRequestTypeBuilder() {
        leaveRequestType = new LeaveRequestType();
    }

    public LeaveRequestTypeBuilder withMinLimit(final int limit) {
        leaveRequestType.setMinLimit(limit);
        return this;
    }

    public LeaveRequestTypeBuilder withMaxLimit(final int limit) {
        leaveRequestType.setMaxLimit(limit);
        return this;
    }

    public LeaveRequestType build() {
        return leaveRequestType;
    }
}
