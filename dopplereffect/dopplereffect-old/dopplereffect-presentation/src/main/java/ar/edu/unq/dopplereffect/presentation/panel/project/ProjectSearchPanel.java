package ar.edu.unq.dopplereffect.presentation.panel.project;

import java.util.Arrays;

import org.apache.wicket.Component;

import ar.edu.unq.dopplereffect.presentation.App;
import ar.edu.unq.dopplereffect.presentation.project.SearchProject;
import ar.edu.unq.dopplereffect.presentation.search.AbstractSearchPanel;
import ar.edu.unq.dopplereffect.presentation.util.AjaxCallBack;

/**
 */
public class ProjectSearchPanel extends AbstractSearchPanel<SearchProject> {
    private static final long serialVersionUID = 1L;

    public ProjectSearchPanel(final String id, final AjaxCallBack<Component> parentPage) {
        super(id, parentPage, App.searchProject, Arrays.asList("name", "maxEffort", "currentEffort"),
                ProjectPanel.class);
    }

    // @Override
    // protected ITable selectITable() {
    // return this.createListView();
    // }

}
