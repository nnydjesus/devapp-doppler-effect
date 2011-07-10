package ar.edu.unq.dopplereffect.presentation.panel.project.assignment;

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;

import ar.edu.unq.dopplereffect.presentation.panel.AjaxActionPanel;
import ar.edu.unq.dopplereffect.presentation.project.AssignmentProjectSearchModel;
import ar.edu.unq.dopplereffect.presentation.search.AbstractSearchPanel;
import ar.edu.unq.dopplereffect.presentation.util.AjaxCallBack;
import ar.edu.unq.dopplereffect.presentation.util.AjaxDataTable;
import ar.edu.unq.dopplereffect.service.project.ProjectAssignmentDTO;

/**
 * TODO: description
 */
public class AssignmentProjectAjaxDataTablePage extends
        AjaxDataTable<ProjectAssignmentDTO, AssignmentProjectSearchModel> {

    private static final long serialVersionUID = 1L;

    public AssignmentProjectAjaxDataTablePage(final AbstractSearchPanel<?> parent, final String id,
            final String sortName, final AssignmentProjectSearchModel searchModel,
            final AjaxCallBack<Component> callBack, final List<String> fields, final Class<? extends Component> abmClass) {
        super(parent, id, sortName, searchModel, callBack, fields, abmClass);
    }

    @Override
    protected void addCustomColumns(final List<IColumn<ProjectAssignmentDTO>> columns) {
        columns.add(new AbstractColumn<ProjectAssignmentDTO>(new StringResourceModel("header.detail",
                new Model<String>(""))) {
            private static final long serialVersionUID = 1L;

            @Override
            public void populateItem(final Item<ICellPopulator<ProjectAssignmentDTO>> cellItem,
                    final String componentId, final IModel<ProjectAssignmentDTO> model) {
                cellItem.add(new AjaxActionPanel(componentId, "../details.png") {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void onAction(final AjaxRequestTarget target) {
                        Component page = AssignmentProjectAjaxDataTablePage.this.createDetailPanel(model);
                        AssignmentProjectAjaxDataTablePage.this.getCallBack().execute(target, page);
                    }

                });
            }
        });
    }

    protected Component createDetailPanel(final IModel<ProjectAssignmentDTO> model) {
        return new DetailsIntervalProjectAssignment(this.getParentPanel().getId(), model.getObject(),
                this.getCallBack(), this.getParentPanel());
    }

}
