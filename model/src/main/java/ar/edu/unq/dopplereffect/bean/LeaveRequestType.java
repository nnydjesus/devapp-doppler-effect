package ar.edu.unq.dopplereffect.bean;

import ar.edu.unq.dopplereffect.configuration.Configuration;

/**
 * Representa un tipo de licencia particular. Cada tipo realiza la validacion de
 * la licencia de manera diferente.
 */
public abstract class LeaveRequestType {

    /* ************************ INSTANCE VARIABLES ************************* */

    private final Configuration configuration;

    /* *************************** CONSTRUCTORS **************************** */

    public LeaveRequestType(final Configuration config) {
        configuration = config;
    }

    /* **************************** ACCESSORS ***************************** */

    public Configuration getConfiguration() {
        return configuration;
    }

    /* ************************* ABSTRACT METHODS ************************* */

    public abstract boolean isValidFor(LeaveRequest leaveReq, Employee employee);
}
