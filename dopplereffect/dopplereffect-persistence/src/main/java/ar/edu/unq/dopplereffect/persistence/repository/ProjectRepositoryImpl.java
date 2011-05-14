package ar.edu.unq.dopplereffect.persistence.repository;

import java.util.List;

import ar.edu.unq.dopplereffect.persistence.repositories.PersistentRepository;
import ar.edu.unq.dopplereffect.project.Project;

@org.springframework.stereotype.Repository("projectRepository")
public class ProjectRepositoryImpl extends PersistentRepository<Project> {

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