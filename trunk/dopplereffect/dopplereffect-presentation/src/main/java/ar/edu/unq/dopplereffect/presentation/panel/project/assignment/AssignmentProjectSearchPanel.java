package ar.edu.unq.dopplereffect.presentation.panel.project.assignment;

import java.util.Arrays;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;

import ar.edu.unq.dopplereffect.presentation.panel.project.AutomaticAssignmentProjectPanel;
import ar.edu.unq.dopplereffect.presentation.panel.utils.PanelCallbackLink;
import ar.edu.unq.dopplereffect.presentation.project.AssignmentProjectSearchModel;
import ar.edu.unq.dopplereffect.presentation.search.AbstractSearchPanel;
import ar.edu.unq.dopplereffect.presentation.search.SearchModel;
import ar.edu.unq.dopplereffect.presentation.util.AjaxCallBack;
import ar.edu.unq.dopplereffect.presentation.util.AjaxDataTable;
import ar.edu.unq.dopplereffect.service.project.ProjectAssignmentDTO;

public class AssignmentProjectSearchPanel extends AbstractSearchPanel<AssignmentProjectSearchModel> {

    private static final long serialVersionUID = 1L;

    public AssignmentProjectSearchPanel(final String id, final AjaxCallBack<Component> parentPage,
            final AssignmentProjectSearchModel model) {
        super(id, parentPage, model, Arrays.asList("projectName", "employeeDTO"),
                DetailsIntervalProjectAssignment.class);
    }

    @Override
    protected AjaxDataTable<ProjectAssignmentDTO, AssignmentProjectSearchModel> createAjaxTable() {
        return new AssignmentProjectAjaxDataTablePage(this, this.getTableWicketId(), this.getSortName(),
                (AssignmentProjectSearchModel) (SearchModel<?>) this.getDefaultModelObject(), this.getCallback(),
                this.getFields(), this.getAbmClass());
    }

    @Override
    protected void addButtons(final Form<AssignmentProjectSearchModel> form) {
        super.addButtons(form);
        form.add(new PanelCallbackLink("automaticAssignment", this.getCallback(), this
                .createFactoryAutomaticAssignment(), new StringResourceModel("automaticAssignment", new Model<String>(
                ""))));

    }

    @Override
    protected void addNewEntityButton(final Form<AssignmentProjectSearchModel> form) {
    }

    private AutomaticAssignmentProjectPanel createFactoryAutomaticAssignment() {
        return new AutomaticAssignmentProjectPanel(this.getId(), this.getCallback(), this.getModelObject());
    }

    @Override
    protected String getSortName() {
        return "projectName";
    }

    @Override
    protected String getFormWicketId() {
        return "searchProjectForm";
    }

    @Override
    protected String getNewFromBeanWicketId() {
        return "newProject";
    }

    @Override
    public Boolean cantDelete() {
        return false;
    }

    @Override
    public Boolean cantEdit() {
        return false;
    }
}
