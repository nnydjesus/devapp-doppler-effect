package ar.edu.unq.dopplereffect.presentation.panel.project;

import java.util.Arrays;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.model.CompoundPropertyModel;

import ar.edu.unq.dopplereffect.presentation.pages.basic.EntityPanel;
import ar.edu.unq.dopplereffect.project.Skill;
import ar.edu.unq.dopplereffect.project.SkillLevel;
import ar.edu.unq.dopplereffect.project.SkillType;

/**
 */
public class SkillPanel extends EntityPanel<Skill> {

    private static final long serialVersionUID = 1L;

    public SkillPanel(final String id, final SkillSearchPanel previousPage, final Skill model, final Boolean editMode) {
        super(id, model, previousPage, editMode);
    }

    public SkillPanel(final String id, final SkillSearchPanel previousPage, final Skill model) {
        super(id, model, previousPage);
    }

    public SkillPanel(final String id, final SkillSearchPanel previousPage) {
        super(id, new Skill(), previousPage);
    }

    @Override
    protected void addFields(final Form<Skill> form) {
        form.add(this.getFeedbackPanel());
        SkillType type = ((Skill) this.getDefaultModelObject()).getType();
        form.add(new RequiredTextField<SkillType>("name", new CompoundPropertyModel<SkillType>(type)));
        form.add(new DropDownChoice<SkillLevel>("level", Arrays.asList(SkillLevel.values())));

    }

    @Override
    protected String getFormWicketId() {
        return "skillForm";
    }

}
