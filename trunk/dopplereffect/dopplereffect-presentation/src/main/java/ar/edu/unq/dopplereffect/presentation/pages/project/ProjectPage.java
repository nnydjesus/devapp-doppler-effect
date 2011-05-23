package ar.edu.unq.dopplereffect.presentation.pages.project;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;

import ar.edu.unq.dopplereffect.presentation.pages.basic.EntityPage;
import ar.edu.unq.dopplereffect.project.Project;

/**
 */

public class ProjectPage extends EntityPage<Project> {
	
    public ProjectPage(final ProjectSearchPage previousPage, final Project model, final Boolean editMode) {
        super(model, previousPage, editMode);
    }

    public ProjectPage(final ProjectSearchPage previousPage, final Project model) {
        super(model, previousPage);
    }

    public ProjectPage(final ProjectSearchPage previousPage) {
        super(new Project(), previousPage);
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

//    @Override
//    protected void createForm(final Form<Project> formulario) {
        // formulario.add(new DropDownChoice<Skill>("skills", new
        // ArrayList<Skill>()));
        // formulario.add(new DropDownChoiceNullValid<Persona>("asignado",
        // PersonaHome.getInstance().getPersonas(),
        // new PersonaChoiceRenderer()));

//    }

}
