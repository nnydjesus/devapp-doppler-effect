package ar.edu.unq.dopplereffect.presentation.panel.employee;

import java.util.Arrays;

import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.presentation.employee.EmployeeSearchModel;
import ar.edu.unq.dopplereffect.presentation.search.AbstractSearchPanel;
import ar.edu.unq.dopplereffect.presentation.search.SearchModel;
import ar.edu.unq.dopplereffect.presentation.search.leaverequest.LeaveRequestSearchModel;
import ar.edu.unq.dopplereffect.presentation.util.AjaxCallBack;

/**
 * Representa la pagina de busqueda de empleados.
 */
public class EmployeeSearchPanel extends AbstractSearchPanel<SearchModel<Employee>> {

    private static final long serialVersionUID = -7425688577267166062L;

    private EmployeeAjaxDataTable employeeAjaxDataTable;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public EmployeeSearchPanel(final String id, final AjaxCallBack parentPage, final EmployeeSearchModel model,
            final LeaveRequestSearchModel leaveRequestSearchModel) {
        super(id, parentPage, model, Arrays.asList("firstName", "lastName", "dni"), EmployeePanel.class);
        employeeAjaxDataTable.setLeaveRequestSearchModel(leaveRequestSearchModel);
    }

    @Override
    protected String getSortName() {
        return "personalData.firstName";
    }

    @Override
    @SuppressWarnings("unchecked")
    protected EmployeeAjaxDataTable createAjaxTable() {
        employeeAjaxDataTable = new EmployeeAjaxDataTable(this, this.getTableWicketId(), this.getSortName(),
                ((SearchModel<Employee>) this.getDefaultModelObject()), this.getCallback(), this.getFields(),
                this.getAbmClass());
        return employeeAjaxDataTable;
    }
}
