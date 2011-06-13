package ar.edu.unq.dopplereffect.persistence.project;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import ar.edu.unq.dopplereffect.exceptions.UserException;
import ar.edu.unq.dopplereffect.persistence.repositories.HibernatePersistentRepository;
import ar.edu.unq.dopplereffect.projects.Skill;
import ar.edu.unq.dopplereffect.projects.SkillLevel;

public class SkillRepositoryImpl extends HibernatePersistentRepository<Skill> {

    private static final long serialVersionUID = 1L;

    public SkillRepositoryImpl() {
        super(Skill.class);
    }

    @SuppressWarnings("unchecked")
    public Skill findByNameAndLevel(final String name, final SkillLevel level) {
        Criteria criteria = this.getSession().createCriteria(this.getEntityClass());
        criteria.add(Restrictions.eq("type", name));
        criteria.add(Restrictions.eq("level", level));
        List<Skill> results = criteria.list();
        if (results.isEmpty()) {
            throw new UserException("No se pudo encontrar el skill con los datos dados");
        }
        return results.get(0);
    }

    public Skill findOrCreate(final String name, final SkillLevel level) {
        Skill skill = null;
        try {
            skill = this.findByNameAndLevel(name, level);
        } catch (UserException e) {
            skill = new Skill(name, level);
            this.save(skill);
        }
        return skill;
    }
}