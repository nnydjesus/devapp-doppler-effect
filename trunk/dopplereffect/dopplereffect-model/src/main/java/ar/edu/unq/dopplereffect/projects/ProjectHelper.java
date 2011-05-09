package ar.edu.unq.dopplereffect.projects;

/**
 */
public class ProjectHelper {// NOPMD
    private static final int HOURS_PER_DAY = 8;

    private ProjectHelper() {
    }

    public static int daysToHoursEffort(final int days) {
        return days * HOURS_PER_DAY;
    }

    public static int hoursEffortToDays(final int effort) {
        return effort / HOURS_PER_DAY;
    }

}