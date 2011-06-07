package ar.edu.unq.dopplereffect.skills;

import ar.edu.unq.dopplereffect.projects.Skill;
import ar.edu.unq.dopplereffect.projects.SkillLevel;

public class SkillBuilder {

    private transient Skill skill;

    public SkillBuilder() {
        skill = new Skill();
    }

    public SkillBuilder withType(final String skillType) {
        skill.setType(skillType);
        return this;
    }

    public SkillBuilder withLevel(final SkillLevel level) {
        skill.setLevel(level);
        return this;
    }

    public Skill build() {
        return skill;
    }
}
