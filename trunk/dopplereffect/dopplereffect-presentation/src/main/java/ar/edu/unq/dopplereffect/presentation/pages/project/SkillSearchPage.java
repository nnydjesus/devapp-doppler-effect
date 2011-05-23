package ar.edu.unq.dopplereffect.presentation.pages.project;

import java.util.Arrays;

import org.apache.wicket.markup.html.WebPage;

import ar.edu.unq.dopplereffect.presentation.project.SearchSkill;
import ar.edu.unq.dopplereffect.presentation.search.AbstractSearchPage;

/**
 */
public class SkillSearchPage extends AbstractSearchPage<SearchSkill> {

    public SkillSearchPage(final WebPage parentPage) {
        super(parentPage, new SearchSkill(), Arrays.asList("type", "level"), SkillPage.class);
    }
    
    @Override
    protected String getSortName() {
    	return "type.name";
    }

}
