package ar.edu.unq.dopplereffect.bean.enums;

/**
 * Identifica el area de trabajo de un empleado.
 */
public enum CareerPlan {
    TECHNICIAN, TESTER, FUNCTIONAL, DESIGNER;

    public CareerPlan getPrevious() {
        switch (this) {
        case TECHNICIAN:
            return null;
        case TESTER:
            return TECHNICIAN;
        case FUNCTIONAL:
            return TESTER;
        case DESIGNER:
            return FUNCTIONAL;
        default:
            return null;
        }
    }

    public CareerPlan getNext() {
        switch (this) {
        case TECHNICIAN:
            return TESTER;
        case TESTER:
            return FUNCTIONAL;
        case FUNCTIONAL:
            return DESIGNER;
        case DESIGNER:
            return null;
        default:
            return null;
        }
    }
}
