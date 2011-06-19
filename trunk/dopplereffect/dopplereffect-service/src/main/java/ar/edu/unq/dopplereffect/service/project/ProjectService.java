package ar.edu.unq.dopplereffect.service.project;

import java.util.List;

import ar.edu.unq.dopplereffect.service.Service;
import ar.edu.unq.dopplereffect.service.employee.EmployeeViewDTO;
import ar.edu.unq.dopplereffect.time.IntervalDurationStrategy;

public interface ProjectService extends Service {

    List<ProjectDTO> searchAllProjects();

    List<ProjectDTO> searchByNameProjects(String name);

    void newProject(ProjectDTO entity);

    void deleteProject(ProjectDTO entity);

    void updateProject(ProjectDTO entity);

    ProjectAssignmentDTO assignmentEmployee(ProjectDTO projectDTO, EmployeeViewDTO employeeViewDTO,
            IntervalDurationStrategy intervalDurationStrategy);

}
