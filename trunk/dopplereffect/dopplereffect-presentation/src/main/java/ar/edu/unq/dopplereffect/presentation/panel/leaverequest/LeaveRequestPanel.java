package ar.edu.unq.dopplereffect.presentation.panel.leaverequest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.wicket.datetime.StyleDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.unq.dopplereffect.presentation.panel.EntityPanel;
import ar.edu.unq.dopplereffect.presentation.search.leaverequest.LeaveRequestSearchModel;
import ar.edu.unq.dopplereffect.service.employee.EmployeeService;
import ar.edu.unq.dopplereffect.service.employee.EmployeeViewDTO;
import ar.edu.unq.dopplereffect.service.leaverequest.LeaveRequestDTO;
import ar.edu.unq.dopplereffect.service.leaverequest.LeaveRequestService;

public class LeaveRequestPanel extends EntityPanel<LeaveRequestDTO> {

    private static final long serialVersionUID = 1511189466309907850L;

    @SpringBean(name = "service.leave_request")
    private LeaveRequestService leaveRequestService;

    @SpringBean(name = "service.employee")
    private EmployeeService employeeService;

    private EmployeeViewDTO employee;

    public LeaveRequestService getLeaveRequestService() {
        return leaveRequestService;
    }

    public void setLeaveRequestService(final LeaveRequestService leaveRequestService) {
        this.leaveRequestService = leaveRequestService;
    }

    public EmployeeViewDTO getEmployee() {
        return employee;
    }

    public void setEmployee(final EmployeeViewDTO employee) {
        this.employee = employee;
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public void setEmployeeService(final EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public LeaveRequestPanel(final String id) {
        super(id, new LeaveRequestDTO());
    }

    public LeaveRequestPanel(final String id, final LeaveRequestDTO model) {
        super(id, model, true);
    }

    @Override
    protected void beforeConstruct() {
        this.setEmployee(((LeaveRequestSearchModel) this.getBackPanel().getModelObject()).getSearchByEmployee());
    }

    @Override
    protected void addFields(final Form<LeaveRequestDTO> form) {
        form.add(this.getFeedbackPanel());
        this.addEmployeeCombo(form);
        this.addTypeCombo(form);
        this.addDurationTypeCombo(form);
        this.addStartDateField(form);
        this.addEndDateField(form);
    }

    private void addEmployeeCombo(final Form<LeaveRequestDTO> form) {
        DropDownChoice<EmployeeViewDTO> ddc = new DropDownChoice<EmployeeViewDTO>("employeeCombo",
                new PropertyModel<EmployeeViewDTO>(form.getDefaultModelObject(), "employee"), this.getEmployeeService()
                        .searchAllEmployees());
        ddc.setNullValid(true);
        ddc.setRequired(true);
        if (this.getEmployee() != null) {
            for (EmployeeViewDTO choice : ddc.getChoices()) {
                if (choice.getDni() == this.getEmployee().getDni()) {
                    ddc.setModelObject(choice);
                }
            }
            ddc.setEnabled(false);
        }
        form.add(ddc);
    }

    protected void addDurationTypeCombo(final Form<LeaveRequestDTO> form) {
        List<String> choices = Arrays.asList("One Day", "Interval");
        DropDownChoice<String> durationTypeDropDownChoice = new DropDownChoice<String>("durationType", choices);
        durationTypeDropDownChoice.setRequired(true);
        durationTypeDropDownChoice.setNullValid(true);
        form.add(durationTypeDropDownChoice);
    }

    protected void addTypeCombo(final Form<LeaveRequestDTO> form) {
        DropDownChoice<String> ddc = new DropDownChoice<String>("reason", this.getLeaveRequestService()
                .searchAllReasons());
        ddc.setRequired(true);
        ddc.setNullValid(true);
        form.add(ddc);
    }

    private void addStartDateField(final Form<LeaveRequestDTO> form) {
        DateTextField dateTextField = new DateTextField("startDate", new PropertyModel<Date>(
                form.getDefaultModelObject(), "startDate"), new StyleDateConverter(true));
        dateTextField.add(new DatePicker());
        dateTextField.setRequired(true);
        form.add(dateTextField);
    }

    private void addEndDateField(final Form<LeaveRequestDTO> form) {
        DateTextField dateTextField = new DateTextField("endDate", new PropertyModel<Date>(
                form.getDefaultModelObject(), "endDate"), new StyleDateConverter(true));
        dateTextField.add(new DatePicker());
        dateTextField.setRequired(true);
        form.add(dateTextField);
    }

    @Override
    protected String getFormWicketId() {
        return "leaveRequestForm";
    }
}
