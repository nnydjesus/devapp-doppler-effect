package ar.edu.unq.dopplereffect.builders.leaverequests;

import org.joda.time.DateTime;

import ar.edu.unq.dopplereffect.builders.Builder;
import ar.edu.unq.dopplereffect.time.IntervalDurationStrategy;

public class IntervalDurationStrategyBuilder implements Builder<IntervalDurationStrategy> {

    protected transient DateTime startDate = new DateTime("2011-06-09");

    protected transient DateTime endDate = new DateTime("2011-06-12");

    public IntervalDurationStrategyBuilder withStartDate(final DateTime theStartDate) {
        startDate = theStartDate;
        return this;
    }

    public IntervalDurationStrategyBuilder withEndDate(final DateTime theEndDate) {
        endDate = theEndDate;
        return this;
    }

    public IntervalDurationStrategyBuilder withInterval(final DateTime theStartDate, final DateTime theEndDate) {
        return this.withStartDate(theStartDate).withEndDate(theEndDate);
    }

    @Override
    public IntervalDurationStrategy build() {
        return new IntervalDurationStrategy(startDate, endDate);
    }
}
