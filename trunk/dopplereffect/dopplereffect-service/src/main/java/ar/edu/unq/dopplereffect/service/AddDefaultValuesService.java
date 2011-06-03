package ar.edu.unq.dopplereffect.service;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import ar.edu.unq.dopplereffect.builders.employees.EmployeeBuilder;
import ar.edu.unq.dopplereffect.employees.CareerPlan;
import ar.edu.unq.dopplereffect.employees.CareerPlanLevel;
import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.persistence.repositories.CareerPlanLevelRepositoryImpl;
import ar.edu.unq.dopplereffect.persistence.repositories.EmployeeRepositoryImpl;
import ar.edu.unq.dopplereffect.persistence.repositories.ProjectRepositoryImpl;

@Service
public class AddDefaultValuesService {

    // @formatter:off
    private static final CareerPlanLevel 
        TRAINEE = new CareerPlanLevel("Trainee"),
        JUNIOR = new CareerPlanLevel("Junior"),
        SEMI_SENIOR = new CareerPlanLevel("Semi senior"),
        SENIOR = new CareerPlanLevel("Senior");
    // @formatter:on

    private EmployeeRepositoryImpl employeeRepo;

    private CareerPlanLevelRepositoryImpl careerPlanLevelRepo;

    private ProjectRepositoryImpl projectRepo;

    public void addAllData() {
        this.addCareerPlanLevels();
        this.addEmployees();
        this.addLeaveRequestTypes();
        this.addLeaveRequests();
        this.addProjects();
    }

    public void addCareerPlanLevels() {
        // @formatter:off
        careerPlanLevelRepo.saveAll(
                TRAINEE,
                JUNIOR,
                SEMI_SENIOR,
                SENIOR
        );
        // @formatter:on
    }

    public void addEmployees() {
        // @formatter:off
        Employee 
            emp1 = new EmployeeBuilder()
                .withFirstName("nombre1")
                .withLastName("apellido1")
                .withDNI(12222222)
                .withJoinDate(new DateTime("2011-03-01"))
                .withCareerPlan(CareerPlan.TESTER)
                .withCareerPlanLevel(TRAINEE)
                .build(),
            emp2 = new EmployeeBuilder()
                .withFirstName("nombre2")
                .withLastName("apellido2")
                .withDNI(22222222)
                .withJoinDate(new DateTime("2011-02-21"))
                .withCareerPlan(CareerPlan.TECHNICIAN)
                .withCareerPlanLevel(SENIOR)
                .build(),
            emp3 = new EmployeeBuilder()
                .withFirstName("nombre3")
                .withLastName("apellido3")
                .withDNI(32222222)
                .withJoinDate(new DateTime("2010-12-20"))
                .withCareerPlan(CareerPlan.TECHNICIAN)
                .withCareerPlanLevel(SEMI_SENIOR)
                .build(),
            emp4 = new EmployeeBuilder()
                .withFirstName("nombre4")
                .withLastName("apellido4")
                .withDNI(42222222)
                .withJoinDate(new DateTime("2011-06-02"))
                .withCareerPlan(CareerPlan.FUNCTIONAL)
                .withCareerPlanLevel(SENIOR)
                .build(),
            emp5 = new EmployeeBuilder()
                .withFirstName("nombre5")
                .withLastName("apellido5")
                .withDNI(52222222)
                .withJoinDate(new DateTime("2011-05-29"))
                .withCareerPlan(CareerPlan.DESIGNER)
                .withCareerPlanLevel(JUNIOR)
                .build();
        employeeRepo.saveAll(emp1, emp2, emp3, emp4, emp5);
        // @formatter:on
    }

    private void addProjects() {
        // TODO hacer
    }

    private void addLeaveRequestTypes() {
        // TODO hacer
    }

    private void addLeaveRequests() {
        // TODO hacer
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
}
