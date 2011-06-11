package ar.edu.unq.dopplereffect.persistence.project;

import ar.edu.unq.dopplereffect.persistence.HibernatePersistentRepository;
import ar.edu.unq.dopplereffect.projects.Project;

public class ProjectRepositoryImpl extends HibernatePersistentRepository<Project> {

    private static final long serialVersionUID = 1L;

    public ProjectRepositoryImpl() {
        super(Project.class);
    }

}