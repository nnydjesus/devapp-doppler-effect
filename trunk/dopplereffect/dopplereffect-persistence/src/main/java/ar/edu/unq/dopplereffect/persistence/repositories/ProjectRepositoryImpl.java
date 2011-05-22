package ar.edu.unq.dopplereffect.persistence.repositories;

import java.util.List;

import ar.edu.unq.dopplereffect.project.Project;

public class ProjectRepositoryImpl extends HibernatePersistentRepository<Project> {

    @SuppressWarnings("unchecked")
    @Override
    public Project getByName(final String name) {
        List<Project> list = this.getHibernateTemplate().find("from Project where name=?", name);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}