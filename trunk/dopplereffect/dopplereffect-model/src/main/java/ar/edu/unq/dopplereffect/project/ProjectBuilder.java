package ar.edu.unq.dopplereffect.project;

import java.util.Set;

import org.joda.time.Period;

public class ProjectBuilder {

    private Project proyect;

    public ProjectBuilder() {
        this.setProyect(new Project(new ProjectAssignmentStrategy()));
    }

    public ProjectBuilder withEstimatedEffort(final long effort) {
        proyect.setMaxEffort(effort);
        return this;
    }

    public ProjectBuilder withName(final String name) {
        this.getProyect().setName(name);
        return this;
    }

    public ProjectBuilder addSkill(final Skill skill) {
        this.getProyect().addSkill(skill);
        return this;
    }

    public ProjectBuilder withSkills(final Set<Skill> skills) {
        this.getProyect().setSkills(skills);
        return this;
    }

    public ProjectBuilder withInformationClient(final ClientData information) {
        this.getProyect().setClientData(information);
        return this;
    }

    public ProjectBuilder withConsideredEffor(final Period period) {
        this.getProyect().setConsideredEffort(period);
        return this;
    }

    public Project build() {
        return this.getProyect();
    }

    protected Project getProyect() {
        return proyect;
    }

    protected void setProyect(final Project proyect) {
        this.proyect = proyect;
    }

}
