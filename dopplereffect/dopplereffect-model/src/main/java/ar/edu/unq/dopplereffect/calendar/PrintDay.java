package ar.edu.unq.dopplereffect.calendar;

import org.joda.time.DateTime;

/**
 * TODO: description
 */
public class PrintDay {

    public static String printDay(final DateTime date) {
        return WeekDayName.valueOf(date.getDayOfWeek()) + " " + date.getDayOfMonth() + "/" + date.getMonthOfYear();
    }
}
