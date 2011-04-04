package ar.edu.unq.dopplereffect.bean;

public class Client {
    private PersonalData personalData;

    public String getRazonSocial() {
        return personalData.getFirstName();
    }

    public void setRazonSocial(final String razonSocial) {
        personalData.setFirstName(razonSocial);
    }

    public void setPersonalData(final PersonalData personalData) {
        this.personalData = personalData;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }
}
