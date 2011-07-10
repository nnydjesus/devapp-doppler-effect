package ar.edu.unq.dopplereffect.service.project;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.dopplereffect.log.NotLoggable;
import ar.edu.unq.dopplereffect.persistence.project.AssignmentRepositoryImpl;
import ar.edu.unq.dopplereffect.projects.Project;
import ar.edu.unq.dopplereffect.projects.ProjectAssignment;
import ar.edu.unq.dopplereffect.service.employee.EmployeeServiceImpl;
import ar.edu.unq.dopplereffect.service.employee.EmployeeViewDTO;
import ar.edu.unq.dopplereffect.service.employee.IEmployeeDTO;
import ar.edu.unq.dopplereffect.time.IntervalDurationStrategy;

/**
 */
public class AssignmentProjectServiceImpl implements AssignmentProjectService {

    private static final long serialVersionUID = 1L;

    private AssignmentRepositoryImpl assignmentRepository;

    private EmployeeServiceImpl employeeService;

    private ProjectServiceImpl projectService;

    @Override
    @Transactional
    public List<ProjectAssignmentDTO> searchAllAssignmentProjects() {
        return this.convertAllProjectAssignment(assignmentRepository.searchAll());
    }

    @NotLoggable
    private List<ProjectAssignmentDTO> convertAllProjectAssignment(final List<ProjectAssignment> projectAssignments) {
        List<ProjectAssignmentDTO> results = new ArrayList<ProjectAssignmentDTO>();
        for (ProjectAssignment projectAssignment : projectAssignments) {
            results.add(this.convertProjectAssignment(projectAssignment));
        }
        return results;
    }

    @NotLoggable
    protected ProjectAssignmentDTO convertProjectAssignment(final ProjectAssignment projectAssignment) {
        return new ProjectAssignmentDTO(this.getEmployeeService().convert(projectAssignment.getEmployee()), this
                .getProjectService().convert(projectAssignment.getProject()), projectAssignment.getIntervals());
    }

    @Override
    @Transactional
    public List<ProjectAssignmentDTO> searchByEmployee(final IEmployeeDTO employeeDTO) {
        return this.convertAllProjectAssignment(this.getAssignmentRepository().searchByEmployee(
                this.getEmployeeService().getEmployeeByDTO(employeeDTO)));
    }

    @Override
    @Transactional
    public ProjectAssignmentDTO assignmentEmployee(final ProjectDTO projectDTO, final EmployeeViewDTO employeeViewDTO,
            final IntervalDurationStrategy intervalDurationStrategy) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Transactional
    public List<ProjectAssignmentDTO> automaticRecommendation(final ProjectDTO projectDTO, final DateTime from) {
        Project project = projectService.getProjectByDTO(projectDTO);
        project.automaticAssignment(this.getEmployeeService().searchEmployees(), from);
        return this.convertAllProjectAssignment(new ArrayList<ProjectAssignment>(project.getAssignedEmployees()));

    }

    @Override
    @Transactional
    public List<ProjectDTO> searchAllProjects() {
        return this.getProjectService().searchAllProjects();
    }

    @Override
    @Transactional
    public void automaticAssignment(final ProjectDTO projectDTO, final DateTime from) {
        Project project = projectService.getProjectByDTO(projectDTO);
        project.automaticAssignment(this.getEmployeeService().searchEmployees(), from);
        this.getProjectService().updateProject(project);
    }

    public void setAssignmentRepository(final AssignmentRepositoryImpl assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    public AssignmentRepositoryImpl getAssignmentRepository() {
        return assignmentRepository;
    }

    public void setEmployeeService(final EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    public EmployeeServiceImpl getEmployeeService() {
        return employeeService;
    }

    public void setProjectService(final ProjectServiceImpl projectService) {
        this.projectService = projectService;
    }

    public ProjectServiceImpl getProjectService() {
        return projectService;
    }

    @Override
    public ProjectAssignmentDTO getProjectAssignmentDTO(final ProjectAssignment assignment) {
        ProjectAssignmentDTO projectAssignmentDTO = new ProjectAssignmentDTO();
        projectAssignmentDTO.setIntervals(assignment.getIntervals());
        projectAssignmentDTO.setEmployeeDTO(this.getEmployeeService().convert(assignment.getEmployee()));
        projectAssignmentDTO.setProjectDTO(this.getProjectService().convert(assignment.getProject()));
        return projectAssignmentDTO;
    }

}
