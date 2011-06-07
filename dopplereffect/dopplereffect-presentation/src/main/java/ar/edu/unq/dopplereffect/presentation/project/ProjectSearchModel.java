package ar.edu.unq.dopplereffect.presentation.project;

import ar.edu.unq.dopplereffect.presentation.search.SearchModel;
import ar.edu.unq.dopplereffect.projects.Project;
import ar.edu.unq.dopplereffect.service.PersistenceService;

public class ProjectSearchModel extends SearchModel<Project> {
    private static final long serialVersionUID = 1L;

    private PersistenceService<Project> service;

    private String name = "";

    public ProjectSearchModel() {
        super(Project.class);
        // this.save(new
        // ProjectBuilder().withName("Wicket").withEstimatedEffort(33333).build());
        // this.save(new
        // ProjectBuilder().withName("PAPAPA").withEstimatedEffort(34234).build());
        // this.save(new
        // ProjectBuilder().withName("SASASA").withEstimatedEffort(11111).build());
        // this.save(new
        // ProjectBuilder().withName("ALALALA").withEstimatedEffort(789898).build());
    }

    public String getSearchByName() {
        this.search();
        return this.getName();
    }

    public void setSearchByName(final String aName) {
        this.setName(aName);
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void setService(final PersistenceService<Project> service) {
        this.service = service;
    }

    @Override
    public PersistenceService<Project> getService() {
        return service;
    }

}
