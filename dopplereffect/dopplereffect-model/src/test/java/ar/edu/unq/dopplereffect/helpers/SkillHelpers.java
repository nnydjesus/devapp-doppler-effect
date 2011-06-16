package ar.edu.unq.dopplereffect.helpers;

import static ar.edu.unq.dopplereffect.projects.SkillLevel.*;
import ar.edu.unq.dopplereffect.builders.projects.SkillBuilder;
import ar.edu.unq.dopplereffect.projects.Skill;

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
