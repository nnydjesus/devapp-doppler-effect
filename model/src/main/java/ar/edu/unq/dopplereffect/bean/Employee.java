package ar.edu.unq.dopplereffect.bean;

import ar.edu.unq.dopplereffect.bean.enums.CareerPlan;

/**
 * Persona que trabaja en la empresa. Un empleado posee datos personales, como
 * por ejemplo su nombre y apellido, pero tambien posee datos relacionados al
 * trabajo, como su plan de carrera.
 */
public class Employee {

    private PersonalData personalData;

    private CareerData careerData;

    public Employee() {
        personalData = new PersonalData();
        careerData = new CareerData();
    }

    public String getFirstName() {
        return this.getPersonalData().getFirstName();
    }

    public void setFirstName(final String firstName) {
        this.getPersonalData().setFirstName(firstName);
    }

    public String getLastName() {
        return this.getPersonalData().getLastName();
    }

    public void setLastName(final String lastName) {
        this.getPersonalData().setLastName(lastName);
    }

    public int getDni() {
        return this.getPersonalData().getDni();
    }

    public void setDni(final int dni) {
        this.getPersonalData().setDni(dni);
    }

    public String getPhoneNumber() {
        return this.getPersonalData().getPhoneNumber();
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.getPersonalData().setPhoneNumber(phoneNumber);
    }

    public String getEmail() {
        return this.getPersonalData().getEmail();
    }

    public void setEmail(final String email) {
        this.getPersonalData().setEmail(email);
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public void setPersonalData(final PersonalData personalData) {
        this.personalData = personalData;
    }

    public CareerData getCareerData() {
        return careerData;
    }

    public void setCareerData(final CareerData careerData) {
        this.careerData = careerData;
    }

    public CareerPlan getCareerPlan() {
        return this.getCareerData().getCareerPlan();
    }

    public int getPercentage() {
        return this.getCareerData().getPercentage();
    }

    public void setPercentage(final int percentage) {
        this.getCareerData().setPercentage(percentage);
    }

    /**
     * Cambia su porcentaje de sueldo acorde al cambio en la banda de sueldo,
     * que se recibe como parametro. Pasa al porcentaje inmediato siguiente al
     * que tiene, de esta manera se asegura de no cobrar menos de lo que cobraba
     * antes.
     * 
     * @param percentages
     * 
     */
    public void changeSalaryPercentage(final int[] percentages) {
        for (int perc : percentages) {
            if (perc >= this.getPercentage()) {
                this.setPercentage(perc);
                return;
            }
        }
    }

    @Override
    public int hashCode() {
        int prime = 31;
        return prime + (personalData == null ? 0 : personalData.hashCode());
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Employee other = (Employee) obj;
        if (personalData == null) {
            if (other.personalData != null) {
                return false;
            }
        } else if (!personalData.equals(other.personalData)) {
            return false;
        }
        return true;
    }
}
