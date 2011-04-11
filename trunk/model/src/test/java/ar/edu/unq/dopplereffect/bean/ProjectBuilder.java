package ar.edu.unq.dopplereffect.bean;

import org.joda.time.Period;

public class ProjectBuilder {

    private Project proyect;

    public ProjectBuilder() {
        proyect = new Project();
    }

    public ProjectBuilder withName(final String name) {
        proyect.setName(name);
        return this;
    }

    public ProjectBuilder withInformationClient(final InformationClient information) {
        proyect.setInformationClient(information);
        return this;
    }

    public ProjectBuilder withConsideredEffor(final Period period) {
        proyect.setConsideredEffor(period);
        return this;
    }

    public Project build() {
        return proyect;
    }

}
