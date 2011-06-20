package ar.edu.unq.dopplereffect.service;

import java.util.Arrays;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import ar.edu.unq.dopplereffect.builders.employees.EmployeeBuilder;
import ar.edu.unq.dopplereffect.builders.leaverequests.HolidayLeaveRequestBuilder;
import ar.edu.unq.dopplereffect.builders.leaverequests.HolidayLeaveRequestTypeBuilder;
import ar.edu.unq.dopplereffect.builders.leaverequests.IntervalDurationStrategyBuilder;
import ar.edu.unq.dopplereffect.builders.leaverequests.LeaveRequestBuilder;
import ar.edu.unq.dopplereffect.builders.leaverequests.LeaveRequestCustomTypeBuilder;
import ar.edu.unq.dopplereffect.builders.leaverequests.OneDayDurationStrategyBuilder;
import ar.edu.unq.dopplereffect.builders.projects.ProjectBuilder;
import ar.edu.unq.dopplereffect.builders.salaries.SalarySpecificationBuilder;
import ar.edu.unq.dopplereffect.data.PersonalData;
import ar.edu.unq.dopplereffect.employees.CareerPlan;
import ar.edu.unq.dopplereffect.employees.CareerPlanLevel;
import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.leaverequests.LeaveRequestCustomType;
import ar.edu.unq.dopplereffect.persistence.employee.CareerPlanLevelRepositoryImpl;
import ar.edu.unq.dopplereffect.persistence.employee.EmployeeRepositoryImpl;
import ar.edu.unq.dopplereffect.persistence.employee.SalarySpecificationRepositoryImpl;
import ar.edu.unq.dopplereffect.persistence.leaverequest.LeaveRequestRepositoryImpl;
import ar.edu.unq.dopplereffect.persistence.leaverequest.LeaveRequestTypeRepositoryImpl;
import ar.edu.unq.dopplereffect.persistence.project.ProjectRepositoryImpl;
import ar.edu.unq.dopplereffect.persistence.project.SkillRepositoryImpl;
import ar.edu.unq.dopplereffect.projects.Project;
import ar.edu.unq.dopplereffect.projects.Skill;
import ar.edu.unq.dopplereffect.projects.SkillLevel;
import ar.edu.unq.tpi.util.common.HashUtils;

@Service
public class AddDefaultValuesService implements ar.edu.unq.dopplereffect.service.Service {

    private static final long serialVersionUID = -5992295762743150896L;

    // @formatter:off
    private static final LeaveRequestCustomType
        MUDANZA_LEAVE_REQ_TYPE = new LeaveRequestCustomTypeBuilder()
            .withReason("Mudanza").withMaxDaysInYear(10).withMinLimit(1).withMaxLimit(2).build(),
        MATERNIDAD_LEAVE_REQ_TYPE = new LeaveRequestCustomTypeBuilder() 
            .withReason("Maternidad").withMaxDaysInYear(90).withMinLimit(15).withMaxLimit(90).build(),
        EXAMEN_LEAVE_REQ_TYPE = new LeaveRequestCustomTypeBuilder() 
            .withReason("Examen").withMaxDaysInYear(12).withMinLimit(1).withMaxLimit(2).build(),
        VACACIONES_LEAVE_REQ_TYPE = new HolidayLeaveRequestTypeBuilder()
            .withInitialCorrespondingDays(7).withCorrespondingDays(1, 15).withCorrespondingDays(5, 21).build();

    private static final CareerPlanLevel 
        TRAINEE = new CareerPlanLevel("Trainee"),
        JUNIOR = new CareerPlanLevel("Junior"),
        SEMI_SENIOR = new CareerPlanLevel("Semi senior"),
        SENIOR = new CareerPlanLevel("Senior");

    private static final Employee
        EMPLOYEE_1 = new EmployeeBuilder().withFirstName("Bombita").withLastName("Rodriguez")
            .withDNI(12222222).withJoinDate(new DateTime("2011-03-01")).withCareerPlan(CareerPlan.TESTER)
            .withCareerPlanLevel(TRAINEE).build(),
        EMPLOYEE_2 = new EmployeeBuilder().withFirstName("Violencia").withLastName("Rivas")
            .withDNI(22222222).withJoinDate(new DateTime("2011-02-21")).withCareerPlan(CareerPlan.TECHNICIAN)
            .withCareerPlanLevel(SENIOR).build(),
        EMPLOYEE_3 = new EmployeeBuilder().withFirstName("Juan Carlos").withLastName("Pelotudo")
            .withDNI(32222222).withJoinDate(new DateTime("2010-12-20")).withCareerPlan(CareerPlan.TECHNICIAN)
            .withCareerPlanLevel(SEMI_SENIOR).build(),
        EMPLOYEE_4 = new EmployeeBuilder().withFirstName("Beto").withLastName("Quantro")
            .withDNI(42222222).withJoinDate(new DateTime("2011-06-02")).withCareerPlan(CareerPlan.FUNCTIONAL)
            .withCareerPlanLevel(SENIOR).build(),
        EMPLOYEE_5 = new EmployeeBuilder().withFirstName("Micky").withLastName("Vainilla")
            .withDNI(52222222).withJoinDate(new DateTime("2011-05-29")).withCareerPlan(CareerPlan.DESIGNER)
            .withCareerPlanLevel(JUNIOR).build();

    private static final Skill 
        JAVA_E = new Skill("JAVA", SkillLevel.EXPERT),
        HIBERNATE_B = new Skill("HIBERNATE", SkillLevel.BEGINNER),
        POO_E = new Skill("POO", SkillLevel.EXPERT),
        AOP_M = new Skill("AOP", SkillLevel.MEDIUM);
    
    private static final Project 
        SERENITO = new ProjectBuilder().withName("Serenito").withSkill(JAVA_E).withInformationClient(new PersonalData("Danone",null)).withEstimatedEffort(12552555).build(),
        CINDOR = new ProjectBuilder().withName("Cindor").withSkill(HIBERNATE_B).withInformationClient(new PersonalData("Danone",null)).withEstimatedEffort(32145555).build(),
        CINE_PLEX = new ProjectBuilder().withName("Cine Plex").withSkill(POO_E).withInformationClient(new PersonalData("De Niro",null)).withEstimatedEffort(545214555).build(),
        SANTANDER = new ProjectBuilder().withName("Santander").withSkill(AOP_M).withInformationClient(new PersonalData("Rio",null)).withEstimatedEffort(5555555).build();
    // @formatter:on

    private EmployeeRepositoryImpl employeeRepo;

    private CareerPlanLevelRepositoryImpl careerPlanLevelRepo;

    private ProjectRepositoryImpl projectRepo;

    private LeaveRequestRepositoryImpl leaveReqRepo;

    private LeaveRequestTypeRepositoryImpl leaveReqTypeRepo;

    private SalarySpecificationRepositoryImpl salarySpecRepo;

    private SkillRepositoryImpl skillRepo;

    private LoginService loginService;

    public void addAllData() {
        this.addCareerPlanLevels();
        this.addEmployees();
        this.addLeaveRequestTypes();
        this.addLeaveRequests();
        this.addProjects();
        this.addSalarySpecs();
        this.addDefaultUser();
    }

    private void addDefaultUser() {
        this.getLoginService().signUp("a", HashUtils.hash("a"));
    }

    private void addSalarySpecs() {
        // @formatter:off
        this.getSalarySpecRepo().saveAll(
           new SalarySpecificationBuilder()
               .withYear(2011).withPlan(CareerPlan.TESTER).withLevel(SEMI_SENIOR)
               .withMinSalary(2000).withMaxSalary(3500)
               .withPercentages(Arrays.asList(0, 50, 100)).build(),
           new SalarySpecificationBuilder()
               .withYear(2011).withPlan(CareerPlan.FUNCTIONAL).withLevel(SENIOR)
               .withMinSalary(4000).withMaxSalary(6500)
               .withPercentages(Arrays.asList(0, 50, 100)).build(),
           new SalarySpecificationBuilder()
               .withYear(2011).withPlan(CareerPlan.DESIGNER).withLevel(JUNIOR)
               .withMinSalary(5500).withMaxSalary(8000)
               .withPercentages(Arrays.asList(0, 50, 100)).build()
        );
        // @formatter:on
    }

    public void addCareerPlanLevels() {
        careerPlanLevelRepo.saveAll(TRAINEE, JUNIOR, SEMI_SENIOR, SENIOR);
    }

    public void addEmployees() {
        employeeRepo.saveAll(EMPLOYEE_1, EMPLOYEE_2, EMPLOYEE_3, EMPLOYEE_4, EMPLOYEE_5);
    }

    public void addSkills() {
        this.getSkillRepo().saveAll(JAVA_E, HIBERNATE_B, POO_E, AOP_M);
    }

    private void addProjects() {
        projectRepo.saveAll(SERENITO, CINDOR, CINE_PLEX, SANTANDER);
    }

    private void addLeaveRequestTypes() {
        this.getLeaveReqTypeRepo().saveAll(EXAMEN_LEAVE_REQ_TYPE, MATERNIDAD_LEAVE_REQ_TYPE, MUDANZA_LEAVE_REQ_TYPE);
    }

    private void addLeaveRequests() {
        // @formatter:off
        EMPLOYEE_1.addAssignment(new LeaveRequestBuilder()
                .withType(EXAMEN_LEAVE_REQ_TYPE)
                .withDurationStrategy(new OneDayDurationStrategyBuilder().withDate(new DateTime("2011-06-03")).build())
                .build());
        
        EMPLOYEE_2.addAssignment(new LeaveRequestBuilder()
                .withType(EXAMEN_LEAVE_REQ_TYPE)
                .withDurationStrategy(new OneDayDurationStrategyBuilder().withDate(new DateTime("2011-04-22")).build())
                .build());
        
        EMPLOYEE_2.addAssignment(new LeaveRequestBuilder()
                .withType(MUDANZA_LEAVE_REQ_TYPE)
                .withDurationStrategy(new IntervalDurationStrategyBuilder()
                    .withStartDate(new DateTime("2011-06-03")).withEndDate(new DateTime("2011-06-04")).build())
                .build());
        
        EMPLOYEE_3.addAssignment(new LeaveRequestBuilder()
                .withType(EXAMEN_LEAVE_REQ_TYPE)
                .withDurationStrategy(new OneDayDurationStrategyBuilder().withDate(new DateTime("2011-06-15")).build())
                .build());
        
        EMPLOYEE_3.addAssignment(new LeaveRequestBuilder()
                .withType(EXAMEN_LEAVE_REQ_TYPE)
                .withDurationStrategy(new OneDayDurationStrategyBuilder().withDate(new DateTime("2011-06-18")).build())
                .build());

        EMPLOYEE_4.addAssignment(new LeaveRequestBuilder()
                .withType(MATERNIDAD_LEAVE_REQ_TYPE).withDurationStrategy(new IntervalDurationStrategyBuilder()
                    .withStartDate(new DateTime("2011-06-05")).withEndDate(new DateTime("2011-09-02")).build())
                .build());
                
        EMPLOYEE_5.addAssignment(new HolidayLeaveRequestBuilder()
                .withType(VACACIONES_LEAVE_REQ_TYPE)
                .withInterval(new DateTime("2011-06-20"), new DateTime("2011-06-26"))
                .build());
        // @formatter:on

        this.getEmployeeRepo().update(EMPLOYEE_1);
        this.getEmployeeRepo().update(EMPLOYEE_2);
        this.getEmployeeRepo().update(EMPLOYEE_3);
        this.getEmployeeRepo().update(EMPLOYEE_4);
        this.getEmployeeRepo().update(EMPLOYEE_5);
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

    public SkillRepositoryImpl getSkillRepo() {
        return skillRepo;
    }

    public void setSkillRepo(final SkillRepositoryImpl skillRepo) {
        this.skillRepo = skillRepo;
    }

    public SalarySpecificationRepositoryImpl getSalarySpecRepo() {
        return salarySpecRepo;
    }

    public void setSalarySpecRepo(final SalarySpecificationRepositoryImpl salarySpecRepo) {
        this.salarySpecRepo = salarySpecRepo;
    }

    public LoginService getLoginService() {
        return loginService;
    }

    public void setLoginService(final LoginService loginService) {
        this.loginService = loginService;
    }
}
