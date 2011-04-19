package ar.edu.unq.dopplereffect.calendar;

import org.joda.time.DateTime;

/**
 * TODO: Hacer el print del mes
 */
public class PrintMonthDay implements PrintDay {

    @Override
    public String printDay(final DateTime date) {
        return WeekDayName.valueOf(date.getDayOfWeek()) + " " + date.getDayOfMonth() + "/" + date.getMonthOfYear();
    }

}
