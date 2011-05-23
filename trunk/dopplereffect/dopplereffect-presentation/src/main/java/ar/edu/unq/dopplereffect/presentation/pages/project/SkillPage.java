package ar.edu.unq.dopplereffect.presentation.pages.project;

import java.util.Arrays;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.model.CompoundPropertyModel;

import ar.edu.unq.dopplereffect.presentation.pages.basic.EntityPage;
import ar.edu.unq.dopplereffect.project.Skill;
import ar.edu.unq.dopplereffect.project.SkillLevel;
import ar.edu.unq.dopplereffect.project.SkillType;

/**
 */
public class SkillPage extends EntityPage<Skill> {
	
	 public SkillPage(final SkillSearchPage previousPage, final Skill model, final Boolean editMode) {
	        super(model, previousPage, editMode);
	    }

	    public SkillPage(final SkillSearchPage previousPage, final Skill model) {
	        super(model, previousPage);
	    }

	    public SkillPage(final SkillSearchPage previousPage) {
	        super(new Skill(), previousPage);
	    }


    @Override
    protected void addFields(final Form<Skill> form) {
        form.add(this.getFeedbackPanel());
        SkillType type = ((Skill) this.getDefaultModelObject()).getType();
        form.add(new RequiredTextField<SkillType>("type", new CompoundPropertyModel<SkillType>(type)));
        form.add(new DropDownChoice<SkillLevel>("level", Arrays.asList(SkillLevel.values())));

    }


}
