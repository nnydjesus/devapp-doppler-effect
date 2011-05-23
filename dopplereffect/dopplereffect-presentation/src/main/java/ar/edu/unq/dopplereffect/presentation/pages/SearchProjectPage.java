package ar.edu.unq.dopplereffect.presentation.pages;

import java.util.Arrays;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;

import ar.edu.unq.dopplereffect.presentation.component.PageLink;
import ar.edu.unq.dopplereffect.presentation.component.SuperAjaxButton;
import ar.edu.unq.dopplereffect.presentation.pages.basic.WebPageFactory;
import ar.edu.unq.dopplereffect.presentation.project.SearchProject;
import ar.edu.unq.dopplereffect.presentation.search.AbstractListView;
import ar.edu.unq.dopplereffect.presentation.search.AbstractSearchPage;
import ar.edu.unq.dopplereffect.project.Project;

public class SearchProjectPage extends AbstractSearchPage<SearchProject> {

    private WebPage laPaginaDondeMeLlamaron;

    /**
     * Constructor that is invoked when page is invoked without a session.
     * 
     * @param parameters
     *            Page parameters
     */
    public SearchProjectPage(final WebPage laPaginaDondeMeLlamaron) {
        super(new SearchProject());
        this.laPaginaDondeMeLlamaron = laPaginaDondeMeLlamaron;
        this.init(this.createForm("searchProjectForm"));
    }

    @Override
    protected void addButton(final Form<SearchProject> form) {
        form.add(new SuperAjaxButton<SearchProject>("search", form, this.getAjaxSectionResult()));
        form.add(new PageLink("newProject", new WebPageFactory() {
            private static final long serialVersionUID = 1L;

            @Override
            public WebPage createPage() {
                return new ABMProject(SearchProjectPage.this);
            }
        }));
        this.add(new PageLink("back", laPaginaDondeMeLlamaron));
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    protected void agregarSeccionResultado(final Form<SearchProject> formulario) {
        this.agregarSeccionResultadoGeneral(new AbstractListView("results", Project.class, Arrays.asList("name",
                "maxEffort", "currentEffort"), ABMProject.class));

    }

    @Override
    protected void armarFormulario(final Form<SearchProject> formulario) {
        formulario.add(new TextField<String>("searchByName"));
        // formulario.add(new TextField<String>("busquedaDireccion"));
    }

}
