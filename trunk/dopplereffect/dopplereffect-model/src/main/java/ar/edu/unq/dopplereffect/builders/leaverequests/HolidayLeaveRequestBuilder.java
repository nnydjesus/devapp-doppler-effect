package ar.edu.unq.dopplereffect.builders.leaverequests;

import org.joda.time.DateTime;
import org.joda.time.Interval;

import ar.edu.unq.dopplereffect.builders.Builder;
import ar.edu.unq.dopplereffect.builders.employees.EmployeeBuilder;
import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.leaverequests.LeaveRequest;
import ar.edu.unq.dopplereffect.leaverequests.LeaveRequestCustomType;
import ar.edu.unq.dopplereffect.time.IntervalDurationStrategy;

public class HolidayLeaveRequestBuilder implements Builder<LeaveRequest> {

    protected transient Employee employee = new EmployeeBuilder().build();

    protected transient IntervalDurationStrategy durationStrategy = new IntervalDurationStrategyBuilder().build();

    protected transient LeaveRequestCustomType type = new HolidayLeaveRequestTypeBuilder().build();

    public HolidayLeaveRequestBuilder withType(final LeaveRequestCustomType theType) {
        type = theType;
        return this;
    }

    public HolidayLeaveRequestBuilder withEmployee(final Employee theEmployee) {
        employee = theEmployee;
        return this;
    }

    public HolidayLeaveRequestBuilder withInterval(final DateTime start, final DateTime end) {
        durationStrategy.setInterval(new Interval(start, end));
        return this;
    }

    @Override
    public LeaveRequest build() {
        return new LeaveRequest(type, durationStrategy, employee);
    }
}
