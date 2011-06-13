package ar.edu.unq.dopplereffect.presentation.search.leaverequest;

import java.util.Date;
import java.util.List;

import ar.edu.unq.dopplereffect.presentation.search.SearchModel;
import ar.edu.unq.dopplereffect.service.DTO;
import ar.edu.unq.dopplereffect.service.employee.EmployeeViewDTO;
import ar.edu.unq.dopplereffect.service.leaverequest.LeaveRequestDTO;
import ar.edu.unq.dopplereffect.service.leaverequest.LeaveRequestDetailDTO;
import ar.edu.unq.dopplereffect.service.leaverequest.LeaveRequestService;
import ar.edu.unq.dopplereffect.service.leaverequest.LeaveRequestViewDTO;

public class LeaveRequestSearchModel extends SearchModel<LeaveRequestViewDTO> {

    private static final long serialVersionUID = -2535940685385343959L;

    private LeaveRequestService service;

    private Date searchByDate;

    private EmployeeViewDTO searchByEmployee;

    public LeaveRequestSearchModel() {
        super(LeaveRequestViewDTO.class);
    }

    public Date getSearchByDate() {
        return searchByDate;
    }

    public void setSearchByDate(final Date searchByDate) {
        this.searchByDate = searchByDate;
    }

    public EmployeeViewDTO getSearchByEmployee() {
        return searchByEmployee;
    }

    public void setSearchByEmployee(final EmployeeViewDTO searchByEmployee) {
        this.searchByEmployee = searchByEmployee;
    }

    public LeaveRequestService getService() {
        return service;
    }

    public void setService(final LeaveRequestService service) {
        this.service = service;
    }

    @Override
    public void search() {
        if (this.searchingByEmployee()) {
            if (this.searchingByDate()) {
                this.setResults(this.getService().searchAllByDateAndEmployee(this.getSearchByDate(),
                        this.getSearchByEmployee().getFirstName(), this.getSearchByEmployee().getLastName()));
            } else {
                this.setResults(this.getService().searchAllByEmployee(this.getSearchByEmployee().getFirstName(),
                        this.getSearchByEmployee().getLastName()));
            }
        } else {
            if (this.searchingByDate()) {
                this.setResults(this.getService().searchAllByDate(this.getSearchByDate()));
            } else {
                this.setResults(this.getService().searchAllLeaveRequests());
            }
        }
    }

    private boolean searchingByDate() {
        return this.getSearchByDate() != null;
    }

    private boolean searchingByEmployee() {
        return this.getSearchByEmployee() != null;
    }

    @Override
    public void reset() {
        super.reset();
        searchByEmployee = null;
        searchByDate = null;
    }

    @Override
    protected List<LeaveRequestViewDTO> getAllResultsFromService() {
        return this.getService().searchAllLeaveRequests();
    }

    @Override
    protected <D extends DTO> void callSaveOnService(final D leaveReqDTO) {
        this.getService().newLeaveRequest((LeaveRequestDTO) leaveReqDTO);
    }

    @Override
    protected void callRemoveOnService(final LeaveRequestViewDTO leaveReqDTO) {
        this.getService().deleteLeaveRequest(leaveReqDTO);
    }

    @Override
    protected <D extends DTO> void callUpdateOnService(final D leaveReqDTO) {
        this.getService().updateLeaveRequest((LeaveRequestDTO) leaveReqDTO);
    }

    @Override
    @SuppressWarnings("unchecked")
    public LeaveRequestDTO createEditDTO(final LeaveRequestViewDTO viewDTO) {
        return this.getService().createEditDTO(viewDTO);
    }

    public LeaveRequestDetailDTO getDetailForLeaveRequest(final LeaveRequestViewDTO leaveReqDTO) {
        return this.getService().getDetailForLeaveRequest(leaveReqDTO);
    }

	@Override
	protected List<LeaveRequestViewDTO> getByNameResultsFromService(String name) {
		return this.getAllResultsFromService();
	}

}
