package ar.edu.unq.dopplereffect.leaverequests;

import ar.edu.unq.dopplereffect.entity.Entity;

/**
 * Representa una configuracion que determina la cantidad de dias permitidos en
 * concepto de cualquier licencia para una antiguedad dada.
 */
public class LeaveRequestDaysConfiguration extends Entity {

    private static final long serialVersionUID = 2236550810291279606L;

    /* ************************ INSTANCE VARIABLES ************************ */

    private int minYear;

    private int correspondingDays;

    /* *************************** CONSTRUCTORS *************************** */

    public LeaveRequestDaysConfiguration(final int minYear, final int correspondingDays) {
        super();
        this.minYear = minYear;
        this.correspondingDays = correspondingDays;
    }

    public LeaveRequestDaysConfiguration() {
        super();
    }

    /* **************************** ACCESSORS ***************************** */

    public int getMinYear() {
        return minYear;
    }

    public void setMinYear(final int minYear) {
        this.minYear = minYear;
    }

    public int getCorrespondingDays() {
        return correspondingDays;
    }

    public void setCorrespondingDays(final int correspondingDays) {
        this.correspondingDays = correspondingDays;
    }

    /* **************************** OPERATIONS **************************** */
}