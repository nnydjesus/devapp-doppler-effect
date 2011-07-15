package ar.edu.unq.dopplereffect.presentation.panel.leaverequest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.odlabs.wiquery.ui.datepicker.DatePicker;

import ar.edu.unq.dopplereffect.presentation.panel.EntityPanel;
import ar.edu.unq.dopplereffect.service.employee.EmployeeService;
import ar.edu.unq.dopplereffect.service.employee.EmployeeViewDTO;
import ar.edu.unq.dopplereffect.service.leaverequest.LeaveRequestDTO;
import ar.edu.unq.dopplereffect.service.leaverequest.LeaveRequestService;

public class LeaveRequestPanel extends EntityPanel<LeaveRequestDTO> {

    private static final long serialVersionUID = 1511189466309907850L;

    /* ************************ INSTANCE VARIABLES ************************ */

    @SpringBean(name = "service.leave_request")
    private LeaveRequestService leaveRequestService;

    @SpringBean(name = "service.employee")
    private EmployeeService employeeService;

    /* *************************** CONSTRUCTORS *************************** */

    public LeaveRequestPanel(final String id) {
        super(id, new LeaveRequestDTO());
    }

    public LeaveRequestPanel(final String id, final LeaveRequestDTO model) {
        super(id, model, true);
    }

    /* **************************** ACCESSORS ***************************** */

    public LeaveRequestService getLeaveRequestService() {
        return leaveRequestService;
    }

    public void setLeaveRequestService(final LeaveRequestService leaveRequestService) {
        this.leaveRequestService = leaveRequestService;
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public void setEmployeeService(final EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /* ************************* PRIVATE METHODS ************************** */

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
        if (this.getModelObject().getEmployee() != null) {
            // if (this.getEmployee() != null) {
            for (EmployeeViewDTO choice : ddc.getChoices()) {
                if (choice.getDni() == this.getModelObject().getEmployee().getDni()) {
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
        DatePicker<Date> startDatePicker = new DatePicker<Date>("startDate");
        form.add(startDatePicker);
    }

    private void addEndDateField(final Form<LeaveRequestDTO> form) {
        DatePicker<Date> endDatePicker = new DatePicker<Date>("endDate");
        form.add(endDatePicker);
    }

    @Override
    protected String getFormWicketId() {
        return "leaveRequestForm";
    }

    @Override
    protected boolean showTitle() {
        return true;
    }
}
