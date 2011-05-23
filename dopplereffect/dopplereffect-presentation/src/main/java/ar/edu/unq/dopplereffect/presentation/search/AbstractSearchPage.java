package ar.edu.unq.dopplereffect.presentation.search;

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;

import ar.edu.unq.dopplereffect.presentation.component.AbstractWebPage;
import ar.edu.unq.dopplereffect.presentation.component.PageLink;
import ar.edu.unq.dopplereffect.presentation.component.ReflectionAjaxButton;
import ar.edu.unq.dopplereffect.presentation.pages.basic.WebPageFactory;
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
        this.init(this.createForm(getFormWicketID(), this.getModelObject()));
    }


    protected void init(final Form<T> formulario) {
        this.armarFormulario(formulario);
        this.addResultSection(formulario);
        this.addButton(formulario);
        this.add(formulario);
        this.add(this.getAjaxSectionResult());
    }

    protected void addButton(final Form<T> form) {
        form.add(new ReflectionAjaxButton(getSubmitButtonWicketId(), form, this.getAjaxSectionResult()));
        form.add(new PageLink(getNewFromBeanWicketId(), new WebPageFactory() {
            private static final long serialVersionUID = 1L;

            @Override
            public WebPage createPage() {
                return ReflectionUtils.instanciate(abm, AbstractSearchPage.this);
            }
        }));
        this.add(new PageLink(getBackButtonWicketId(), this.getParentPage()));
    }


    @SuppressWarnings({ "unchecked" })
    protected void addResultSection(final Form<T> formulario) {
        this.addGeneralResultSection(new AjaxDataTablePage<T, WebPage>(getTableWicketId(), getSortName(), ((Search<T>) this
                .getDefaultModelObject()), fields, abm));
    }


    protected void armarFormulario(final Form<T> formulario) {
        formulario.add(new TextField<String>(getDefaultImputSearchWicketId()));
        // formulario.add(new TextField<String>("busquedaDireccion"));
    }



    protected void addGeneralResultSection(final AjaxDataTablePage<T, WebPage> listView) {
		WebMarkupContainer panel = new WebMarkupContainer(getResultSectionWicketId());
        // para que refresque por Ajax
        panel.setOutputMarkupId(true);
        listView.setResultSection(panel);
        // listView.setSearch(this.getModelObject());
        listView.setParentPage(this);
        panel.add(listView.getAjaxdataTable());
        this.setAjaxSectionResult(panel);

    }
    
	protected String getFormWicketID() {
		return getSubmitButtonWicketId() + this.getBeanName() + "Form";
	}
	
	protected String getBackButtonWicketId() {
		return "back";
	}
	
	protected String getResultSectionWicketId() {
		return "resultSecction";
	}
	
	protected String getTableWicketId() {
		return "results";
	}

	protected String getSubmitButtonWicketId() {
		return "search";
	}


	protected String getNewFromBeanWicketId() {
		return "new" + this.getBeanName();
	}


	protected String getDefaultImputSearchWicketId() {
		return "searchByName";
	}
	
	protected String getSortName() {
		return "name";
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
