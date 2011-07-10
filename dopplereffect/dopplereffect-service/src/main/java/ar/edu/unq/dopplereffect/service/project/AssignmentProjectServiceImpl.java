package ar.edu.unq.dopplereffect.service.project;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.dopplereffect.log.NotLoggable;
import ar.edu.unq.dopplereffect.persistence.project.AssignmentRepositoryImpl;
import ar.edu.unq.dopplereffect.projects.ProjectAssignment;
import ar.edu.unq.dopplereffect.service.employee.EmployeeServiceImpl;
import ar.edu.unq.dopplereffect.service.employee.EmployeeViewDTO;
import ar.edu.unq.dopplereffect.service.employee.IEmployeeDTO;
import ar.edu.unq.dopplereffect.time.IntervalDurationStrategy;

/**
 * TODO: description
 */
public class AssignmentProjectServiceImpl implements AssignmentProjectService {

    private static final long serialVersionUID = 1L;

    private AssignmentRepositoryImpl assignmentRepository;

    private EmployeeServiceImpl employeeService;

    private ProjectServiceImpl projectService;

    @Override
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
    public List<ProjectAssignmentDTO> searchByEmployee(final IEmployeeDTO employeeDTO) {
        return this.convertAllProjectAssignment(this.getAssignmentRepository().searchByEmployee(
                this.getEmployeeService().getEmployeeByDTO(employeeDTO)));
    }

    @Override
    public void newProject(final ProjectAssignmentDTO entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteProject(final ProjectAssignmentDTO entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateProject(final ProjectAssignmentDTO entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ProjectAssignmentDTO assignmentEmployee(final ProjectDTO projectDTO, final EmployeeViewDTO employeeViewDTO,
            final IntervalDurationStrategy intervalDurationStrategy) {
        throw new UnsupportedOperationException();
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

}
