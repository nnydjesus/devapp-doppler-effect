package ar.edu.unq.dopplereffect.presentation.panel.project;

import java.util.Arrays;

import org.apache.wicket.Component;

import ar.edu.unq.dopplereffect.presentation.project.SearchSkill;
import ar.edu.unq.dopplereffect.presentation.search.AbstractSearchPanel;
import ar.edu.unq.dopplereffect.presentation.util.AjaxCallBack;

/**
 */
public class SkillSearchPanel extends AbstractSearchPanel<SearchSkill> {
    private static final long serialVersionUID = 1L;

    public SkillSearchPanel(final String id, final AjaxCallBack<Component> parentPage) {
        super(id, parentPage, new SearchSkill(), Arrays.asList("type", "level"), SkillPanel.class);
    }

    @Override
    protected String getSortName() {
        return "type.name";
    }
}
