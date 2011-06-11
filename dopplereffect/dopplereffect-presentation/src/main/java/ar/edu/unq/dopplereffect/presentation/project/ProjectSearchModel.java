package ar.edu.unq.dopplereffect.presentation.project;

import java.util.List;

import ar.edu.unq.dopplereffect.presentation.search.SearchModel;
import ar.edu.unq.dopplereffect.service.DTO;
import ar.edu.unq.dopplereffect.service.project.ProjectDTO;
import ar.edu.unq.dopplereffect.service.project.ProjectService;

public class ProjectSearchModel extends SearchModel<ProjectDTO> {

    private static final long serialVersionUID = 1L;

    private ProjectService service;

    private String name = "";

    public ProjectSearchModel() {
        super(ProjectDTO.class);
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

    public void setService(final ProjectService service) {
        this.service = service;
    }

    public ProjectService getService() {
        return service;
    }

    @Override
    protected List<ProjectDTO> getAllResultsFromService() {
        return this.getService().searchAllProjects();
    }

    @Override
    protected <D extends DTO> void callSaveOnService(final D entity) {
        this.getService().newProject((ProjectDTO) entity);
    }

    @Override
    protected void callRemoveOnService(final ProjectDTO entity) {
        this.getService().deleteProject(entity);
    }

    @Override
    protected <D extends DTO> void callUpdateOnService(final D entity) {
        this.getService().updateProject((ProjectDTO) entity);
    }

    @Override
    @SuppressWarnings("unchecked")
    public ProjectDTO createEditDTO(final ProjectDTO viewDTO) {
        // es el mismo en este caso??
        return viewDTO;
    }
}
