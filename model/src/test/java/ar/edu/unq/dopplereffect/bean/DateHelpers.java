package ar.edu.unq.dopplereffect.bean;

import org.joda.time.DateTime;

final public class DateHelpers {

    private DateHelpers() {
    }

    public static DateTime getDate(final int year, final int month, final int day) {
        return new DateTime(year, month, day, 0, 0, 0, 0);
    }
}
