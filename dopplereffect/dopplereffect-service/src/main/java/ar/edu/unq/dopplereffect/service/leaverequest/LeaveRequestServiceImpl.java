package ar.edu.unq.dopplereffect.service.leaverequest;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.exceptions.UserException;
import ar.edu.unq.dopplereffect.exceptions.ValidationException;
import ar.edu.unq.dopplereffect.leaverequests.LeaveRequest;
import ar.edu.unq.dopplereffect.leaverequests.LeaveRequestType;
import ar.edu.unq.dopplereffect.persistence.leaverequest.LeaveRequestRepositoryImpl;
import ar.edu.unq.dopplereffect.persistence.leaverequest.LeaveRequestTypeRepositoryImpl;
import ar.edu.unq.dopplereffect.service.employee.EmployeeServiceImpl;
import ar.edu.unq.dopplereffect.service.helpers.DateHelpers;
import ar.edu.unq.dopplereffect.service.validations.Validator;
import ar.edu.unq.dopplereffect.time.DurationStrategy;
import ar.edu.unq.dopplereffect.time.IntervalDurationStrategy;
import ar.edu.unq.dopplereffect.time.OneDayDurationStrategy;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {

    private static final long serialVersionUID = -4327535350301917583L;

    private LeaveRequestRepositoryImpl leaveRequestRepo;

    private LeaveRequestTypeRepositoryImpl leaveRequestTypeRepo;

    private EmployeeServiceImpl employeeService;

    public LeaveRequestRepositoryImpl getLeaveRequestRepo() {
        return leaveRequestRepo;
    }

    public void setLeaveRequestRepo(final LeaveRequestRepositoryImpl leaveRequestRepo) {
        this.leaveRequestRepo = leaveRequestRepo;
    }

    public LeaveRequestTypeRepositoryImpl getLeaveRequestTypeRepo() {
        return leaveRequestTypeRepo;
    }

    public void setLeaveRequestTypeRepo(final LeaveRequestTypeRepositoryImpl leaveRequestTypeRepo) {
        this.leaveRequestTypeRepo = leaveRequestTypeRepo;
    }

    public EmployeeServiceImpl getEmployeeService() {
        return employeeService;
    }

    public void setEmployeeService(final EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    @Transactional
    public List<LeaveRequestViewDTO> searchAllLeaveRequests() {
        return this.convertAll(this.getLeaveRequestRepo().searchAll());
    }

    @Override
    @Transactional
    public List<LeaveRequestViewDTO> searchAllByDateAndEmployee(final Date date, final String employeeFirstName,
            final String employeeLastName) {
        Employee employee = this.getEmployeeService().getEmployeeRepo()
                .findFirstWithName(employeeFirstName, employeeLastName);
        return this.convertAll(this.getLeaveRequestRepo().searchAllByDateAndEmployee(new DateTime(date), employee));
    }

    @Override
    @Transactional
    public List<LeaveRequestViewDTO> searchAllByDate(final Date date) {
        return this.convertAll(this.getLeaveRequestRepo().searchAllByDate(new DateTime(date)));
    }

    @Override
    @Transactional
    public List<LeaveRequestViewDTO> searchAllByEmployee(final String firstName, final String lastName) {
        Employee employee = this.getEmployeeService().getEmployeeRepo().findFirstWithName(firstName, lastName);
        return this.convertAll(this.getLeaveRequestRepo().searchAllByEmployee(employee));
    }

    @Override
    @Transactional
    public LeaveRequestDetailDTO getDetailForLeaveRequest(final LeaveRequestViewDTO leaveReqDTO) {
        // TODO
        return new LeaveRequestDetailDTO();
    }

    @Override
    @Transactional
    public void newLeaveRequest(final LeaveRequestDTO leaveReqDTO) {
        // validaciones
        new Validator(leaveReqDTO).notNull("reason").notNull("employee").notNull("durationType").notBlank("reason");
        LeaveRequestType type;
        try {
            type = this.getLeaveRequestTypeRepo().searchByReason(leaveReqDTO.getReason());
        } catch (UserException e) {
            throw new ValidationException("validations.inexistentReason", e);
        }
        DurationStrategy durationStrategy = this.getAndValidateDurationStrategy(leaveReqDTO);
        Employee employee = this.getAndValidateEmployee(leaveReqDTO);
        LeaveRequest leaveRequest = new LeaveRequest(type, durationStrategy);
        leaveRequest.setEmployee(employee);
        this.getLeaveRequestRepo().save(leaveRequest);
    }

    @Override
    @Transactional
    public void deleteLeaveRequest(final LeaveRequestViewDTO leaveReqDTO) {
        Employee emp = this.getEmployeeService().getEmployeeRepo().searchByDni(leaveReqDTO.getEmployee().getDni());
        LeaveRequest leaveRequest = this.getLeaveRequestRepo().searchByStartDateAndEmployee(
                new DateTime(leaveReqDTO.getStartDate()), emp);
        this.getLeaveRequestRepo().delete(leaveRequest);
    }

    @Override
    @Transactional
    public void updateLeaveRequest(final LeaveRequestDTO leaveReqDTO) {
        Employee emp = this.getAndValidateEmployee(leaveReqDTO);
        LeaveRequest leaveRequest = this.getLeaveRequestRepo().searchByStartDateAndEmployee(
                new DateTime(leaveReqDTO.getStartDate()), emp);
        this.getLeaveRequestRepo().update(leaveRequest);
    }

    @Override
    @Transactional
    public List<String> searchAllReasons() {
        return this.getLeaveRequestTypeRepo().searchAllReasons();
    }

    @Override
    public LeaveRequestDTO createEditDTO(final LeaveRequestViewDTO viewDTO) {
        Employee emp = this.getEmployeeService().getEmployeeRepo().searchByDni(viewDTO.getEmployee().getDni());
        LeaveRequest leaveRequest = this.getLeaveRequestRepo().searchByStartDateAndEmployee(
                new DateTime(viewDTO.getStartDate()), emp);
        LeaveRequestDTO result = new LeaveRequestDTO();
        if (leaveRequest.getDurationStrategy().getClass().equals(IntervalDurationStrategy.class)) {
            result.setDurationType("Interval");
            result.setEndDate(((IntervalDurationStrategy) leaveRequest.getDurationStrategy()).getEndDate().toDate());
        } else {
            result.setDurationType("One Day");
        }
        result.setStartDate(leaveRequest.getFirstDate().toDate());
        result.setReason(leaveRequest.getReason());
        result.setEmployee(this.getEmployeeService().convert(leaveRequest.getEmployee()));
        return result;
    }

    private List<LeaveRequestViewDTO> convertAll(final List<LeaveRequest> leaveRequests) {
        List<LeaveRequestViewDTO> results = new LinkedList<LeaveRequestViewDTO>();
        for (LeaveRequest lr : leaveRequests) {
            results.add(this.convert(lr));
        }
        return results;
    }

    private LeaveRequestViewDTO convert(final LeaveRequest leaveRequest) {
        LeaveRequestViewDTO result = new LeaveRequestViewDTO();
        result.setAmountOfDays(leaveRequest.getAmountOfDays());
        result.setReason(leaveRequest.getReason());
        result.setEmployee(this.getEmployeeService().convert(leaveRequest.getEmployee()));
        result.setStartDate(leaveRequest.getFirstDate().toDate());
        return result;
    }

    private Employee getAndValidateEmployee(final LeaveRequestDTO leaveReqDTO) {
        Employee employee = this.getEmployeeService().getEmployeeRepo().searchByDni(leaveReqDTO.getEmployee().getDni());
        for (DateTime date : this.startEndDates(leaveReqDTO)) {
            if (employee.hasLeaveRequestInDay(date)) {
                throw new ValidationException("validations.daysAlreadyRequested");
            }
        }
        return employee;
    }

    private DurationStrategy getAndValidateDurationStrategy(final LeaveRequestDTO leaveReqDTO) {
        DurationStrategy dStrategy = null;
        new Validator(leaveReqDTO).notNull("startDate");
        if (leaveReqDTO.getDurationType().equals("One Day")) {
            dStrategy = new OneDayDurationStrategy(new DateTime(leaveReqDTO.getStartDate()));
        } else if (leaveReqDTO.getDurationType().equals("Interval")) {
            new Validator(leaveReqDTO).notNull("endDate");
            if (leaveReqDTO.getStartDate().after(leaveReqDTO.getEndDate())) {
                throw new ValidationException("validations.wrongInterval");
            }
            dStrategy = new IntervalDurationStrategy(new DateTime(leaveReqDTO.getStartDate()), new DateTime(
                    leaveReqDTO.getEndDate()));
        } else {
            throw new ValidationException("validations.wrongDurationType");
        }
        return dStrategy;
    }

    private List<DateTime> startEndDates(final LeaveRequestDTO leaveReqDTO) {
        // solo porque el PMD chilla
        return DateHelpers.getDates(new DateTime(leaveReqDTO.getStartDate()), new DateTime(leaveReqDTO.getEndDate()));
    }
}
