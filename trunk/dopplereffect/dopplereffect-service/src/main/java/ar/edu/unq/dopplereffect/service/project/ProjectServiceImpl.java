package ar.edu.unq.dopplereffect.service.project;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.log.NotLoggable;
import ar.edu.unq.dopplereffect.persistence.project.ProjectRepositoryImpl;
import ar.edu.unq.dopplereffect.projects.Project;
import ar.edu.unq.dopplereffect.projects.ProjectAssignment;
import ar.edu.unq.dopplereffect.projects.Skill;
import ar.edu.unq.dopplereffect.service.employee.EmployeeServiceImpl;
import ar.edu.unq.dopplereffect.service.employee.EmployeeViewDTO;
import ar.edu.unq.dopplereffect.service.export.ExportService;
import ar.edu.unq.dopplereffect.time.IntervalDurationStrategy;

@Service
public class ProjectServiceImpl implements ProjectService {

    private static final long serialVersionUID = -8603838797683404612L;

    private ProjectRepositoryImpl projectRepo;

    private SkillServiceImpl skillService;

    private EmployeeServiceImpl employeeService;

    private ExportService<Project> exportService;

    @Override
    @Transactional
    public List<ProjectDTO> searchAllProjects() {
        // throw new FatalException("ASDFasdfA SdfASDfAsd fasDFsadFasdFsadfsd");
        return this.convertAll(this.getProjectRepo().searchAll());
    }

    @NotLoggable
    private List<ProjectDTO> convertAll(final List<Project> projects) {
        List<ProjectDTO> results = new LinkedList<ProjectDTO>();
        for (Project p : projects) {
            results.add(this.convert(p));
        }
        return results;
    }

    @NotLoggable
    public ProjectDTO convert(final Project project) {
        ProjectDTO pDTO = new ProjectDTO();
        pDTO.setName(project.getName());
        pDTO.setClientName(project.getClientData().getFirstName());
        pDTO.setTimeProject(project.getTimeProyect());
        pDTO.setMaxEffort(project.getMaxEffort());
        pDTO.setSkills(this.getSkillService().convertAll(project.getSkills()));
        List<ProjectAssignmentDTO> projectAssignment = this.convertAllProjectAssignment(project.getAssignedEmployees(),
                pDTO);
        pDTO.setAssignment(projectAssignment);
        return pDTO;
    }

    @NotLoggable
    private List<ProjectAssignmentDTO> convertAllProjectAssignment(final Set<ProjectAssignment> projectAssignments,
            final ProjectDTO pDTO) {
        List<ProjectAssignmentDTO> results = new ArrayList<ProjectAssignmentDTO>();
        for (ProjectAssignment projectAssignment : projectAssignments) {
            results.add(this.convertProjectAssignment(projectAssignment, pDTO));
        }
        return results;
    }

    @NotLoggable
    protected ProjectAssignmentDTO convertProjectAssignment(final ProjectAssignment projectAssignment,
            final ProjectDTO pDTO) {
        return new ProjectAssignmentDTO(this.getEmployeeService().convert(projectAssignment.getEmployee()), pDTO,
                projectAssignment.getIntervals());
    }

    @Override
    @Transactional
    public void newProject(final ProjectDTO entity) {
        Project project = new Project();
        project.setName(entity.getName());
        project.setMaxEffort(entity.getMaxEffort());
        project.setTimeProyect(entity.getTimeProject());
        Set<Skill> skills = new HashSet<Skill>();
        for (SkillDTO skDTO : entity.getSkills()) {
            skills.add(this.getSkillService().getSkillRepo().findOrCreate(skDTO.getName(), skDTO.getLevel()));
        }
        project.setSkills(skills);
        this.getProjectRepo().save(project);
    }

    @Override
    @Transactional
    public void deleteProject(final ProjectDTO entity) {
        this.getProjectRepo().delete(this.getProjectByDTO(entity));
    }

    @Transactional
    public Project getProjectByDTO(final ProjectDTO dto) {
        return this.getProjectRepo().getByName(dto.getName());
    }

    @Transactional
    public void updateProject(final Project project) {
        this.getProjectRepo().update(project);
    }

    @Override
    @Transactional
    public void updateProject(final ProjectDTO entity) {
        Project project = this.getProjectByDTO(entity);
        project.setName(entity.getName());
        project.setMaxEffort(entity.getMaxEffort());
        Set<Skill> skills = new HashSet<Skill>();
        for (SkillDTO skDTO : entity.getSkills()) {
            skills.add(this.getSkillService().getSkillRepo().findOrCreate(skDTO.getName(), skDTO.getLevel()));
        }
        project.setSkills(skills);
        this.getProjectRepo().update(project);
    }

    @Override
    @Transactional
    public List<ProjectDTO> searchByNameProjects(final String name) {
        return this.convertAll(this.getProjectRepo().searchByName(name));
    }

    @Override
    @Transactional
    public ProjectAssignmentDTO assignmentEmployee(final ProjectDTO projectDTO, final EmployeeViewDTO employeeViewDTO,
            final IntervalDurationStrategy intervalDurationStrategy) {
        Project project = this.getProjectByDTO(projectDTO);
        Employee employee = this.getEmployeeService().getEmployeeByDTO(employeeViewDTO);
        ProjectAssignment assignment = project.manualAssignment(employee, intervalDurationStrategy);
        employeeViewDTO.getAssignments().add(assignment);
        ProjectAssignmentDTO projectAssignmentDTO = new ProjectAssignmentDTO(employeeViewDTO, this.convert(assignment
                .getProject()), assignment.getIntervals());
        projectDTO.getAssignment().add(projectAssignmentDTO);
        return projectAssignmentDTO;

    }

    @Override
    @Transactional
    public File export(final String pathFile) {
        HashMap<String, String> parameters = new HashMap<String, String>();
        parameters.put("Title", "Projects");
        return this.getExportService().export(pathFile, this.getProjectRepo().searchAll(), parameters);
    }

    public void setEmployeeService(final EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    public EmployeeServiceImpl getEmployeeService() {
        return employeeService;
    }

    public ProjectRepositoryImpl getProjectRepo() {
        return projectRepo;
    }

    public void setProjectRepo(final ProjectRepositoryImpl projectRepo) {
        this.projectRepo = projectRepo;
    }

    public SkillServiceImpl getSkillService() {
        return skillService;
    }

    public void setSkillService(final SkillServiceImpl skillService) {
        this.skillService = skillService;
    }

    public void setExportService(final ExportService<Project> exportService) {
        this.exportService = exportService;
    }

    public ExportService<Project> getExportService() {
        return exportService;
    }

}
