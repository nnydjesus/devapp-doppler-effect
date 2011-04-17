package ar.edu.unq.dopplereffect.bean;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;

import ar.edu.unq.dopplereffect.bean.enums.CareerPlan;

/**
 * Persona que trabaja en la empresa. Un empleado posee datos personales, como
 * por ejemplo su nombre y apellido, pero tambien posee datos relacionados al
 * trabajo, como su plan de carrera.
 */
public class Employee {

    /* ************************ INSTANCE VARIABLES ************************ */

    private EmployeeData personalData;

    private CareerData careerData;

    private Set<LeaveRequest> leaveRequests;

    // private Set<Assignable> assignment;

    /* *************************** CONSTRUCTORS *************************** */

    public Employee() {
        this(new EmployeeData(), new CareerData());
        leaveRequests = new HashSet<LeaveRequest>();
    }

    public Employee(final EmployeeData personalData, final CareerData careerData) {
        this.personalData = personalData;
        this.careerData = careerData;
    }

    /* **************************** ACCESSORS ***************************** */

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

    public CareerPlan getCareerPlan() {
        return this.getCareerData().getCareerPlan();
    }

    public CareerData getCareerData() {
        return careerData;
    }

    public void setCareerData(final CareerData careerData) {
        this.careerData = careerData;
    }

    public EmployeeData getPersonalData() {
        return personalData;
    }

    public void setPersonalData(final EmployeeData personalData) {
        this.personalData = personalData;
    }

    public int getPercentage() {
        return this.getCareerData().getPercentage();
    }

    public void setPercentage(final int percentage) {
        this.getCareerData().setPercentage(percentage);
    }

    public Set<LeaveRequest> getLeaveRequests() {
        return leaveRequests;
    }

    public void setLeaveRequests(final Set<LeaveRequest> leaveRequests) {
        this.leaveRequests = leaveRequests;
    }

    // public void setAssignment(final Set<Assignable> assignment) {
    // this.assignment = assignment;
    // }
    //
    // public Set<Assignable> getAssignment() {
    // return assignment;
    // }

    /* **************************** OPERATIONS **************************** */

    /**
     * Cambia su porcentaje de sueldo acorde al cambio en la banda de sueldo,
     * que se recibe como parametro. Pasa al porcentaje inmediato siguiente al
     * que tiene, de esta manera se asegura de no cobrar menos de lo que cobraba
     * antes.
     * 
     * @param percentages
     * 
     */
    public void changeSalaryPercentage(final List<Integer> percentages) {
        Collections.sort(percentages);
        for (int perc : percentages) {
            if (perc >= this.getPercentage()) {
                this.setPercentage(perc);
                return;
            }
        }
    }

    /**
     * Agrega una licencia al empleado.
     * 
     * @param leaveReq
     *            la licencia a agregar.
     */
    public void addLeaveRequest(final LeaveRequest leaveReq) {
        this.getLeaveRequests().add(leaveReq);
    }

    /**
     * Agrega una asignacion
     * 
     * @param assignable
     *            Asignacion a agregar.
     */
    // public void addAssignment(final Assignable assignable) {
    // this.getAssignment().add(assignable);
    // }

    /**
     * Calcula la cantidad de dias que el empleado pidio en un año, para un
     * determinado tipo de licencia.
     * 
     * @param leaveRequestType
     *            el tipo de licencia por el que se desea buscar.
     * @param year
     *            el año por el que se desea averiguar.
     * @return la cantidad de dias que el empleado pidio hasta el momento.
     */
    public int daysRequestedInYear(final LeaveRequestType leaveRequestType, final int year) {
        int days = 0;
        for (LeaveRequest leaveRequest : this.getLeaveRequests()) {
            if (leaveRequest.getType().equals(leaveRequestType) && year == leaveRequest.getStartDate().getYear()) {
                // TODO no se banca pedir una licencia en un año y terminarla en
                // otro
                days += leaveRequest.getAmountOfDays();
            }
        }
        return days;
    }

    /**
     * Verifica si el empleado tiene una licencia en la fecha dada.
     * 
     * @param date
     *            la fecha a verificar
     * @return <code>true</code> si tiene una licencia en la fecha dada,
     *         <code>false</code> en caso contrario.
     */
    public boolean hasLeaveRequestInDay(final DateTime date) {
        for (LeaveRequest req : this.getLeaveRequests()) {
            if (req.includesDay(date))
                return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        return prime + (this.getPersonalData() == null ? 0 : this.getPersonalData().hashCode());
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;
        Employee other = (Employee) obj;
        if (this.getPersonalData() == null) {
            if (other.getPersonalData() != null)
                return false;
        } else if (!this.getPersonalData().equals(other.getPersonalData()))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "employee";
    }

}
