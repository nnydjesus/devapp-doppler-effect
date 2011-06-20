package ar.edu.unq.dopplereffect.employees;

/**
 * Identifica el area de trabajo de un empleado.
 */
public enum CareerPlan {
    TECHNICIAN("Technician"), TESTER("Tester"), FUNCTIONAL("Funtional"), DESIGNER("Designer");

    private String value;

    private CareerPlan(final String value) {
        this.setValue(value);
    }

    /**
     * @return el plan de carrera anterior, es decir aquel mas principiante.
     *         Para el plan de carrera mas principiante, se retorna
     *         <code>null</code>.
     */
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

    /**
     * @return el siguiente plan de carrera, es decir aquel mas avanzado. Para
     *         el plan de carrera mas avanzado, se retorna <code>null</code>.
     */
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

    @Override
    public String toString() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
