package ar.edu.unq.dopplereffect.presentation.panel.project;

import java.util.Arrays;

import org.apache.wicket.Component;

import ar.edu.unq.dopplereffect.presentation.project.ProjectSearchModel;
import ar.edu.unq.dopplereffect.presentation.search.AbstractSearchPanel;
import ar.edu.unq.dopplereffect.presentation.util.AjaxCallBack;

public class ProjectSearchPanel extends AbstractSearchPanel<ProjectSearchModel> {
    private static final long serialVersionUID = 1L;

    public ProjectSearchPanel(final String id, final AjaxCallBack<Component> parentPage, final ProjectSearchModel model) {
        super(id, parentPage, model, Arrays.asList("name", "clientName", "timeProject.days", "currentEffort"),
                ProjectPanel.class);
    }

    // @Override
    // protected ITable selectITable() {
    // return this.createAjaxGrid();
    // }

    @Override
    protected String getFormWicketId() {
        return "searchProjectForm";
    }

    @Override
    protected String getNewFromBeanWicketId() {
        return "newProject";
    }
}
