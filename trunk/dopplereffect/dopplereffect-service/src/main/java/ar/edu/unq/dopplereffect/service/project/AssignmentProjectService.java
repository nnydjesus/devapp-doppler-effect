package ar.edu.unq.dopplereffect.service.project;

import java.util.List;

import org.joda.time.DateTime;

import ar.edu.unq.dopplereffect.projects.ProjectAssignment;
import ar.edu.unq.dopplereffect.service.Service;
import ar.edu.unq.dopplereffect.service.employee.EmployeeViewDTO;
import ar.edu.unq.dopplereffect.service.employee.IEmployeeDTO;
import ar.edu.unq.dopplereffect.time.IntervalDurationStrategy;

public interface AssignmentProjectService extends Service {

    List<ProjectAssignmentDTO> searchAllAssignmentProjects();
    
    void deleteAssignmentProject(ProjectAssignment assignment);

    List<ProjectAssignmentDTO> searchByEmployee(IEmployeeDTO employeeDTO);

    ProjectAssignmentDTO assignmentEmployee(ProjectDTO projectDTO, EmployeeViewDTO employeeViewDTO,
            IntervalDurationStrategy intervalDurationStrategy);

    List<ProjectAssignmentDTO> automaticRecommendation(ProjectDTO projectDTO, DateTime from);

    List<ProjectDTO> searchAllProjects();

    void automaticAssignment(ProjectDTO project, DateTime from);

    ProjectAssignmentDTO getProjectAssignmentDTO(ProjectAssignment assignment);

}
