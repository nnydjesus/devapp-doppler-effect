package ar.edu.unq.dopplereffect.service.employee;

import java.util.Date;

import ar.edu.unq.dopplereffect.employees.CareerPlan;
import ar.edu.unq.dopplereffect.service.DTO;

public class EmployeeDTO implements DTO {

    private static final long serialVersionUID = -8217656824150568037L;

    private String firstName;

    private String lastName;

    private int dni;

    private String phoneNumber;

    private String email;

    private String addressStreet;

    private int addressNumber;

    private String addressCity;

    private Date joinDate;

    private CareerPlan careerPlan;

    private String careerPlanLevel;

    private int percentage;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(final int dni) {
        this.dni = dni;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(final String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public int getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(final int addressNumber) {
        this.addressNumber = addressNumber;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(final String addressCity) {
        this.addressCity = addressCity;
    }

    public Date getJoinDate() {
        return (Date) joinDate.clone();
    }

    public void setJoinDate(final Date joinDate) {
        this.joinDate = (Date) joinDate.clone();
    }

    public CareerPlan getCareerPlan() {
        return careerPlan;
    }

    public void setCareerPlan(final CareerPlan careerPlan) {
        this.careerPlan = careerPlan;
    }

    public String getCareerPlanLevel() {
        return careerPlanLevel;
    }

    public void setCareerPlanLevel(final String careerPlanLevel) {
        this.careerPlanLevel = careerPlanLevel;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(final int percentage) {
        this.percentage = percentage;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }
}
