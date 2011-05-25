package ar.edu.unq.dopplereffect.presentation.search;

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;

import ar.edu.unq.dopplereffect.presentation.pages.basic.WebComponentFactory;
import ar.edu.unq.dopplereffect.presentation.panel.utils.AbstractCallbackPanel;
import ar.edu.unq.dopplereffect.presentation.panel.utils.PanelCallbackLink;
import ar.edu.unq.dopplereffect.presentation.util.AjaxDataTablePage;
import ar.edu.unq.dopplereffect.presentation.util.CallBack;
import ar.edu.unq.dopplereffect.presentation.util.ReflectionAjaxButton;
import ar.edu.unq.tpi.util.common.ReflectionUtils;

public class AbstractSearchPanel<T extends Search> extends AbstractCallbackPanel<T> {
    private static final long serialVersionUID = 1L;

    private Component ajaxSectionResult;

    private Class<Component> entityPanel;

    private List<String> fields;

    /**
     * Constructor that is invoked when page is invoked without a session.
     * 
     * @param parameters
     *            Page parameters
     */
    public AbstractSearchPanel(final String id, final CallBack<Component> parentPage, final T model,
            final List<String> fields, final Class abm) {
        super(id, parentPage, model);
        this.setFields(fields);
        this.setAbm(abm);
        this.init(this.createForm(this.getFormWicketID()));
    }

    protected void init(final Form<T> formulario) {
        this.armarFormulario(formulario);
        this.addResultSection();
        this.addButton(formulario);
        this.add(formulario);
        this.add(this.getAjaxSectionResult());
    }

    protected void addButton(final Form<T> form) {
        form.add(new ReflectionAjaxButton<T>(this.getSubmitButtonWicketId(), form, this.getAjaxSectionResult()));
        form.add(new PanelCallbackLink(this.getNewFromBeanWicketId(), this.getCallback(),
                new WebComponentFactory<Component>() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public Component createPage() {
                        return ReflectionUtils.instanciate(AbstractSearchPanel.this.getAbm(),
                                AbstractSearchPanel.this.getId(), AbstractSearchPanel.this);
                    }
                }));
        this.add(new PanelCallbackLink(this.getBackButtonWicketId(), this.getCallback(), (Component) null));
    }

    @SuppressWarnings({ "unchecked" })
    protected void addResultSection() {
        this.addGeneralResultSection(new AjaxDataTablePage<T, Panel>(this.getTableWicketId(), this.getSortName(),
                ((Search<T>) this.getDefaultModelObject()), this.getCallback(), this.getFields(), this.getAbm()));
    }

    protected void armarFormulario(final Form<T> formulario) {
        formulario.add(new TextField<String>(this.getDefaultImputSearchWicketId()));
        // formulario.add(new TextField<String>("busquedaDireccion"));
    }

    protected void addGeneralResultSection(final AjaxDataTablePage<T, Panel> listView) {
        WebMarkupContainer panel = new WebMarkupContainer(this.getResultSectionWicketId());
        // para que refresque por Ajax
        panel.setOutputMarkupId(true);
        listView.setResultSection(panel);
        // listView.setSearch(this.getModelObject());
        listView.setParentPage(this);
        panel.add(listView.getAjaxdataTable());
        this.setAjaxSectionResult(panel);
    }

    protected String getFormWicketID() {
        return this.getSubmitButtonWicketId() + this.getBeanName() + "Form";
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

    @SuppressWarnings("unchecked")
    protected String getBeanName() {
        return ((Search<T>) this.getDefaultModelObject()).getEntityType().getSimpleName();
    }

    public void setAjaxSectionResult(final Component ajaxSectionResult) {
        this.ajaxSectionResult = ajaxSectionResult;
    }

    public Component getAjaxSectionResult() {
        return ajaxSectionResult;
    }

    public void setAbm(final Class<Component> abm) {
        this.setEntityPanel(abm);
    }

    public Class<Component> getAbm() {
        return this.getEntityPanel();
    }

    public void setFields(final List<String> fields) {
        this.fields = fields;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setEntityPanel(final Class<Component> entityPanel) {
        this.entityPanel = entityPanel;
    }

    public Class<Component> getEntityPanel() {
        return entityPanel;
    }
}
