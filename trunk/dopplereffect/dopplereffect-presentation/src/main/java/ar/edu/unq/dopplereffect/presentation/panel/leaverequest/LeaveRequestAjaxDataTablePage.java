package ar.edu.unq.dopplereffect.presentation.panel.leaverequest;

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import ar.edu.unq.dopplereffect.presentation.panel.AjaxActionPanel;
import ar.edu.unq.dopplereffect.presentation.panel.utils.AbstractPanel;
import ar.edu.unq.dopplereffect.presentation.search.leaverequest.LeaveRequestSearchModel;
import ar.edu.unq.dopplereffect.presentation.util.AjaxCallBack;
import ar.edu.unq.dopplereffect.presentation.util.AjaxDataTablePage;
import ar.edu.unq.dopplereffect.service.leaverequest.LeaveRequestViewDTO;

public class LeaveRequestAjaxDataTablePage extends AjaxDataTablePage<LeaveRequestViewDTO, LeaveRequestSearchModel> {

    private static final long serialVersionUID = -2014336162839157158L;

    public LeaveRequestAjaxDataTablePage(final AbstractPanel<?> parent, final String id, final String sortName,
            final LeaveRequestSearchModel aSearch, final AjaxCallBack<Component> aCallBack, final List<String> fields,
            final Class<? extends Component> abm) {
        super(parent, id, sortName, aSearch, aCallBack, fields, abm);
    }

    @Override
    protected void addCustomColumns(final List<IColumn<LeaveRequestViewDTO>> columns) {
        columns.add(new AbstractColumn<LeaveRequestViewDTO>(new Model<String>("Detalle")) {

            private static final long serialVersionUID = -2909724564965473105L;

            @Override
            public void populateItem(final Item<ICellPopulator<LeaveRequestViewDTO>> cellItem,
                    final String componentId, final IModel<LeaveRequestViewDTO> rowModel) {
                cellItem.add(new AjaxActionPanel(componentId, "../details.png") {

                    private static final long serialVersionUID = 1026116621448693265L;

                    LeaveRequestDetailPanel comp = new LeaveRequestDetailPanel("body",
                            LeaveRequestAjaxDataTablePage.this.getSearchModel().getDetailForLeaveRequest(
                                    rowModel.getObject()), LeaveRequestAjaxDataTablePage.this.getCallBack(),
                            LeaveRequestAjaxDataTablePage.this.getParentPage());

                    @Override
                    public void onAction(final AjaxRequestTarget target) {
                        LeaveRequestAjaxDataTablePage.this.getCallBack().execute(target, comp);
                    }
                });
            }
        });
    }

}