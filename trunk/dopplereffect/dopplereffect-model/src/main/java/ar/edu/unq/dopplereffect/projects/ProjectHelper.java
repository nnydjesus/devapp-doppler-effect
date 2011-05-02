package ar.edu.unq.dopplereffect.projects;


/**
 */
public class ProjectHelper {
    private final static int HOURS_PER_DAY = 8;

    public static int getHoursOfEffort(final int days) {
        return days * HOURS_PER_DAY;
    }

}
