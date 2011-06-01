package ar.edu.unq.dopplereffect.presentation.project;

import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.unq.dopplereffect.presentation.App;
import ar.edu.unq.dopplereffect.presentation.search.SearchPanelModel;
import ar.edu.unq.dopplereffect.project.Project;
import ar.edu.unq.dopplereffect.service.Service;

public class SearchProject extends SearchPanelModel<Project> {
    private static final long serialVersionUID = 1L;

    @SpringBean(name = "projectSearch")
    private Service<Project> service;

    private String name = "";

    public SearchProject() {
        super(Project.class);
        // this.save(new
        // ProjectBuilder().withName("Wicket").withEstimatedEffort(33333).build());
        // this.save(new
        // ProjectBuilder().withName("PAPAPA").withEstimatedEffort(34234).build());
        // this.save(new
        // ProjectBuilder().withName("SASASA").withEstimatedEffort(11111).build());
        // this.save(new
        // ProjectBuilder().withName("ALALALA").withEstimatedEffort(789898).build());
        App.searchProject = this;
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
    public void setService(final Service<Project> service) {
        this.service = service;
    }

    @Override
    public Service<Project> getService() {
        return service;
    }

}
