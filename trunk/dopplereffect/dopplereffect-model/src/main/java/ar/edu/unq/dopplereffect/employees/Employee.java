package ar.edu.unq.dopplereffect.employees;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Years;

import ar.edu.unq.dopplereffect.assignments.Assignable;
import ar.edu.unq.dopplereffect.leaverequests.LeaveRequest;
import ar.edu.unq.dopplereffect.leaverequests.LeaveRequestType;

/**
 * Persona que trabaja en la empresa. Un empleado posee datos personales, como
 * por ejemplo su nombre y apellido, pero tambien posee datos relacionados al
 * trabajo, como su plan de carrera.
 * 
 * Ademas el empleado tiene asignaciones, ya sean a licencias o a proyectos.
 */
public class Employee {

    /* ************************ INSTANCE VARIABLES ************************ */

    private EmployeeData personalData;

    private CareerData careerData;

    private Set<Assignable> assignments;

    /* *************************** CONSTRUCTORS *************************** */

    public Employee() {
        this(new EmployeeData(), new CareerData());
    }

    public Employee(final EmployeeData personalData, final CareerData careerData) {
        this.personalData = personalData;
        this.careerData = careerData;
        assignments = new HashSet<Assignable>();
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

    public CareerPlan getCareerPlan() {
        return this.getCareerData().getCareerPlan();
    }

    public CareerPlanLevel getLevel() {
        return this.getCareerData().getLevel();
    }

    public int getPercentage() {
        return this.getCareerData().getPercentage();
    }

    public void setPercentage(final int percentage) {
        this.getCareerData().setPercentage(percentage);
    }

    public Set<Assignable> getAssignments() {
        return assignments;
    }

    public void setAssignments(final Set<Assignable> assignments) {
        this.assignments = assignments;
    }

    public Set<LeaveRequest> getLeaveRequests() {
        Set<LeaveRequest> leaveRequests = new HashSet<LeaveRequest>();
        for (Assignable assignable : this.getAssignments()) {
            if (assignable.isLeaveRequest()) {
                leaveRequests.add((LeaveRequest) assignable);
            }
        }
        return leaveRequests;
    }

    public DateTime getJoinDate() {
        return this.getCareerData().getJoinDate();
    }

    public void setJoinDate(final DateTime date) {
        this.getCareerData().setJoinDate(date);
    }

    /* **************************** OPERATIONS **************************** */

    /**
     * Agrega una asignacion.
     * 
     * @param assignable
     *            Asignacion a agregar.
     */
    public void addAssignment(final Assignable assignable) {
        this.getAssignments().add(assignable);
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
     * Calcula la cantidad de dias que el empleado pidio en un año, para un
     * determinado tipo de licencia.
     * 
     * @param type
     *            el tipo de licencia por el que se desea buscar.
     * @param year
     *            el año por el que se desea averiguar.
     * @return la cantidad de dias que el empleado pidio hasta el momento.
     */
    public int daysRequestedInYear(final LeaveRequestType type, final int year) {
        int days = 0;
        for (LeaveRequest leaveReq : this.getLeaveRequests()) {
            if (leaveReq.getType().getReason().equals(type.getReason()) && year == leaveReq.getYear()) {
                days += leaveReq.getAmountOfDays();
            }
        }
        return days;
    }

    /**
     * Verifica si el empleado tiene una licencia en la fecha dada.
     * 
     * @param date
     *            la fecha a verificar.
     * @return <code>true</code> si tiene una licencia en la fecha dada,
     *         <code>false</code> en caso contrario.
     */
    public boolean hasLeaveRequestInDay(final DateTime date) {
        Assignable assignable = this.getAssignableForDay(date);
        return assignable != null && assignable.isLeaveRequest();
    }

    /**
     * Retorna que esta haciendo el empleado en un dia determinado. Puede ser
     * que este de licencia, o este asignado a un proyecto, o bien que no este
     * asignado, en ese caso se retorna null.
     */
    public Assignable getAssignableForDay(final DateTime date) {
        for (Assignable assignable : this.getAssignments()) {
            if (assignable.includesDay(date)) {
                return assignable;
            }
        }
        return null;
    }

    /**
     * Verifica si el empleado esta libre en el intervalo dado.
     * 
     * @param interval
     *            el intervalo que se desea verificar.
     * @return <code>true</code> si en ninguna de las fechas descriptas por el
     *         intervalo el empleado esta asignado, <code>false</code> en caso
     *         contrario.
     */
    public boolean isFreeAtInterval(final Interval interval) {
        for (Assignable assignable : this.getAssignments()) {
            // si alguna asignacion se pisa, entonces no esta libre
            if (assignable.overlapsAssignment(interval)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Retorna la antiguedad del empleado, en cantidad de años.
     */
    public int getSeniority() {
        return Years.yearsBetween(this.getJoinDate(), new DateTime()).getYears();
    }

    @Override
    public String toString() {
        return "employee";
    }

    @Override
    public int hashCode() {
        int prime = 31;
        return prime + (this.getPersonalData() == null ? 0 : this.getPersonalData().hashCode());
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
        if (this.getPersonalData() == null) {
            if (other.getPersonalData() != null) {
                return false;
            }
        } else if (!this.getPersonalData().equals(other.getPersonalData())) {
            return false;
        }
        return true;
    }
}
