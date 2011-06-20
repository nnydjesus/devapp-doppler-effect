package ar.edu.unq.dopplereffect.presentation.panel.employee;

import java.util.Arrays;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;

import ar.edu.unq.dopplereffect.presentation.panel.utils.AbstractPanel;
import ar.edu.unq.dopplereffect.presentation.search.AbstractSearchPanel;
import ar.edu.unq.dopplereffect.presentation.search.SearchModel;
import ar.edu.unq.dopplereffect.presentation.search.employee.EmployeeSearchModel;
import ar.edu.unq.dopplereffect.presentation.search.leaverequest.LeaveRequestSearchModel;
import ar.edu.unq.dopplereffect.presentation.util.AjaxCallBack;
import ar.edu.unq.dopplereffect.service.employee.EmployeeViewDTO;

/**
 * Representa la pagina de busqueda de empleados.
 */
public class EmployeeSearchPanel extends AbstractSearchPanel<SearchModel<EmployeeViewDTO>> {

    private static final long serialVersionUID = -7425688577267166062L;

    /* ************************ INSTANCE VARIABLES ************************ */

    private EmployeeAjaxDataTable employeeAjaxDataTable;

    @SuppressWarnings({ "rawtypes" })
    public EmployeeSearchPanel(final String id, final AjaxCallBack parentPage, final EmployeeSearchModel model,
            final LeaveRequestSearchModel leaveRequestSearchModel) {
        this(id, parentPage, null, model, leaveRequestSearchModel);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public EmployeeSearchPanel(final String id, final AjaxCallBack parentPage, final AbstractPanel<?> backPanel,
            final EmployeeSearchModel model, final LeaveRequestSearchModel leaveRequestSearchModel) {
        super(id, parentPage, backPanel, model, Arrays.asList("firstName", "lastName", "dni"), EmployeePanel.class);
        this.getEmployeeAjaxDataTable().setLeaveRequestSearchModel(leaveRequestSearchModel);
    }

    public EmployeeAjaxDataTable getEmployeeAjaxDataTable() {
        return employeeAjaxDataTable;
    }

    public void setEmployeeAjaxDataTable(final EmployeeAjaxDataTable employeeAjaxDataTable) {
        this.employeeAjaxDataTable = employeeAjaxDataTable;
    }

    @Override
    protected String getSortName() {
        return "firstName";
    }

    @Override
    protected EmployeeAjaxDataTable createAjaxTable() {
        this.setEmployeeAjaxDataTable(new EmployeeAjaxDataTable(this, this.getTableWicketId(), this.getSortName(),
                (EmployeeSearchModel) this.getDefaultModelObject(), this.getCallback(), this.getFields(), this
                        .getAbmClass()));
        return this.getEmployeeAjaxDataTable();
    }

    @Override
    protected String getFormWicketId() {
        return "searchEmployeeForm";
    }

    @Override
    protected String getNewFromBeanWicketId() {
        return "newEmployee";
    }

    @Override
    protected void buildForm(final Form<SearchModel<EmployeeViewDTO>> form) {
        form.add(new TextField<String>("searchByFirstName"));
        form.add(new TextField<String>("searchByLastName"));
    }
}
