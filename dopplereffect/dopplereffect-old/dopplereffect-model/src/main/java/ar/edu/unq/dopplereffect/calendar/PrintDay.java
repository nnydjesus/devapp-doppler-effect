package ar.edu.unq.dopplereffect.calendar;

import org.joda.time.DateTime;

/**
 * Imprime un dia ejemplo Lunes 4/5
 */
public class PrintDay {

    private PrintDay() {
    }

    public static String printDay(final DateTime date) {
        return WeekDayName.valueOf(date.getDayOfWeek()) + " " + date.getDayOfMonth() + "/" + date.getMonthOfYear();
    }
}
