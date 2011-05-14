package ar.edu.unq.dopplereffect.persistence.util;

import ar.edu.unq.dopplereffect.project.SkillLevel;

public class SkillLevelEnumType extends EnumUserType<SkillLevel> {
    public SkillLevelEnumType() {
        super(SkillLevel.class);
    }
}