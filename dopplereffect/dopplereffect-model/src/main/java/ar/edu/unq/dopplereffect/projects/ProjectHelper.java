package ar.edu.unq.dopplereffect.projects;

public class ProjectHelper {

    private static final int HOURS_PER_DAY = 8;

    private ProjectHelper() {
    }

    public static long daysToHoursEffort(final int days) {
        return (long) days * HOURS_PER_DAY;
    }

    public static int hoursEffortToDays(final Long effort) {
        return (int) (effort / HOURS_PER_DAY);
    }

}