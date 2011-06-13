package ar.edu.unq.dopplereffect.presentation.employee;

import java.util.List;

import ar.edu.unq.dopplereffect.presentation.search.SearchByExampleModel;
import ar.edu.unq.dopplereffect.service.DTO;
import ar.edu.unq.dopplereffect.service.employee.EmployeeDTO;
import ar.edu.unq.dopplereffect.service.employee.EmployeeDetailDTO;
import ar.edu.unq.dopplereffect.service.employee.EmployeeService;
import ar.edu.unq.dopplereffect.service.employee.EmployeeViewDTO;

public class EmployeeSearchModel extends SearchByExampleModel<EmployeeViewDTO> {

    private static final long serialVersionUID = -4428182030184876921L;

    private EmployeeService service;

    public EmployeeService getService() {
        return service;
    }

    public void setService(final EmployeeService service) {
        this.service = service;
    }

    public EmployeeSearchModel() {
        super(EmployeeViewDTO.class);
    }

    public String getSearchByName() {
        return this.getExample().getFirstName();
    }

    public void setSearchByName(final String aName) {
        this.getExample().setFirstName(aName);
    }

    @Override
    public Class<EmployeeViewDTO> getEntityType() {
        return EmployeeViewDTO.class;
    }

    @Override
    public List<EmployeeViewDTO> searchByExample(final EmployeeViewDTO theExample) {
        return this.getService().searchAllByExample(theExample);
    }

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

    public EmployeeDetailDTO getDetailForEmployee(final EmployeeViewDTO employeeViewDTO) {
        return this.getService().getDetailForEmployee(employeeViewDTO);
    }

    @Override
    @SuppressWarnings("unchecked")
    public EmployeeDTO createEditDTO(final EmployeeViewDTO viewDTO) {
        return this.getService().createEditDTO(viewDTO);
    }

	@Override
	protected List<EmployeeViewDTO> getByNameResultsFromService(String name) {
		return this.getAllResultsFromService();
	}

}
