package ar.edu.unq.dopplereffect.presentation.search;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;

import ar.edu.unq.dopplereffect.presentation.component.PageLink;
import ar.edu.unq.dopplereffect.presentation.component.ReflectionAjaxButton;
import ar.edu.unq.dopplereffect.presentation.pages.basic.NavigableWebPage;

/**
 * Representa una pagina de busquedas.
 */
public abstract class AbstractSearchPage2<T, S extends Search<T>> extends NavigableWebPage<S> {

    private static final String SUBMIT_BUTTON_ACTION_NAME = "search";

    /**
     * El componente que muestra los resultados via Ajax.
     */
    private Component ajaxSectionResult;

    public AbstractSearchPage2(final S model, final WebPage previousPage) {
        super(model, previousPage);
        this.createPage(this.createForm(this.getSearchFormWicketId()));
    }

    public Component getAjaxSectionResult() {
        return ajaxSectionResult;
    }

    /**
     * Agrega todos los elementos de la busqueda a la pagina.
     */
    protected void createPage(final Form<S> form) {
        this.buildForm(form);
        this.addButtons(form);
        this.addResultSection();
        this.add(form);
        this.add(this.getAjaxSectionResult());
    }

    /**
     * Agrega los botones necesarios para el panel de busqueda.
     */
    protected void addButtons(final Form<S> form) {
        form.add(new ReflectionAjaxButton<S>(this.getSubmitButtonWicketId(), SUBMIT_BUTTON_ACTION_NAME, form, this
                .getAjaxSectionResult()));
        form.add(this.getCreateObjectLink());
        this.add(new PageLink(this.getBackButtonWicketId(), this.getPreviousPage()));
    }

    /**
     * Construye la seccion de resultados, que luego sera actualizada via Ajax.
     */
    protected void addResultSection() {
        CustomListView<T, S> listView = this.getListView();
        WebMarkupContainer panel = new WebMarkupContainer(this.getResultSectionWicketId());
        // para que refresque por Ajax
        panel.setOutputMarkupId(true);
        listView.setResultSection(panel);
        listView.setSearch(this.getModelObject());
        listView.setParentPage(this);
        panel.add(listView);
        this.ajaxSectionResult = panel;
    }

    /**
     * Retorna el wicket:id del boton que lleva a la pagina anterior.
     */
    protected String getBackButtonWicketId() {
        return "back"; // default value
    }

    /**
     * Retorna el wicket:id del panel que contiene la lista de resultados.
     */
    protected String getResultSectionWicketId() {
        return "resultSection"; // default value
    }

    /**
     * Retorna el wicket:id del form de la pagina de busqueda.
     */
    protected String getSearchFormWicketId() {
        return "searchForm"; // default value
    }

    /**
     * Retorna el wicket:id del boton de submit del formulario de la busqueda.
     */
    protected String getSubmitButtonWicketId() {
        return "search"; // default value
    }

    /**
     * Encargado de construir el formulario, es decir, de agregar en el los
     * campos que sean necesarios dependiendo de la entidad por la que se este
     * buscando.
     */
    protected abstract void buildForm(Form<S> form);

    /**
     * Retorna un {@link PageLink} que conduce a la pagina para crear un item.
     */
    protected abstract PageLink getCreateObjectLink();

    /**
     * Retorna una {@link AbstractListView2} que describe la tabla de los items
     * a mostrarse.
     */
    protected abstract CustomListView<T, S> getListView();
}