package ar.edu.unq.dopplereffect.presentation.panel.employee;

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.presentation.panel.AjaxActionPanel;
import ar.edu.unq.dopplereffect.presentation.panel.leaverequest.LeaveRequestSearchPanel;
import ar.edu.unq.dopplereffect.presentation.panel.utils.AbstractCallbackPanel;
import ar.edu.unq.dopplereffect.presentation.search.SearchModel;
import ar.edu.unq.dopplereffect.presentation.search.leaverequest.LeaveRequestSearchModel;
import ar.edu.unq.dopplereffect.presentation.util.AjaxCallBack;
import ar.edu.unq.dopplereffect.presentation.util.AjaxDataTablePage;

/**
 * Similar a una {@link AjaxDataTablePage} pero con el agregado de un link con
 * mas detalles.
 */
public class EmployeeAjaxDataTable extends AjaxDataTablePage<Employee> {

    private static final long serialVersionUID = 6376218516758816207L;

    private LeaveRequestSearchModel leaveRequestSearchModel;

    public EmployeeAjaxDataTable(final Panel parent, final String id, final String sortName,
            final SearchModel<Employee> aSearch, final AjaxCallBack<Component> aCallBack, final List<String> fields,
            final Class<? extends Component> abm) {
        super(parent, id, sortName, aSearch, aCallBack, fields, abm);
    }

    @Override
    protected void addCustomColumns(final List<IColumn<Employee>> columns) {
        columns.add(new AbstractColumn<Employee>(new Model<String>("Detalle")) {

            private static final long serialVersionUID = -2909724564965473105L;

            @Override
            public void populateItem(final Item<ICellPopulator<Employee>> cellItem, final String componentId,
                    final IModel<Employee> rowModel) {
                cellItem.add(new AjaxActionPanel(componentId, "details.png", "../") {

                    private static final long serialVersionUID = 1026116621448693265L;

                    @Override
                    public void onAction(final AjaxRequestTarget target) {
                        @SuppressWarnings({ "rawtypes", "unchecked" })
                        EmployeeDetailPanel comp = new EmployeeDetailPanel("body", rowModel.getObject(),
                                (AbstractCallbackPanel) EmployeeAjaxDataTable.this.getParentPanel());
                        EmployeeAjaxDataTable.this.getCallBack().execute(target, comp);
                    }
                });
            }
        });
        columns.add(new AbstractColumn<Employee>(new Model<String>("Licencias")) {

            private static final long serialVersionUID = -2909724564965473105L;

            @Override
            public void populateItem(final Item<ICellPopulator<Employee>> cellItem, final String componentId,
                    final IModel<Employee> rowModel) {
                cellItem.add(new AjaxActionPanel(componentId, "details.png", "../") {

                    private static final long serialVersionUID = 1026116621448693265L;

                    @Override
                    public void onAction(final AjaxRequestTarget target) {
                        AjaxCallBack<Component> callback = EmployeeAjaxDataTable.this.getCallBack();
                        LeaveRequestSearchModel leaveReqSearchModel = EmployeeAjaxDataTable.this
                                .getLeaveRequestSearchModel();
                        LeaveRequestSearchPanel comp = new LeaveRequestSearchPanel("body", callback,
                                leaveReqSearchModel);
                        leaveReqSearchModel.setSearchByEmployee(rowModel.getObject());
                        leaveReqSearchModel.search();
                        callback.execute(target, comp);
                    }
                });
            }
        });
    }

    public LeaveRequestSearchModel getLeaveRequestSearchModel() {
        return leaveRequestSearchModel;
    }

    public void setLeaveRequestSearchModel(final LeaveRequestSearchModel leaveRequestSearchModel) {
        this.leaveRequestSearchModel = leaveRequestSearchModel;
    }
}