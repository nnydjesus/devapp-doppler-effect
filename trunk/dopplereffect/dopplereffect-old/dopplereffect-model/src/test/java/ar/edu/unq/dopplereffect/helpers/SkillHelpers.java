package ar.edu.unq.dopplereffect.helpers;

import static ar.edu.unq.dopplereffect.project.SkillLevel.BEGINNER;
import static ar.edu.unq.dopplereffect.project.SkillLevel.EXPERT;
import static ar.edu.unq.dopplereffect.project.SkillLevel.MEDIUM;
import ar.edu.unq.dopplereffect.project.Skill;
import ar.edu.unq.dopplereffect.skills.SkillBuilder;

public class SkillHelpers {

    private SkillHelpers() {

    }

    public static final Skill MYSQL_BEGINNER = new SkillBuilder().withType("MySQL").withLevel(BEGINNER).build();

    public static final Skill MYSQL_MEDIUM = new SkillBuilder().withType("MySQL").withLevel(MEDIUM).build();

    public static final Skill MYSQL_EXPERT = new SkillBuilder().withType("MySQL").withLevel(EXPERT).build();

    public static final Skill WICKET_EXPERT = new SkillBuilder().withType("Wicket").withLevel(EXPERT).build();

    public static final Skill HIBERNATE_EXPERT = new SkillBuilder().withType("Hibernate").withLevel(EXPERT).build();

    public static final Skill HIBERNATE_BEGINNER = new SkillBuilder().withType("Hibernate").withLevel(BEGINNER).build();

    public static final Skill HIBERNATE_MEDIUM = new SkillBuilder().withType("Hibernate").withLevel(MEDIUM).build();
}
