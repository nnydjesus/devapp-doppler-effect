package ar.edu.unq.dopplereffect.projects;

import java.util.Set;

import org.joda.time.Period;

import ar.edu.unq.dopplereffect.data.PersonalData;
import ar.edu.unq.dopplereffect.employees.EmployeeTimeCalculator;

public class ProjectBuilder {

    private Project proyect;

    public ProjectBuilder() {
        this.setProyect(new Project(new ProjectAssignmentStrategy()));
    }

    protected Project getProyect() {
        return proyect;
    }

    protected void setProyect(final Project proyect) {
        this.proyect = proyect;
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

    public ProjectBuilder withInformationClient(final PersonalData information) {
        this.getProyect().setClientData(information);
        return this;
    }

    public ProjectBuilder withConsideredEffor(final Period period) {
        this.getProyect().setConsideredEffort(period);
        return this;
    }

    public ProjectBuilder withEmployeeTimeCalculator(final EmployeeTimeCalculator calculator) {
        ((ProjectAssignmentStrategy) this.getProyect().getProjectAssignmentStrategy())
                .setEmployeeTimeCalculator(calculator);
        return this;
    }

    public Project build() {
        return this.getProyect();
    }
}
