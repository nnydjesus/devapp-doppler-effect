package ar.edu.unq.dopplereffect.presentation.search;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;

import ar.edu.unq.dopplereffect.presentation.pages.basic.WebComponentFactory;
import ar.edu.unq.dopplereffect.presentation.panel.EntityPanel;
import ar.edu.unq.dopplereffect.presentation.panel.utils.AbstractCallbackPanel;
import ar.edu.unq.dopplereffect.presentation.panel.utils.AbstractPanel;
import ar.edu.unq.dopplereffect.presentation.panel.utils.PanelCallbackLink;
import ar.edu.unq.dopplereffect.presentation.util.AjaxCallBack;
import ar.edu.unq.dopplereffect.presentation.util.AjaxDataTablePage;
import ar.edu.unq.dopplereffect.presentation.util.ITable;
import ar.edu.unq.dopplereffect.presentation.util.ReflectionAjaxButton;
import ar.edu.unq.dopplereffect.service.DTO;
import ar.edu.unq.tpi.util.common.ReflectionUtils;

@SuppressWarnings("rawtypes")
public abstract class AbstractSearchPanel<T extends SearchModel<? extends DTO>> extends AbstractCallbackPanel<T> {
    private static final long serialVersionUID = 1L;

    private Component ajaxSectionResult;

    private Class<Component> entityPanel;

    private List<String> fields;

    private Model<String> modelSearchByName;

    /**
     * Constructor that is invoked when page is invoked without a session.
     * 
     * @param parameters
     *            Page parameters
     */
    public AbstractSearchPanel(final String id, final AjaxCallBack<Component> parentPage, final T model,
            final List<String> fields, final Class abm) {
        this(id, parentPage, null, model, fields, abm);
    }

    @SuppressWarnings("unchecked")
    public AbstractSearchPanel(final String id, final AjaxCallBack<Component> aCallback,
            final AbstractPanel<?> backPanel, final T model, final List<String> fields, final Class abm) {
        super(id, model);
        this.setFields(fields);
        this.setAbm(abm);
        super.init(aCallback, backPanel);
        this.init(this.createForm(this.getFormWicketId()));
    }

    protected void init(final Form<T> formulario) {
        this.buildForm(formulario);
        this.addResultSection(this.selectITable());
        this.addButtons(formulario);
        this.add(formulario);
        this.add(this.getAjaxSectionResult());
    }

    protected void addButtons(final Form<T> form) {
        form.add(new ReflectionAjaxButton<AbstractSearchPanel<T>>(this.getSubmitButtonWicketId(), this, this
                .getAjaxSectionResult(), new StringResourceModel("searchButton", new Model<String>(""))));
        form.add(new PanelCallbackLink(this.getNewFromBeanWicketId(), this.getCallback(), new StringResourceModel(
                "newEntityButton", new Model<String>("")), new WebComponentFactory<Component>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Component createPage() {
                return AbstractSearchPanel.this.createEntityPanel();
            }
        }));
        this.add(new PanelCallbackLink(this.getBackButtonWicketId(), this.getCallback(), this.getBackPanel(),
                new StringResourceModel("backButton", new Model<String>(""))));
    }

    protected void addResultSection(final ITable iTable) {
        this.addGeneralResultSection(iTable);
    }

    /**
     * Selected in *createAjaxTable() *createListView()
     */
    protected ITable selectITable() {
        return this.getNewAjaxTable();
    }

    protected CustomListView<T, Class<Component>> createListView() {
        return new CustomListView<T, Class<Component>>(this, this.getTableWicketId(), this.getFields(),
                this.getAbmClass(), this.getCallback());
    }

    private AjaxDataTablePage getNewAjaxTable() {
        AjaxDataTablePage ajaxDataTablePage = this.createAjaxTable();
        ajaxDataTablePage.init();
        return ajaxDataTablePage;
    }

    @SuppressWarnings("unchecked")
    protected AjaxDataTablePage createAjaxTable() {
        return new AjaxDataTablePage(this, this.getTableWicketId(), this.getSortName(),
                ((SearchModel) this.getDefaultModelObject()), this.getCallback(), this.getFields(), this.getAbmClass());
    }

    // protected GridPanel<T> createAjaxGrid() {
    // return new GridPanel<T>(this.getTableWicketId(),
    // this.getModelObject().getEntityType(), this.getFields());
    // }

    protected void buildForm(final Form<T> form) {
        modelSearchByName = new Model<String>("");
        form.add(new TextField<String>(this.getDefaultInputSearchWicketId(), modelSearchByName));
    }

    protected void addGeneralResultSection(final ITable listView) {
        WebMarkupContainer panel = new WebMarkupContainer(this.getResultSectionWicketId());
        panel.setOutputMarkupId(true);
        listView.setParentPage(this);
        panel.add(listView.getSortableAjaxWicket());
        this.setAjaxSectionResult(panel);
    }

    protected Component createEntityPanel() {
        EntityPanel<? extends DTO> newEntityPanel = (EntityPanel<?>) ReflectionUtils.instanciate(
                AbstractSearchPanel.this.getAbmClass(), this.getId());
        newEntityPanel.init(this.getCallback(), this);
        return newEntityPanel;
    }

    protected String getFormWicketId() {
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

    protected String getDefaultInputSearchWicketId() {
        return "searchByName";
    }

    protected String getSortName() {
        return "name";
    }

    @Override
    public void reset() {
        super.reset();
        ajaxSectionResult.setVisible(false);
    }

    public void search() {
        if (modelSearchByName != null && !StringUtils.isBlank(modelSearchByName.getObject())) {
            this.getModelObject().searchByName(modelSearchByName.getObject());
        } else {
            this.getModelObject().search();
        }
        ajaxSectionResult.setVisible(true);
    }

    protected String getBeanName() {
        return ((SearchModel) this.getDefaultModelObject()).getEntityType().getSimpleName();
    }

    public Component getAjaxSectionResult() {
        return ajaxSectionResult;
    }

    public void setAjaxSectionResult(final Component ajaxSectionResult) {
        this.ajaxSectionResult = ajaxSectionResult;
    }

    public Class<Component> getAbmClass() {
        return this.getEntityPanel();
    }

    public void setAbm(final Class<Component> abm) {
        this.setEntityPanel(abm);
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(final List<String> fields) {
        this.fields = fields;
    }

    public Class<Component> getEntityPanel() {
        return entityPanel;
    }

    public void setEntityPanel(final Class<Component> entityPanel) {
        this.entityPanel = entityPanel;
    }

}
