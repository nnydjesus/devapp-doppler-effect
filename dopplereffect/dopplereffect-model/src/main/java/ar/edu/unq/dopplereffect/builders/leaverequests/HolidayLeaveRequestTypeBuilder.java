package ar.edu.unq.dopplereffect.builders.leaverequests;

import ar.edu.unq.dopplereffect.builders.Builder;
import ar.edu.unq.dopplereffect.leaverequests.LeaveRequestCustomType;

public class HolidayLeaveRequestTypeBuilder implements Builder<LeaveRequestCustomType> {

    protected transient LeaveRequestCustomType type = new LeaveRequestCustomTypeBuilder().withReason("Vacaciones")
            .withMinLimit(7).build();

    public HolidayLeaveRequestTypeBuilder withInitialCorrespondingDays(final int days) {
        type.initialConfig(days);
        return this;
    }

    public HolidayLeaveRequestTypeBuilder withCorrespondingDays(final int year, final int days) {
        type.configure(year, days);
        return this;
    }

    @Override
    public LeaveRequestCustomType build() {
        return type;
    }
}
