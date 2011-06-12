package ar.edu.unq.dopplereffect.presentation.panel.project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.markup.html.form.palette.Palette;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.model.util.CollectionModel;
import org.apache.wicket.model.util.ListModel;

import ar.edu.unq.dopplereffect.presentation.pages.basic.NavigablePanel;
import ar.edu.unq.dopplereffect.presentation.panel.utils.AbstractCallbackPanel;
import ar.edu.unq.dopplereffect.presentation.panel.utils.PanelCallbackLink;
import ar.edu.unq.dopplereffect.service.employee.EmployeeViewDTO;

public class AssignmentProjectPanel extends NavigablePanel<String> {

    private static final long serialVersionUID = 7612811876399884445L;

    public AssignmentProjectPanel(final String id, final AbstractCallbackPanel<? extends Serializable> callback) {
        super(id, "", callback);
        this.makePanel();
        this.addNavigationButtons();
    }

    protected void makePanel() {
        List<EmployeeViewDTO> persons = Arrays.asList(new EmployeeViewDTO(), new EmployeeViewDTO(),
                new EmployeeViewDTO(), new EmployeeViewDTO());

        IChoiceRenderer<EmployeeViewDTO> renderer = new ChoiceRenderer<EmployeeViewDTO>("firstName", "lastName");

        final Palette<EmployeeViewDTO> palette = new Palette<EmployeeViewDTO>("palette",
                new ListModel<EmployeeViewDTO>(new ArrayList<EmployeeViewDTO>()), new CollectionModel<EmployeeViewDTO>(
                        persons), renderer, 10, true);

        Form<String> form = new Form<String>("form");
        form.add(new AjaxButton("acept", new StringResourceModel("aceptButton", new Model<String>(""))) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit(final AjaxRequestTarget target, final Form<?> aForm) {
                AssignmentProjectPanel.this.getCallBackPrevuousPanel().getCallback()
                        .execute(target, AssignmentProjectPanel.this.getCallBackPrevuousPanel());
            }
        });

        form.add(palette);
        this.add(form);

    }

    protected void addNavigationButtons() {
        this.add(new PanelCallbackLink("back_button", this.getCallBackPrevuousPanel().getCallback(), this
                .getCallBackPrevuousPanel(), new StringResourceModel("back_button", new Model<String>(""))));
    }

}
