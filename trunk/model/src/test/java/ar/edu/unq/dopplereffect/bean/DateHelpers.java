package ar.edu.unq.dopplereffect.bean;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.Duration;
import org.joda.time.Period;

public class DateHelpers {

    public static DateTime getDate(final int year, final int month, final int day) {
        return new DateTime(year, month, day, 0, 0, 0, 0);
    }

    public static Duration getDutarion(final Period period) {
        long millis = period.getMillis(); // no overflow can happen, even with
                                          // Integer.MAX_VALUEs
        millis += (long) period.getSeconds() * DateTimeConstants.MILLIS_PER_SECOND;
        millis += (long) period.getMinutes() * DateTimeConstants.MILLIS_PER_MINUTE;
        millis += (long) period.getHours() * DateTimeConstants.MILLIS_PER_HOUR;
        millis += (long) period.getDays() * DateTimeConstants.MILLIS_PER_DAY;
        millis += (long) period.getWeeks() * DateTimeConstants.MILLIS_PER_WEEK;
        millis += (long) period.getMonths() * DateTimeConstants.MILLIS_PER_WEEK * 4;

        return new Duration(millis);
    }

}
