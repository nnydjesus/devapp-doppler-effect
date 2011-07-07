package ar.edu.unq.dopplereffect.presentation.search.employee;

import java.io.File;
import java.util.List;

import ar.edu.unq.dopplereffect.presentation.search.SearchModel;
import ar.edu.unq.dopplereffect.service.DTO;
import ar.edu.unq.dopplereffect.service.employee.EmployeeDTO;
import ar.edu.unq.dopplereffect.service.employee.EmployeeDetailDTO;
import ar.edu.unq.dopplereffect.service.employee.EmployeeService;
import ar.edu.unq.dopplereffect.service.employee.EmployeeServiceImpl;
import ar.edu.unq.dopplereffect.service.employee.EmployeeViewDTO;
import ar.edu.unq.dopplereffect.service.export.FormatterExportType;

/**
 * Modelo de busqueda de empleados, que permite buscar por nombre y apellido.
 */
public class EmployeeSearchModel extends SearchModel<EmployeeViewDTO> {

    private static final long serialVersionUID = -4428182030184876921L;

    /* ************************ INSTANCE VARIABLES ************************ */

    private EmployeeService service;

    private String searchByFirstName;

    private String searchByLastName;

    /* *************************** CONSTRUCTORS *************************** */

    public EmployeeSearchModel() {
        super(EmployeeViewDTO.class);
        searchByFirstName = "";
        searchByLastName = "";
    }

    /* **************************** ACCESSORS ***************************** */

    public String getSearchByFirstName() {
        return searchByFirstName;
    }

    public void setSearchByFirstName(final String searchByFirstName) {
        this.searchByFirstName = searchByFirstName;
    }

    public String getSearchByLastName() {
        return searchByLastName;
    }

    public void setSearchByLastName(final String searchByLastName) {
        this.searchByLastName = searchByLastName;
    }

    public EmployeeService getService() {
        return service;
    }

    public void setService(final EmployeeService service) {
        this.service = service;
    }

    /* **************************** OPERATIONS **************************** */

    @Override
    protected List<EmployeeViewDTO> getAllResultsFromService() {
        return this.getService().searchAllEmployees();
    }

    @Override
    protected <D extends DTO> void callSaveOnService(final D employee) {
        this.getService().newEmployee((EmployeeDTO) employee);
    }

    @Override
    protected void callRemoveOnService(final EmployeeViewDTO employee) {
        this.getService().deleteEmployee(employee);
    }

    @Override
    protected <D extends DTO> void callUpdateOnService(final D employee) {
        this.getService().updateEmployee((EmployeeDTO) employee);
    }

    /**
     * Construye un empleado con un detalle mayor de sus datos.
     */
    public EmployeeDetailDTO getDetailForEmployee(final EmployeeViewDTO employeeViewDTO) {
        return this.getService().getDetailForEmployee(employeeViewDTO);
    }

    @Override
    @SuppressWarnings("unchecked")
    public EmployeeDTO createEditDTO(final EmployeeViewDTO viewDTO) {
        return this.getService().createEditDTO(viewDTO);
    }

    @Override
    public void search() {
        this.setResults(this.getService().searchAllByFirstAndLastName(this.getSearchByFirstName(),
                this.getSearchByLastName()));
    }

    @Override
    protected List<EmployeeViewDTO> getByNameResultsFromService(final String name) {
        return this.getService().searchAllByFirstAndLastName(this.getSearchByFirstName(), this.getSearchByLastName());
    }

    @Override
    public File export(final String folder, final FormatterExportType type) {
        return ((EmployeeServiceImpl) this.getService()).export(folder + "/Employee." + type.getExtension());
    }

}
