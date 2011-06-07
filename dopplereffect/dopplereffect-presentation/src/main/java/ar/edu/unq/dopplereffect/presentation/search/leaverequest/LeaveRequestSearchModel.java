package ar.edu.unq.dopplereffect.presentation.search.leaverequest;

import java.util.Date;

import org.joda.time.DateTime;

import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.leaverequests.LeaveRequest;
import ar.edu.unq.dopplereffect.presentation.search.SearchModel;
import ar.edu.unq.dopplereffect.service.LeaveRequestServiceImpl;
import ar.edu.unq.dopplereffect.service.PersistenceService;

public class LeaveRequestSearchModel extends SearchModel<LeaveRequest> {

    private static final long serialVersionUID = -2535940685385343959L;

    private PersistenceService<LeaveRequest> service;

    private DateTime searchByDate;

    private Employee searchByEmployee;

    public LeaveRequestSearchModel() {
        super(LeaveRequest.class);
    }

    public Date getSearchByDate() {
        return searchByDate == null ? null : searchByDate.toDate();
    }

    public void setSearchByDate(final Date searchByDate) {
        this.searchByDate = new DateTime(searchByDate);
    }

    public Employee getSearchByEmployee() {
        return searchByEmployee;
    }

    public void setSearchByEmployee(final Employee searchByEmployee) {
        this.searchByEmployee = searchByEmployee;
    }

    @Override
    public PersistenceService<LeaveRequest> getService() {
        return service;
    }

    @Override
    public void setService(final PersistenceService<LeaveRequest> service) {
        this.service = service;
    }

    @Override
    public void search() {
        // TODO mejorar!
        if (this.getSearchByEmployee() == null) {
            this.setResults(((LeaveRequestServiceImpl) this.getService()).searchByDate(searchByDate));
        } else {
            this.setResults(((LeaveRequestServiceImpl) this.getService()).searchByEmployee(searchByEmployee));
        }
    }

    @Override
    public void reset() {
        super.reset();
        searchByEmployee = null;
        searchByDate = null;
    }
}
