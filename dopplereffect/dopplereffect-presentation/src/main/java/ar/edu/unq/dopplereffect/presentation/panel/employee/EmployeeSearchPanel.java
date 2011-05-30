package ar.edu.unq.dopplereffect.presentation.panel.employee;

import java.util.Arrays;

import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.presentation.App;
import ar.edu.unq.dopplereffect.presentation.search.AbstractSearchPanel;
import ar.edu.unq.dopplereffect.presentation.search.Search;
import ar.edu.unq.dopplereffect.presentation.util.AjaxCallBack;

/**
 * Representa la pagina de busqueda de empleados.
 */
public class EmployeeSearchPanel extends AbstractSearchPanel<Search<Employee>> {

    private static final long serialVersionUID = -7425688577267166062L;

    @SuppressWarnings("unchecked")
    public EmployeeSearchPanel(final String id, final AjaxCallBack parentPage) {
        super(id, parentPage, App.employeeSearch, Arrays.asList("firstName", "lastName", "dni"), EmployeePanel.class);
    }

    @Override
    protected String getSortName() {
        return "personalData.firstName";
    }

    @Override
    @SuppressWarnings("unchecked")
    protected EmployeeAjaxDataTablePage createAjaxTable() {
        return new EmployeeAjaxDataTablePage(this, this.getTableWicketId(), this.getSortName(),
                ((Search<Employee>) this.getDefaultModelObject()), this.getCallback(), this.getFields(), this.getAbm());
    }
}
