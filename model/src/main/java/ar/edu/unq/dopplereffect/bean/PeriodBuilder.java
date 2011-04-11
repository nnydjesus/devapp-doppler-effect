package ar.edu.unq.dopplereffect.bean;

import org.joda.time.Period;

public class PeriodBuilder {

    private Period period;

    public PeriodBuilder() {
        period = new Period();
    }

    public PeriodBuilder withMonth(final int months) {
        period = period.plusMonths(months);
        return this;
    }

    public PeriodBuilder withDays(final int days) {
        period = period.plusDays(days);
        return this;
    }

    public Period build() {
        return period;
    }

}
