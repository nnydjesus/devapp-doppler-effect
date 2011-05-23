package ar.edu.unq.dopplereffect.presentation.pages.project;

import java.util.Arrays;

import org.apache.wicket.markup.html.WebPage;

import ar.edu.unq.dopplereffect.presentation.project.SearchProject;
import ar.edu.unq.dopplereffect.presentation.search.AbstractSearchPage;

/**
 */
public class ProjectSearchPage extends AbstractSearchPage<SearchProject> {

    public ProjectSearchPage(final WebPage parentPage) {
        super(parentPage, new SearchProject(), Arrays.asList("name", "maxEffort", "currentEffort"), ProjectPage.class);
    }

}
