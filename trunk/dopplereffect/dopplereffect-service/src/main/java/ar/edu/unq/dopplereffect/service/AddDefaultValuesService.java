package ar.edu.unq.dopplereffect.service;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import ar.edu.unq.dopplereffect.builders.employees.EmployeeBuilder;
import ar.edu.unq.dopplereffect.employees.CareerPlan;
import ar.edu.unq.dopplereffect.employees.CareerPlanLevel;
import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.leaverequests.LeaveRequest;
import ar.edu.unq.dopplereffect.leaverequests.LeaveRequestCustomType;
import ar.edu.unq.dopplereffect.persistence.repositories.CareerPlanLevelRepositoryImpl;
import ar.edu.unq.dopplereffect.persistence.repositories.EmployeeRepositoryImpl;
import ar.edu.unq.dopplereffect.persistence.repositories.LeaveRequestRepositoryImpl;
import ar.edu.unq.dopplereffect.persistence.repositories.LeaveRequestTypeRepositoryImpl;
import ar.edu.unq.dopplereffect.persistence.repositories.ProjectRepositoryImpl;
import ar.edu.unq.dopplereffect.time.IntervalDurationStrategy;
import ar.edu.unq.dopplereffect.time.OneDayDurationStrategy;

@Service
public class AddDefaultValuesService {

    // @formatter:off
    private static final LeaveRequestCustomType
        MUDANZA_LEAVE_REQ_TYPE = new LeaveRequestCustomType("Mudanza", 10, 1, 2),
        MATERNIDAD_LEAVE_REQ_TYPE = new LeaveRequestCustomType("Maternidad", 90, 15, 90),
        EXAMEN_LEAVE_REQ_TYPE = new LeaveRequestCustomType("Examen", 12, 0, 2);

    private static final CareerPlanLevel 
        TRAINEE = new CareerPlanLevel("Trainee"),
        JUNIOR = new CareerPlanLevel("Junior"),
        SEMI_SENIOR = new CareerPlanLevel("Semi senior"),
        SENIOR = new CareerPlanLevel("Senior");

    private static final Employee
        EMPLOYEE_1 = new EmployeeBuilder().withFirstName("nombre1").withLastName("apellido1")
            .withDNI(12222222).withJoinDate(new DateTime("2011-03-01")).withCareerPlan(CareerPlan.TESTER)
            .withCareerPlanLevel(TRAINEE).build(),
        EMPLOYEE_2 = new EmployeeBuilder().withFirstName("nombre2").withLastName("apellido2")
            .withDNI(22222222).withJoinDate(new DateTime("2011-02-21")).withCareerPlan(CareerPlan.TECHNICIAN)
            .withCareerPlanLevel(SENIOR).build(),
        EMPLOYEE_3 = new EmployeeBuilder().withFirstName("nombre3").withLastName("apellido3")
            .withDNI(32222222).withJoinDate(new DateTime("2010-12-20")).withCareerPlan(CareerPlan.TECHNICIAN)
            .withCareerPlanLevel(SEMI_SENIOR).build(),
        EMPLOYEE_4 = new EmployeeBuilder().withFirstName("nombre4").withLastName("apellido4")
            .withDNI(42222222).withJoinDate(new DateTime("2011-06-02")).withCareerPlan(CareerPlan.FUNCTIONAL)
            .withCareerPlanLevel(SENIOR).build(),
        EMPLOYEE_5 = new EmployeeBuilder().withFirstName("nombre5").withLastName("apellido5")
            .withDNI(52222222).withJoinDate(new DateTime("2011-05-29")).withCareerPlan(CareerPlan.DESIGNER)
            .withCareerPlanLevel(JUNIOR).build();
    // @formatter:on

    private EmployeeRepositoryImpl employeeRepo;

    private CareerPlanLevelRepositoryImpl careerPlanLevelRepo;

    private ProjectRepositoryImpl projectRepo;

    private LeaveRequestRepositoryImpl leaveReqRepo;

    private LeaveRequestTypeRepositoryImpl leaveReqTypeRepo;

    public void addAllData() {
        this.addCareerPlanLevels();
        this.addEmployees();
        this.addLeaveRequestTypes();
        this.addLeaveRequests();
        this.addProjects();
    }

    public void addCareerPlanLevels() {
        careerPlanLevelRepo.saveAll(TRAINEE, JUNIOR, SEMI_SENIOR, SENIOR);
    }

    public void addEmployees() {
        employeeRepo.saveAll(EMPLOYEE_1, EMPLOYEE_2, EMPLOYEE_3, EMPLOYEE_4, EMPLOYEE_5);
    }

    private void addProjects() {
        // TODO hacer
    }

    private void addLeaveRequestTypes() {
        this.getLeaveReqTypeRepo().saveAll(EXAMEN_LEAVE_REQ_TYPE, MATERNIDAD_LEAVE_REQ_TYPE, MUDANZA_LEAVE_REQ_TYPE);
    }

    private void addLeaveRequests() {
        EMPLOYEE_1.addAssignment(new LeaveRequest(EXAMEN_LEAVE_REQ_TYPE, new OneDayDurationStrategy(new DateTime(
                "2011-06-03"))));
        EMPLOYEE_2.addAssignment(new LeaveRequest(EXAMEN_LEAVE_REQ_TYPE, new OneDayDurationStrategy(new DateTime(
                "2011-04-22"))));
        EMPLOYEE_2.addAssignment(new LeaveRequest(MUDANZA_LEAVE_REQ_TYPE, new IntervalDurationStrategy(new DateTime(
                "2011-06-03"), new DateTime("2011-06-04"))));
        EMPLOYEE_3.addAssignment(new LeaveRequest(EXAMEN_LEAVE_REQ_TYPE, new OneDayDurationStrategy(new DateTime(
                "2011-05-15"))));
        EMPLOYEE_3.addAssignment(new LeaveRequest(EXAMEN_LEAVE_REQ_TYPE, new OneDayDurationStrategy(new DateTime(
                "2011-06-18"))));
        EMPLOYEE_4.addAssignment(new LeaveRequest(MATERNIDAD_LEAVE_REQ_TYPE, new IntervalDurationStrategy(new DateTime(
                "2011-06-03"), new DateTime("2011-09-02"))));
        this.getEmployeeRepo().update(EMPLOYEE_1);
        this.getEmployeeRepo().update(EMPLOYEE_2);
        this.getEmployeeRepo().update(EMPLOYEE_3);
        this.getEmployeeRepo().update(EMPLOYEE_4);
    }

    public EmployeeRepositoryImpl getEmployeeRepo() {
        return employeeRepo;
    }

    public void setEmployeeRepo(final EmployeeRepositoryImpl employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public CareerPlanLevelRepositoryImpl getCareerPlanLevelRepo() {
        return careerPlanLevelRepo;
    }

    public void setCareerPlanLevelRepo(final CareerPlanLevelRepositoryImpl careerPlanLevelRepo) {
        this.careerPlanLevelRepo = careerPlanLevelRepo;
    }

    public ProjectRepositoryImpl getProjectRepo() {
        return projectRepo;
    }

    public void setProjectRepo(final ProjectRepositoryImpl projectRepo) {
        this.projectRepo = projectRepo;
    }

    public LeaveRequestRepositoryImpl getLeaveReqRepo() {
        return leaveReqRepo;
    }

    public void setLeaveReqRepo(final LeaveRequestRepositoryImpl leaveReqRepo) {
        this.leaveReqRepo = leaveReqRepo;
    }

    public LeaveRequestTypeRepositoryImpl getLeaveReqTypeRepo() {
        return leaveReqTypeRepo;
    }

    public void setLeaveReqTypeRepo(final LeaveRequestTypeRepositoryImpl leaveReqTypeRepo) {
        this.leaveReqTypeRepo = leaveReqTypeRepo;
    }
}
