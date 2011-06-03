package ar.edu.unq.dopplereffect.presentation.panel.leaverequest;

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

import ar.edu.unq.dopplereffect.leaverequests.LeaveRequest;
import ar.edu.unq.dopplereffect.presentation.panel.AjaxActionPanel;
import ar.edu.unq.dopplereffect.presentation.panel.utils.AbstractCallbackPanel;
import ar.edu.unq.dopplereffect.presentation.search.SearchModel;
import ar.edu.unq.dopplereffect.presentation.util.AjaxCallBack;
import ar.edu.unq.dopplereffect.presentation.util.AjaxDataTablePage;

public class LeaveRequestAjaxDataTablePage extends AjaxDataTablePage<LeaveRequest> {

    private static final long serialVersionUID = -2014336162839157158L;

    public LeaveRequestAjaxDataTablePage(final Panel parent, final String id, final String sortName,
            final SearchModel<LeaveRequest> aSearch, final AjaxCallBack<Component> aCallBack,
            final List<String> fields, final Class<? extends Component> abm) {
        super(parent, id, sortName, aSearch, aCallBack, fields, abm);
    }

    @Override
    protected void addCustomColumns(final List<IColumn<LeaveRequest>> columns) {
        int index = columns.size() - 3; // delante de 'edit' y 'delete'
        columns.add(index, new AbstractColumn<LeaveRequest>(new Model<String>("Detalle")) {

            private static final long serialVersionUID = -2909724564965473105L;

            @Override
            public void populateItem(final Item<ICellPopulator<LeaveRequest>> cellItem, final String componentId,
                    final IModel<LeaveRequest> rowModel) {
                cellItem.add(new AjaxActionPanel(componentId, "details.png", "../") {

                    private static final long serialVersionUID = 1026116621448693265L;

                    @SuppressWarnings({ "rawtypes", "unchecked" })
                    LeaveRequestDetailPanel comp = new LeaveRequestDetailPanel("body", rowModel.getObject(),
                            (AbstractCallbackPanel) LeaveRequestAjaxDataTablePage.this.getParentPanel());

                    @Override
                    public void onAction(final AjaxRequestTarget target) {
                        LeaveRequestAjaxDataTablePage.this.getCallBack().execute(target, comp);
                    }
                });
            }
        });
    }

}
