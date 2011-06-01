package ar.edu.unq.dopplereffect.helpers;

import java.util.LinkedList;
import java.util.List;

import org.joda.time.DateTime;

public final class DateHelpers {

    // @formatter:off
    public static final DateTime 
            D_2011_04_01 = new DateTime("2011-04-01"), 
            D_2011_04_05 = new DateTime("2011-04-05"), 
            D_2011_04_06 = new DateTime("2011-04-06"),
            D_2011_04_07 = new DateTime("2011-04-07"),
            D_2011_04_08 = new DateTime("2011-04-08"),
            D_2011_04_09 = new DateTime("2011-04-09"),
            D_2011_04_10 = new DateTime("2011-04-10"),
            D_2011_04_11 = new DateTime("2011-04-11"),
            D_2011_04_12 = new DateTime("2011-04-12"),
            D_2011_04_13 = new DateTime("2011-04-13");
    // @formatter:on

    private DateHelpers() {
    }

    public static DateTime getDate(final int year, final int month, final int day) {
        return new DateTime(year, month, day, 0, 0, 0, 0);
    }

    public static List<DateTime> getDates(final DateTime start, final DateTime end) {
        assert start.isBefore(end);
        List<DateTime> result = new LinkedList<DateTime>();
        DateTime current = start;
        do {
            result.add(current);
            current = current.plusDays(1);
        } while (!current.equals(end));
        return result;
    }
}
