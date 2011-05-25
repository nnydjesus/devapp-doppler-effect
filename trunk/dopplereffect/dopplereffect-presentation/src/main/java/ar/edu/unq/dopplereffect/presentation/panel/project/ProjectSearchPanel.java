package ar.edu.unq.dopplereffect.presentation.panel.project;

import java.util.Arrays;

import ar.edu.unq.dopplereffect.presentation.project.SearchProject;
import ar.edu.unq.dopplereffect.presentation.search.AbstractSearchPanel;
import ar.edu.unq.dopplereffect.presentation.util.CallBack;

/**
 */
public class ProjectSearchPanel extends AbstractSearchPanel<SearchProject> {
    private static final long serialVersionUID = 1L;

    public ProjectSearchPanel(final String id, final CallBack parentPage) {
        super(id, parentPage, new SearchProject(), Arrays.asList("name", "maxEffort", "currentEffort"),
                ProjectPanel.class);
    }

}