package ar.edu.unq.dopplereffect.presentation.panel.project;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;

import ar.edu.unq.dopplereffect.presentation.pages.basic.EntityPanel;
import ar.edu.unq.dopplereffect.project.Project;

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
        form.add(this.getFeedbackPanel());
        form.add(new RequiredTextField<Project>("name"));
        form.add(new TextField<Project>("maxEffort"));
    }

    /**
     * Crea y agrega los controles para editar el nuevo cliente.
     */

    // @Override
    // protected void createForm(final Form<Project> formulario) {
    // formulario.add(new DropDownChoice<Skill>("skills", new
    // ArrayList<Skill>()));
    // formulario.add(new DropDownChoiceNullValid<Persona>("asignado",
    // PersonaHome.getInstance().getPersonas(),
    // new PersonaChoiceRenderer()));

    // }

}
