package ar.edu.unq.dopplereffect.presentation.project;

import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.unq.dopplereffect.presentation.search.Search;
import ar.edu.unq.dopplereffect.project.Skill;
import ar.edu.unq.dopplereffect.service.Service;

public class SearchSkill extends Search<Skill> {
    private static final long serialVersionUID = 1L;

    @SpringBean(name = "servieImpl")
    private Service<Skill> service;

    private String name = "";

    public SearchSkill() {
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
    public void setService(final Service<Skill> service) {
        this.service = service;
    }

    @Override
    public Service<Skill> getService() {
        return service;
    }

}
