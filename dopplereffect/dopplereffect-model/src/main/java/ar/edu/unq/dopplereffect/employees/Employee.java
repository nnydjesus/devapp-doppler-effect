package ar.edu.unq.dopplereffect.employees;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.Years;

import ar.edu.unq.dopplereffect.assignments.Assignable;
import ar.edu.unq.dopplereffect.calendar.Calendareable;
import ar.edu.unq.dopplereffect.entity.Entity;
import ar.edu.unq.dopplereffect.exceptions.UserException;
import ar.edu.unq.dopplereffect.leaverequests.LeaveRequest;
import ar.edu.unq.dopplereffect.projects.Skill;
import ar.edu.unq.dopplereffect.projects.SkillLevel;

/**
 * Persona que trabaja en la empresa. Un empleado posee datos personales, como
 * por ejemplo su nombre y apellido, pero tambien posee datos relacionados al
 * trabajo, como su plan de carrera.
 * 
 * Ademas el empleado tiene asignaciones, ya sean a licencias o a proyectos.
 */
public class Employee extends Entity implements Calendareable {

    private static final long serialVersionUID = 2985643249801148589L;

    /* ************************ INSTANCE VARIABLES ************************ */

    private EmployeeData personalData;

    private CareerData careerData;

    private Set<Assignable> assignments;

    private Set<Skill> skills;

    /* *************************** CONSTRUCTORS *************************** */

    public Employee() {
        this(new EmployeeData(), new CareerData());
    }

    public Employee(final EmployeeData personalData, final CareerData careerData) {
        super();
        this.personalData = personalData;
        this.careerData = careerData;
        assignments = new HashSet<Assignable>();
        skills = new HashSet<Skill>();
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

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(final Set<Skill> skills) {
        this.skills = skills;
    }

    public CareerPlan getCareerPlan() {
        return this.getCareerData().getCareerPlan();
    }

    public CareerPlanLevel getLevel() {
        return this.getCareerData().getLevel();
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
        assignable.setEmployee(this);
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
                this.getCareerData().setPercentage(perc);
                return;
            }
        }
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
    @Override
    public Assignable getAssignableForDay(final DateTime date) {
        for (Assignable assignable : this.getAssignments()) {
            if (assignable.includesDay(date)) {
                return assignable;
            }
        }
        return null;
    }

    /**
     * Retorna la antiguedad del empleado, en cantidad de años.
     */
    public int getSeniority() {
        return Years.yearsBetween(this.getCareerData().getJoinDate(), new DateTime()).getYears();
    }

    /**
     * Cambia de plan de carrera, de nivel y porcentaje. Verifica que el salario
     * nuevo correspondiente no sea menor al que tenia, si eso ocurre entonces
     * se queda con los datos que poseia originalmente.
     */
    public void changeCareerPlan(final EmployeesController employeesController, final CareerPlan careerPlan,
            final CareerPlanLevel level, final int percentage) {
        CareerPlanLevel oldLevel = this.getCareerData().getLevel();
        CareerPlan oldPlan = this.getCareerData().getCareerPlan();
        int oldPercentage = this.getPercentage();
        float oldSalary = employeesController.getSalary(this);
        this.getCareerData().setCareerPlan(careerPlan);
        this.getCareerData().setLevel(level);
        this.getCareerData().setPercentage(percentage);
        float newSalary = employeesController.getSalary(this);
        if (newSalary < oldSalary) {
            this.getCareerData().setCareerPlan(oldPlan);
            this.getCareerData().setLevel(oldLevel);
            this.getCareerData().setPercentage(oldPercentage);
            throw new UserException("el salario nuevo es menor al que tenia anteriormente");
        }
    }

    /**
     * Le agrega un {@link Skill} al empleado. Si un skill de ese tipo ya
     * existe, actualiza el nivel.
     */
    public void addSkill(final Skill skill) {
        for (Skill sk : this.getSkills()) {
            if (sk.getType().equals(skill.getType())) {
                this.getSkills().remove(sk);
                this.getSkills().add(this.createSkill(skill, sk));
                return;
            }
        }
        this.getSkills().add(skill);
    }

    /**
     * Retorna el nivel correspondiente al tipo de skill dado. Retorna
     * <code>null</code> si el empleado no posee ese skill.
     */
    public SkillLevel getLevelOfSkill(final String type) {
        for (Skill skill : this.getSkills()) {
            if (skill.getType().equals(type)) {
                return skill.getLevel();
            }
        }
        return null;
    }

    /**
     * Retorna un porcentaje que indica cuanto satisface el empleado a un
     * conjunto dado de skills.
     */
    public int satisfactionLevelOfSkills(final Set<Skill> skillSet) {
        int result = 0;
        for (Skill skill : skillSet) {
            result += this.skillSatifactionLevel(skill);
        }
        return result / skillSet.size();
    }

    /**
     * Retorna un porcentaje que indica que tanto satisface un empleado a un
     * skill;
     */
    public int skillSatifactionLevel(final Skill skill) {
        for (Skill sk : this.getSkills()) {
            if (sk.getType().equals(skill.getType())) {
                return Math.min(sk.getLevel().satisfactionPercentage(skill.getLevel()), 100);
            }
        }
        return 0;
    }

    /**
     * Retorna <code>true</code> si el empleado tiene algun skill igual o mejor
     * al skill dado como parametro, <code>false</code> en caso contrario.
     */
    public boolean satisfySkill(final Skill skill) {
        for (Skill sk : this.getSkills()) {
            if (sk.betterOrEqual(skill)) {
                return true;
            }
        }
        return false;
    }

    /* ************************* PRIVATE METHODS ************************** */

    private Skill createSkill(final Skill skill, final Skill sk) {
        // lo hice solo para que el PMD no chille
        return new Skill(sk.getType(), skill.getLevel());
    }

    /* ****************** EQUALS, HASHCODE, TOSTRING ********************** */

    @Override
    public String toString() {
        return this.getFirstName() + " " + this.getLastName();
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
