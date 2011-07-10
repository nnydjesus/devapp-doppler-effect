package ar.edu.unq.dopplereffect.presentation.project;

import java.io.File;
import java.util.List;

import ar.edu.unq.dopplereffect.presentation.search.SearchModel;
import ar.edu.unq.dopplereffect.service.DTO;
import ar.edu.unq.dopplereffect.service.employee.EmployeeViewDTO;
import ar.edu.unq.dopplereffect.service.employee.IEmployeeDTO;
import ar.edu.unq.dopplereffect.service.export.FormatterExportType;
import ar.edu.unq.dopplereffect.service.project.AssignmentProjectService;
import ar.edu.unq.dopplereffect.service.project.ProjectAssignmentDTO;
import ar.edu.unq.dopplereffect.service.project.ProjectDTO;
import ar.edu.unq.dopplereffect.time.IntervalDurationStrategy;

public class AssignmentProjectSearchModel extends SearchModel<ProjectAssignmentDTO> {

    private static final long serialVersionUID = 1L;

    private AssignmentProjectService service;

    private String name = "";

    public AssignmentProjectSearchModel() {
        super(ProjectAssignmentDTO.class);
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

    public void setService(final AssignmentProjectService service) {
        this.service = service;
    }

    public AssignmentProjectService getService() {
        return service;
    }

    @Override
    protected List<ProjectAssignmentDTO> getAllResultsFromService() {
        return this.getService().searchAllAssignmentProjects();
    }

    @Override
    protected <D extends DTO> void callSaveOnService(final D entity) {
        this.getService().newProject((ProjectAssignmentDTO) entity);
    }

    @Override
    protected void callRemoveOnService(final ProjectAssignmentDTO entity) {
        this.getService().deleteProject(entity);
    }

    @Override
    protected <D extends DTO> void callUpdateOnService(final D entity) {
        this.getService().updateProject((ProjectAssignmentDTO) entity);
    }

    @Override
    @SuppressWarnings("unchecked")
    public ProjectAssignmentDTO createEditDTO(final ProjectAssignmentDTO viewDTO) {
        // es el mismo en este caso??
        return viewDTO;
    }

    @Override
    protected List<ProjectAssignmentDTO> getByNameResultsFromService(final String searchName) {
        return null;
        // return this.getService().searchByEmployee(employee);
    }

    protected List<ProjectAssignmentDTO> getByEmployeeFromService(final IEmployeeDTO employee) {
        return this.getService().searchByEmployee(employee);
    }

    public ProjectAssignmentDTO assignmentEmployee(final ProjectDTO projectDTO, final EmployeeViewDTO employeeViewDTO,
            final IntervalDurationStrategy intervalDurationStrategy) {
        return this.getService().assignmentEmployee(projectDTO, employeeViewDTO, intervalDurationStrategy);
    }

    @Override
    public File export(final String folder, final FormatterExportType type) {
        return null;
    }
}
