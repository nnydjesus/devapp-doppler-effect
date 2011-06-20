package ar.edu.unq.dopplereffect.projects;

import java.util.List;

import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.entity.IEntity;
import ar.edu.unq.dopplereffect.time.IntervalDurationStrategy;

public interface IProjectAssignmentStrategy extends IEntity {

    ProjectAssignment manualAssignment(final Project anProject, final Employee employee,
            final IntervalDurationStrategy interval);

    void automaticAssignment(final Project anProject, final List<Employee> employees,
            final IntervalDurationStrategy intervalDurationStrategy);

    Project getProject();

    void setProject(Project project);

}
