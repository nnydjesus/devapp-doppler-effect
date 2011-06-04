package ar.edu.unq.dopplereffect.presentation.panel.project;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;
import org.odlabs.wiquery.core.events.Event;
import org.odlabs.wiquery.core.events.MouseEvent;
import org.odlabs.wiquery.core.events.WiQueryEventBehavior;
import org.odlabs.wiquery.core.javascript.JsScope;

import ar.edu.unq.dopplereffect.presentation.components.CustomComponent;
import ar.edu.unq.dopplereffect.presentation.pages.basic.EntityPanel;
import ar.edu.unq.dopplereffect.project.Project;

import com.wiquery.plugin.watermark.TextFieldWatermarkBehaviour;

/**
 */

public class ProjectPanel extends EntityPanel<Project> {
    private static final long serialVersionUID = 1L;

    public ProjectPanel(final String id, final ProjectSearchPanel previousPage, final Project model,
            final Boolean editMode) {
        super(id, model, previousPage, editMode);
    }

    public ProjectPanel(final String id, final ProjectSearchPanel previousPage, final Project model) {
        super(id, model, previousPage);
    }

    public ProjectPanel(final String id, final ProjectSearchPanel previousPage) {
        super(id, new Project(), previousPage);
    }

    @Override
    protected void addFields(final Form<Project> form) {
        final AddSkillDialog addSkillDialog = new AddSkillDialog("addSkillDialog", this.getModelObject());
        form.add(this.getFeedbackPanel());
        form.add(new RequiredTextField<Project>("name").add(new TextFieldWatermarkBehaviour("Project Name")));
        form.add(new TextField<Project>("maxEffort"));
        Button addSkill = (Button) CustomComponent.addButtonSking(new Button("addSkill", new StringResourceModel(
                "addSkill", new Model<String>(""))));
        form.add(addSkill);
        form.add(addSkillDialog);

        addSkill.add(new WiQueryEventBehavior(new Event(MouseEvent.CLICK) {

            private static final long serialVersionUID = 1L;

            @Override
            public JsScope callback() {
                return JsScope.quickScope(addSkillDialog.open().render());
            }

        }));
    }

    @Override
    protected void beforeConstruct() {
        // x
    }
}
