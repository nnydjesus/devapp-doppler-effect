package ar.edu.unq.dopplereffect.builders.projects;

import ar.edu.unq.dopplereffect.builders.Builder;
import ar.edu.unq.dopplereffect.projects.Skill;
import ar.edu.unq.dopplereffect.projects.SkillLevel;

public class SkillBuilder implements Builder<Skill> {

    protected transient String type = "MySkill";

    protected transient SkillLevel level = SkillLevel.MEDIUM;

    public SkillBuilder withType(final String theType) {
        type = theType;
        return this;
    }

    public SkillBuilder withLevel(final SkillLevel theLevel) {
        level = theLevel;
        return this;
    }

    @Override
    public Skill build() {
        return new Skill(type, level);
    }
}
