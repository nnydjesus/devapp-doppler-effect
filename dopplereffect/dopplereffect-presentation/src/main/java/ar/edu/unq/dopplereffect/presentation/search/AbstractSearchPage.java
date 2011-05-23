package ar.edu.unq.dopplereffect.presentation.search;

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;

import ar.edu.unq.dopplereffect.presentation.component.AbstractWebPage;
import ar.edu.unq.dopplereffect.presentation.component.PageLink;
import ar.edu.unq.dopplereffect.presentation.component.SuperAjaxButton;
import ar.edu.unq.dopplereffect.presentation.pages.WebPageFactory;
import ar.edu.unq.dopplereffect.presentation.util.AjaxDataTablePage;
import ar.edu.unq.tpi.util.common.ReflectionUtils;

public class AbstractSearchPage<T extends Search> extends AbstractWebPage<T> {

    private Component ajaxSectionResult;

    private Class abm;

    private List<String> fields;

    /**
     * Constructor that is invoked when page is invoked without a session.
     * 
     * @param parameters
     *            Page parameters
     */
    public AbstractSearchPage(final WebPage parentPage, final T model, final List<String> fields, final Class abm) {
        super(parentPage, model);
        this.fields = fields;
        this.abm = abm;
        this.init(this.createForm("search" + this.getBeanName() + "Form", this.getModelObject()));
    }

    protected void init(final Form<T> formulario) {
        this.armarFormulario(formulario);
        this.addResultSection(formulario);
        this.addButton(formulario);
        this.add(formulario);
        this.add(this.getAjaxSectionResult());
    }

    protected void addButton(final Form<T> form) {
        form.add(new SuperAjaxButton<T>("search", form, this.getAjaxSectionResult()));
        form.add(new PageLink("new" + this.getBeanName(), new WebPageFactory() {
            private static final long serialVersionUID = 1L;

            @Override
            public WebPage createPage() {
                return ReflectionUtils.instanciate(abm, AbstractSearchPage.this);
            }
        }));
        this.add(new PageLink("back", this.getParentPage()));
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected void addResultSection(final Form<T> formulario) {
        this.addGeneralResultSection(new AjaxDataTablePage<T, AbstractSearchPage>("results", ((Search<T>) this
                .getDefaultModelObject()), fields, abm));
        // this.addGeneralResultSection(new AbstractListView("results",
        // ((Search<T>) this.getDefaultModelObject())
        // .getEntityType(), fields, abm));

    }

    protected void armarFormulario(final Form<T> formulario) {
        formulario.add(new TextField<String>("searchByName"));
        // formulario.add(new TextField<String>("busquedaDireccion"));
    }

    protected void addGeneralResultSection(final AjaxDataTablePage<T, AbstractSearchPage> listView) {
        WebMarkupContainer panel = new WebMarkupContainer("resultSecction");
        // para que refresque por Ajax
        panel.setOutputMarkupId(true);
        listView.setResultSection(panel);
        // listView.setSearch(this.getModelObject());
        listView.setParentPage(this);
        panel.add(listView.getAjaxdataTable());
        this.setAjaxSectionResult(panel);

    }

    protected String getBeanName() {
        return ((Search<T>) this.getDefaultModelObject()).getEntityType().getSimpleName();
    }

    public void setAjaxSectionResult(final Component ajaxSectionResult) {
        this.ajaxSectionResult = ajaxSectionResult;
    }

    public Component getAjaxSectionResult() {
        return ajaxSectionResult;
    }
}
