package ar.edu.unq.dopplereffect.bean;

/**
 * Provee la informacion de un cliente de la empresa.
 */
public class ClientData {

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
