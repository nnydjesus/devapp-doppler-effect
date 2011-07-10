package ar.edu.unq.dopplereffect.builders.projects;

import java.util.HashSet;
import java.util.Set;

import org.joda.time.Months;
import org.joda.time.Period;

import ar.edu.unq.dopplereffect.builders.Builder;
import ar.edu.unq.dopplereffect.builders.employees.PersonalDataBuilder;
import ar.edu.unq.dopplereffect.data.PersonalData;
import ar.edu.unq.dopplereffect.employees.EmployeeTimeCalculator;
import ar.edu.unq.dopplereffect.projects.Project;
import ar.edu.unq.dopplereffect.projects.ProjectAssignmentStrategyImpl;
import ar.edu.unq.dopplereffect.projects.Skill;

public class ProjectBuilder implements Builder<Project> {

    protected transient String name = "project 1";

    protected transient long estimatedEffort = 1000L;

    protected transient Set<Skill> skills = new HashSet<Skill>();

    protected transient PersonalData infoClient = new PersonalDataBuilder().build();

    protected transient Period consideredEffort = new Period(Months.FOUR);

    protected transient EmployeeTimeCalculator calculator = new EmployeeTimeCalculator();

    protected transient ProjectAssignmentStrategyImpl assignmentStrategy = new ProjectAssignmentStrategyImpl();

    public ProjectBuilder withName(final String theName) {
        name = theName;
        return this;
    }

    public ProjectBuilder withEstimatedEffort(final long effort) {
        estimatedEffort = effort;
        return this;
    }

    public ProjectBuilder withSkill(final Skill skill) {
        skills.add(skill);
        return this;
    }

    public ProjectBuilder withSkills(final Set<Skill> theSkills) {
        skills = theSkills;
        return this;
    }

    public ProjectBuilder withInformationClient(final PersonalData information) {
        infoClient = information;
        return this;
    }

    public ProjectBuilder withConsideredEffor(final Period period) {
        consideredEffort = period;
        return this;
    }

    public ProjectBuilder withEmployeeTimeCalculator(final EmployeeTimeCalculator employeeCalculator) {
        calculator = employeeCalculator;
        assignmentStrategy.setEmployeeTimeCalculator(calculator);
        return this;
    }

    public ProjectBuilder withAssignmentStrategy(final ProjectAssignmentStrategyImpl strategy) {
        assignmentStrategy = strategy;
        return this;
    }

    @Override
    public Project build() {
        Project result = new Project(assignmentStrategy);
        result.setName(name);
        result.setClientData(infoClient);
        result.setMaxEffort(estimatedEffort);
        result.setConsideredEffort(consideredEffort);
        result.setSkills(skills);
        return result;
    }
}
