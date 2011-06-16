package ar.edu.unq.dopplereffect.builders.leaverequests;

import org.joda.time.DateTime;

import ar.edu.unq.dopplereffect.builders.Builder;
import ar.edu.unq.dopplereffect.time.OneDayDurationStrategy;

public class OneDayDurationStrategyBuilder implements Builder<OneDayDurationStrategy> {

    protected transient DateTime date = new DateTime("2011-06-09");

    public OneDayDurationStrategyBuilder withDate(final DateTime theDate) {
        date = theDate;
        return this;
    }

    @Override
    public OneDayDurationStrategy build() {
        return new OneDayDurationStrategy(date);
    }
}
