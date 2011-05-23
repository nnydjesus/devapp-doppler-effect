package ar.edu.unq.dopplereffect.presentation.search;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;

import ar.edu.unq.dopplereffect.presentation.pages.basic.AbstractWebPage;

public abstract class AbstractSearchPage<T> extends AbstractWebPage<T> {

    private Component ajaxSectionResult;

    /**
     * Constructor that is invoked when page is invoked without a session.
     * 
     * @param parameters
     *            Page parameters
     */
    public AbstractSearchPage(final T t) {
        super(t);
    }

    protected void init(final Form<T> formulario) {
        this.armarFormulario(formulario);
        this.agregarSeccionResultado(formulario);
        this.addButton(formulario);
        this.add(formulario);
        this.add(this.getAjaxSectionResult());
    }

    protected abstract void addButton(Form<T> formulario);

    protected abstract void agregarSeccionResultado(Form<T> formulario);

    protected abstract void armarFormulario(Form<T> formulario);

    protected void agregarSeccionResultadoGeneral(final AbstractListView listView) {
        WebMarkupContainer panel = new WebMarkupContainer("resultSecction");
        // para que refresque por Ajax
        panel.setOutputMarkupId(true);
        listView.setResultSection(panel);
        listView.setSearch(this.getModelObject());
        listView.setParentPage(this);
        panel.add(listView);
        this.setAjaxSectionResult(panel);

    }

    public void setAjaxSectionResult(final Component ajaxSectionResult) {
        this.ajaxSectionResult = ajaxSectionResult;
    }

    public Component getAjaxSectionResult() {
        return ajaxSectionResult;
    }
}
