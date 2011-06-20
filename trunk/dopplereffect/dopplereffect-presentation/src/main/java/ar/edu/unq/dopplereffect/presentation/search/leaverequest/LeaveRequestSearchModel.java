package ar.edu.unq.dopplereffect.presentation.search.leaverequest;

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

    /* ************************ INSTANCE VARIABLES ************************ */

    private LeaveRequestService service;

    private String searchByReason;

    private EmployeeViewDTO searchByEmployee;

    /* *************************** CONSTRUCTORS *************************** */

    public LeaveRequestSearchModel() {
        super(LeaveRequestViewDTO.class);
    }

    /* **************************** ACCESSORS ***************************** */

    public LeaveRequestService getService() {
        return service;
    }

    public void setService(final LeaveRequestService service) {
        this.service = service;
    }

    public EmployeeViewDTO getSearchByEmployee() {
        return searchByEmployee;
    }

    public void setSearchByEmployee(final EmployeeViewDTO searchByEmployee) {
        this.searchByEmployee = searchByEmployee;
    }

    public String getSearchByReason() {
        return searchByReason;
    }

    public void setSearchByReason(final String searchByReason) {
        this.searchByReason = searchByReason;
    }

    /* **************************** OPERATIONS **************************** */

    @Override
    public void search() {
        this.setResults(this.getService().searchAllByReasonAndEmployee(this.getSearchByReason(),
                this.getSearchByEmployee()));
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
    protected List<LeaveRequestViewDTO> getByNameResultsFromService(final String name) {
        return this.getAllResultsFromService();
    }
}
