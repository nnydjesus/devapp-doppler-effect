package ar.edu.unq.dopplereffect.project;

import ar.edu.unq.dopplereffect.data.PersonalData;
import ar.edu.unq.dopplereffect.entity.Entity;

/**
 * Provee la informacion de un cliente de la empresa.
 */
public class ClientData extends Entity {
    private static final long serialVersionUID = 1L;

    /* ************************ INSTANCE VARIABLES ************************ */

    private PersonalData personalData;

    /* *************************** CONSTRUCTORS *************************** */

    /* **************************** ACCESSORS ***************************** */

    public String getName() {
        return personalData.getFirstName();
    }

    public void setName(final String name) {
        personalData.setFirstName(name);
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public void setPersonalData(final PersonalData personalData) {
        this.personalData = personalData;
    }
}
