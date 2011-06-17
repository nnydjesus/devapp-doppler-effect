package ar.edu.unq.dopplereffect.builders.leaverequests;

import ar.edu.unq.dopplereffect.builders.Builder;
import ar.edu.unq.dopplereffect.employees.EmployeeTimeCalculator;
import ar.edu.unq.dopplereffect.leaverequests.LeaveRequestCustomType;

public class LeaveRequestCustomTypeBuilder implements Builder<LeaveRequestCustomType> {

    protected transient int minLimit = 1;

    protected transient int maxLimit = 10;

    protected transient int daysInYear = 20;

    protected transient String reason = "unknown";

    protected transient EmployeeTimeCalculator employeeTimeCalculator = new EmployeeTimeCalculator();

    public LeaveRequestCustomTypeBuilder withMinLimit(final int limit) {
        minLimit = limit;
        return this;
    }

    public LeaveRequestCustomTypeBuilder withMaxLimit(final int limit) {
        maxLimit = limit;
        return this;
    }

    public LeaveRequestCustomTypeBuilder withReason(final String theReason) {
        reason = theReason;
        return this;
    }

    public LeaveRequestCustomTypeBuilder withMaxDaysInYear(final int days) {
        daysInYear = days;
        return this;
    }

    public LeaveRequestCustomTypeBuilder withEmployeeTimeCalculator(final EmployeeTimeCalculator calculator) {
        employeeTimeCalculator = calculator;
        return this;
    }

    @Override
    public LeaveRequestCustomType build() {
        return new LeaveRequestCustomType(reason, daysInYear, minLimit, maxLimit, employeeTimeCalculator);
    }
}
