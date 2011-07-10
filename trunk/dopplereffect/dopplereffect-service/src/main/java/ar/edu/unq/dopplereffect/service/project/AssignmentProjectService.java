package ar.edu.unq.dopplereffect.service.project;

import java.util.List;

import ar.edu.unq.dopplereffect.service.Service;
import ar.edu.unq.dopplereffect.service.employee.EmployeeViewDTO;
import ar.edu.unq.dopplereffect.service.employee.IEmployeeDTO;
import ar.edu.unq.dopplereffect.time.IntervalDurationStrategy;

public interface AssignmentProjectService extends Service {

    List<ProjectAssignmentDTO> searchAllAssignmentProjects();

    List<ProjectAssignmentDTO> searchByEmployee(IEmployeeDTO employeeDTO);

    void newProject(ProjectAssignmentDTO entity);

    void deleteProject(ProjectAssignmentDTO entity);

    void updateProject(ProjectAssignmentDTO entity);

    ProjectAssignmentDTO assignmentEmployee(ProjectDTO projectDTO, EmployeeViewDTO employeeViewDTO,
            IntervalDurationStrategy intervalDurationStrategy);

}
