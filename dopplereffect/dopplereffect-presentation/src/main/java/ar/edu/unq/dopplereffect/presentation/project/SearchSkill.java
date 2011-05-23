package ar.edu.unq.dopplereffect.presentation.project;

import ar.edu.unq.dopplereffect.presentation.search.Search;
import ar.edu.unq.dopplereffect.project.Skill;
import ar.edu.unq.dopplereffect.project.SkillLevel;
import ar.edu.unq.dopplereffect.project.SkillType;

public class SearchSkill extends Search<Skill> {
    private static final long serialVersionUID = 1L;

    private String name = "";

    public SearchSkill() {
        super(Skill.class);
        this.save(new Skill(new SkillType("Hbernate"), SkillLevel.EXPERT));
        this.save(new Skill(new SkillType("Wicket"), SkillLevel.BEGINNER));
        this.save(new Skill(new SkillType("JPA"), SkillLevel.MEDIUM));
    }

    public String getSearchByName() {
        return name;
    }

    public void setSearchByName(final String aName) {
        name = aName;
    }

}
