package ar.edu.unq.dopplereffect.persistence.project;

import java.util.List;

import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.persistence.repositories.HibernatePersistentRepository;
import ar.edu.unq.dopplereffect.projects.ProjectAssignment;

public class AssignmentRepositoryImpl extends HibernatePersistentRepository<ProjectAssignment> {

    private static final long serialVersionUID = 1L;

    public AssignmentRepositoryImpl() {
        super(ProjectAssignment.class);
    }

    public List<ProjectAssignment> searchByEmployee(final Employee employee) {
        throw new UnsupportedOperationException();
    }

}