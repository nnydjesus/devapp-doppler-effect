package ar.edu.unq.dopplereffect.bean;

import org.joda.time.Period;

public class ProjectBuilder {

    private Project proyect;

    public ProjectBuilder() {
        this.setProyect(new Project());
    }

    public ProjectBuilder withName(final String name) {
        this.getProyect().setName(name);
        return this;
    }

    public ProjectBuilder withInformationClient(final InformationClient information) {
        this.getProyect().setInformationClient(information);
        return this;
    }

    public ProjectBuilder withConsideredEffor(final Period period) {
        this.getProyect().setConsideredEffor(period);
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
