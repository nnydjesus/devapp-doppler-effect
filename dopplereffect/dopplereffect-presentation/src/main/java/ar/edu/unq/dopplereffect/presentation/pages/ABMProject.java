package ar.edu.unq.dopplereffect.presentation.pages;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

import ar.edu.unq.dopplereffect.presentation.abm.ABMAbstract;
import ar.edu.unq.dopplereffect.project.Project;

/**
 */

public class ABMProject extends ABMAbstract<Project> {

    public ABMProject(final SearchProjectPage parent, final Project project) {
        super();
        this.setLaPaginaDeLaQueMeLlamaron(parent);
        this.setModel(project);
        Form<Project> form = new Form<Project>("projectForm", this.createModel());
        this.add(form);

        this.addFields(form);
        this.armarFormulario(form);
        this.addButtons(form);

    }

    public ABMProject(final SearchProjectPage parent) {
        this(parent, new Project());

    }

    /**
     * Crea y agrega los controles para editar el nuevo cliente.
     */
    @Override
    protected void addFields(final Form<Project> form) {
        form.add(new RequiredTextField<Project>("name"));
        form.add(new TextField<Project>("maxEffort"));
        form.add(this.setFeedbackPanel(new FeedbackPanel("feedbackPanel")));
    }

    protected void armarFormulario(final Form<Project> formulario) {
        // formulario.add(new DropDownChoice<Skill>("skills", new
        // ArrayList<Skill>()));
        // formulario.add(new DropDownChoiceNullValid<Persona>("asignado",
        // PersonaHome.getInstance().getPersonas(),
        // new PersonaChoiceRenderer()));

    }

}
