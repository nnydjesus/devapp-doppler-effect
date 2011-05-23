package ar.edu.unq.dopplereffect.presentation.project;

import ar.edu.unq.dopplereffect.presentation.search.Search;
import ar.edu.unq.dopplereffect.project.Project;
import ar.edu.unq.dopplereffect.project.ProjectBuilder;

public class SearchProject extends Search<Project> {
    private static final long serialVersionUID = 1L;

    private String name = "";

    public SearchProject() {
        super(Project.class);
        this.save(new ProjectBuilder().withName("Wicket").withEstimatedEffort(33333).build());
        this.save(new ProjectBuilder().withName("PAPAPA").withEstimatedEffort(34234).build());
        this.save(new ProjectBuilder().withName("SASASA").withEstimatedEffort(11111).build());
        this.save(new ProjectBuilder().withName("ALALALA").withEstimatedEffort(789898).build());

    }

    public String getSearchByName() {
        return name;
    }

    public void setSearchByName(final String aName) {
        name = aName;
    }

}
