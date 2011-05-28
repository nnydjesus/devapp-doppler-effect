package ar.edu.unq.dopplereffect.persistence.repositories;

import ar.edu.unq.dopplereffect.project.Project;

public class ProjectRepositoryImpl extends HibernatePersistentRepository<Project> {

    private static final long serialVersionUID = 1L;

    public ProjectRepositoryImpl() {
        super(Project.class);
    }

}