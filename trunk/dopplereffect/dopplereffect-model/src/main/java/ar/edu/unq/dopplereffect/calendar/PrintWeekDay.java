package ar.edu.unq.dopplereffect.calendar;

import org.joda.time.DateTime;

/**
 */
public class PrintWeekDay implements PrintDay {

    @Override
    public String printDay(final DateTime date) {
        return WeekDayName.valueOf(date.getDayOfWeek()) + " " + date.getDayOfMonth() + "/" + date.getMonthOfYear();
    }

}
