package ar.edu.unq.dopplereffect.presentation.pages;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

import ar.edu.unq.dopplereffect.presentation.pages.employee.EmployeeSearchPage;
import ar.edu.unq.dopplereffect.presentation.pages.project.ProjectPage;
import ar.edu.unq.dopplereffect.presentation.pages.project.ProjectSearchPage;

/**
 * Pagina de inicio de la aplicacion, que contiene los links a todas las otras
 * paginas.
 */
public class MainPage extends WebPage {

    public MainPage() {
        this.add(this.createProjectLink());
        // this.add(new Label("projectABMLinkText", "GSHIFGUSD"));
        this.add(this.createEmployeeLink());
        // this.add(new Label("employeeABMLinkText", "SOJGKFB"));
    }

    /**
     * Crea el link que lleva al ABM de empleados.
     */
    private Component createEmployeeLink() {
        return new Link<Object>("employeeABMLink") {

            private static final long serialVersionUID = -7320784256155291439L;

            @Override
            public void onClick() {
                MainPage.this.setResponsePage(new EmployeeSearchPage(MainPage.this));
            }
        };
    }

    /**
     * Agrega el link al ABM de proyectos
     */
    private Component createProjectLink() {
        return new Link<Object>("projectABMLink") {

            private static final long serialVersionUID = -7320784256155291439L;

            @Override
            public void onClick() {
                MainPage.this.setResponsePage(new ProjectPage(new ProjectSearchPage(MainPage.this)));
            }
        };
    }
}
