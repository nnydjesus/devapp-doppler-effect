package ar.edu.unq.dopplereffect.persistence.repositories;

import ar.edu.unq.dopplereffect.project.Project;

public class ProjectRepositoryImpl extends HibernatePersistentRepository<Project> {

    public ProjectRepositoryImpl() {
        super(Project.class);
    }

}