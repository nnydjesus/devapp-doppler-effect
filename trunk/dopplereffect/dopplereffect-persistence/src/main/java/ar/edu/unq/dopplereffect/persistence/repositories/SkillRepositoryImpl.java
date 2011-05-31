package ar.edu.unq.dopplereffect.persistence.repositories;

import ar.edu.unq.dopplereffect.project.Skill;

public class SkillRepositoryImpl extends HibernatePersistentRepository<Skill> {

    private static final long serialVersionUID = 1L;

    public SkillRepositoryImpl() {
        super(Skill.class);
    }

}