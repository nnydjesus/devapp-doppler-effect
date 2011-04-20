package ar.edu.unq.dopplereffect.bean;

/**
 * Provee la informacion de un cliente de la empresa
 */
public class InformationClient {

    /* ************************ INSTANCE VARIABLES ************************ */

    private PersonalData personalData;

    /* *************************** CONSTRUCTORS *************************** */

    /* **************************** ACCESSORS ***************************** */

    public String getRazonSocial() {
        return personalData.getFirstName();
    }

    public void setRazonSocial(final String razonSocial) {
        personalData.setFirstName(razonSocial);
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public void setPersonalData(final PersonalData personalData) {
        this.personalData = personalData;
    }
}
