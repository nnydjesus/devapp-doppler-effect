package ar.edu.unq.dopplereffect.presentation.panel.project;

import java.util.Arrays;

import org.apache.wicket.Component;

import ar.edu.unq.dopplereffect.presentation.project.SkillSearchModel;
import ar.edu.unq.dopplereffect.presentation.search.AbstractSearchPanel;
import ar.edu.unq.dopplereffect.presentation.util.AjaxCallBack;

/**
 */
public class SkillSearchPanel extends AbstractSearchPanel<SkillSearchModel> {
    private static final long serialVersionUID = 1L;

    public SkillSearchPanel(final String id, final AjaxCallBack<Component> parentPage, final SkillSearchModel model) {
        super(id, parentPage, model, Arrays.asList("type.name", "level"), SkillPanel.class);
    }

    @Override
    protected String getSortName() {
        return "type.name";
    }
}
