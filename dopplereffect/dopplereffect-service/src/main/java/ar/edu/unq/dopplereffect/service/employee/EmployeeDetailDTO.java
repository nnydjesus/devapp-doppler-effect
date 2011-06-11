package ar.edu.unq.dopplereffect.service.employee;

import java.util.Date;

import ar.edu.unq.dopplereffect.service.DTO;

public class EmployeeDetailDTO implements DTO {

    private static final long serialVersionUID = -8470686184354833725L;

    private String firstName;

    private String lastName;

    private int dni;

    private String phoneNumber;

    private String address;

    private String email;

    private Date joinDate;

    private String careerPlan;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(final Date joinDate) {
        this.joinDate = joinDate;
    }

    public String getCareerPlan() {
        return careerPlan;
    }

    public void setCareerPlan(final String careerPlan) {
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

}
