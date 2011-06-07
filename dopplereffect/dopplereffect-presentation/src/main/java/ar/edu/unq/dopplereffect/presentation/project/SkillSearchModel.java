package ar.edu.unq.dopplereffect.presentation.project;

import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.unq.dopplereffect.presentation.search.SearchModel;
import ar.edu.unq.dopplereffect.projects.Skill;
import ar.edu.unq.dopplereffect.service.PersistenceService;

public class SkillSearchModel extends SearchModel<Skill> {
    private static final long serialVersionUID = 1L;

    @SpringBean(name = "servieImpl")
    private PersistenceService<Skill> service;

    private String name = "";

    public SkillSearchModel() {
        super(Skill.class);
        // this.save(new Skill(new SkillType("Hbernate"), SkillLevel.EXPERT));
        // this.save(new Skill(new SkillType("Wicket"), SkillLevel.BEGINNER));
        // this.save(new Skill(new SkillType("JPA"), SkillLevel.MEDIUM));
    }

    public String getSearchByName() {
        return this.getName();
    }

    public void setSearchByName(final String aName) {
        this.setName(aName);
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void setService(final PersistenceService<Skill> service) {
        this.service = service;
    }

    @Override
    public PersistenceService<Skill> getService() {
        return service;
    }

}
