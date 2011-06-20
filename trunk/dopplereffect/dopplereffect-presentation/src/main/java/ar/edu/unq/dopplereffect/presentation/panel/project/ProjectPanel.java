package ar.edu.unq.dopplereffect.presentation.panel.project;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
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
import ar.edu.unq.dopplereffect.presentation.panel.EntityPanel;
import ar.edu.unq.dopplereffect.presentation.search.AbstractSearchPanel;
import ar.edu.unq.dopplereffect.presentation.search.SearchModel;
import ar.edu.unq.dopplereffect.service.project.ProjectDTO;

import com.wiquery.plugin.watermark.TextFieldWatermarkBehaviour;

public class ProjectPanel extends EntityPanel<ProjectDTO> {

    private static final long serialVersionUID = 1L;

    /* *************************** CONSTRUCTORS *************************** */

    public ProjectPanel(final String id, final ProjectDTO model) {
        super(id, model, true);
    }

    public ProjectPanel(final String id) {
        super(id, new ProjectDTO());
    }

    /* ************************* PRIVATE METHODS ************************** */

    @Override
    protected void addFields(final Form<ProjectDTO> form) {
        final AddSkillDialog addSkillDialog = new AddSkillDialog("addSkillDialog", this.getModelObject());
        form.add(this.getFeedbackPanel());
        form.add(new RequiredTextField<String>("name").add(new TextFieldWatermarkBehaviour("Project Name")));
        form.add(new TextField<Long>("maxEffort"));
        Button addSkill = (Button) CustomComponent.addButtonSking(new Button("addSkill", new StringResourceModel(
                "addSkill", new Model<String>(""))));
        form.add(addSkill);
        this.add(addSkillDialog);

        addSkill.add(new WiQueryEventBehavior(new Event(MouseEvent.CLICK) {

            private static final long serialVersionUID = 1L;

            @Override
            public JsScope callback() {
                return JsScope.quickScope(addSkillDialog.open().render());
            }
        }));

        Component assignmentLink = CustomComponent.addButtonSking(new AjaxLink<String>("assignemt",
                new StringResourceModel("add", new Model<String>(""))) {

            private static final long serialVersionUID = 1L;

            @Override
            @SuppressWarnings("unchecked")
            public void onClick(final AjaxRequestTarget target) {
                ProjectPanel.this.getCallback().execute(
                        target,
                        new AssignmentProjectPanel("body", ProjectPanel.this.getModelObject(), ProjectPanel.this
                                .getCallback(), ProjectPanel.this,
                                (AbstractSearchPanel<SearchModel<ProjectDTO>>) ProjectPanel.this.getBackPanel()));
            }

        });
        if (!this.isEditMode()) {
            assignmentLink.setVisible(false);
        }
        form.add(assignmentLink);
    }

    @Override
    protected String getFormWicketId() {
        return "projectForm";
    }
}
