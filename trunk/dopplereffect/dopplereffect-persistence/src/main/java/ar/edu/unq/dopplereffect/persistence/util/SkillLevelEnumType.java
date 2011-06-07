package ar.edu.unq.dopplereffect.persistence.util;

import ar.edu.unq.dopplereffect.projects.SkillLevel;

public class SkillLevelEnumType extends EnumUserType<SkillLevel> {
    public SkillLevelEnumType() {
        super(SkillLevel.class);
    }
}