package ar.edu.unq.dopplereffect.presentation.search;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;

import ar.edu.unq.dopplereffect.presentation.grid.GridPanel;
import ar.edu.unq.dopplereffect.presentation.pages.basic.WebComponentFactory;
import ar.edu.unq.dopplereffect.presentation.panel.EntityPanel;
import ar.edu.unq.dopplereffect.presentation.panel.upload.UploadPanel;
import ar.edu.unq.dopplereffect.presentation.panel.utils.AbstractCallbackPanel;
import ar.edu.unq.dopplereffect.presentation.panel.utils.AbstractPanel;
import ar.edu.unq.dopplereffect.presentation.panel.utils.PanelCallbackLink;
import ar.edu.unq.dopplereffect.presentation.util.AjaxCallBack;
import ar.edu.unq.dopplereffect.presentation.util.AjaxDataTable;
import ar.edu.unq.dopplereffect.presentation.util.ITable;
import ar.edu.unq.dopplereffect.presentation.util.ReflectionAjaxButton;
import ar.edu.unq.dopplereffect.service.DTO;
import ar.edu.unq.tpi.util.common.ReflectionUtils;

@SuppressWarnings("rawtypes")
public abstract class AbstractSearchPanel<T extends SearchModel<? extends DTO>> extends AbstractCallbackPanel<T> {

    private static final long serialVersionUID = 1L;

    public static final String UNCHECKED = "unchecked";

    /* ************************ INSTANCE VARIABLES ************************ */

    private WebMarkupContainer ajaxSectionResult;

    private Class<Component> entityPanel;

    private List<String> fields;

    private Model<String> modelSearchByName;

    /* *************************** CONSTRUCTORS *************************** */

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

    @SuppressWarnings(UNCHECKED)
    public AbstractSearchPanel(final String id, final AjaxCallBack<Component> aCallback,
            final AbstractPanel<?> backPanel, final T model, final List<String> fields, final Class abm) {
        super(id, model);
        this.setFields(fields);
        this.setAbm(abm);
        super.init(aCallback, backPanel);
        this.init(this.createForm(this.getFormWicketId()));
    }

    /* **************************** ACCESSORS ***************************** */

    public WebMarkupContainer getAjaxSectionResult() {
        return ajaxSectionResult;
    }

    public void setAjaxSectionResult(final WebMarkupContainer ajaxSectionResult) {
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

    public Model<String> getModelSearchByName() {
        return modelSearchByName;
    }

    public void setModelSearchByName(final Model<String> modelSearchByName) {
        this.modelSearchByName = modelSearchByName;
    }

    @SuppressWarnings(UNCHECKED)
    protected void init(final Form<T> formulario) {
        this.addInputSearch(formulario);
        this.addResultSection(this.selectITable());
        this.addButtons(formulario);
        this.add(formulario);
        this.add(this.getAjaxSectionResult());
        this.add(new UploadPanel("uploadPanel", this.getModelObject()));
    }

    protected void addButtons(final Form<T> form) {
        form.add(new ReflectionAjaxButton<AbstractSearchPanel<T>>(this.getSubmitButtonWicketId(), this, this
                .getAjaxSectionResult(), new StringResourceModel("searchButton", new Model<String>(""))));
        this.addNewEntityButton(form);
        this.add(new PanelCallbackLink(this.getBackButtonWicketId(), this.getCallback(), this.getBackPanel(),
                new StringResourceModel("backButton", new Model<String>(""))));
    }

    protected void addNewEntityButton(final Form<T> form) {
        form.add(new PanelCallbackLink(this.getNewFromBeanWicketId(), this.getCallback(), new StringResourceModel(
                "newEntityButton", new Model<String>("")), new WebComponentFactory<Component>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Component createPage() {
                return AbstractSearchPanel.this.createEntityPanel();
            }
        }));
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

    private AjaxDataTable getNewAjaxTable() {
        AjaxDataTable ajaxDataTablePage = this.createAjaxTable();
        ajaxDataTablePage.init();
        return ajaxDataTablePage;
    }

    @SuppressWarnings(UNCHECKED)
    protected AjaxDataTable createAjaxTable() {
        return new AjaxDataTable(this, this.getTableWicketId(), this.getSortName(),
                ((SearchModel) this.getDefaultModelObject()), this.getCallback(), this.getFields(), this.getAbmClass());
    }

    @SuppressWarnings(UNCHECKED)
    protected GridPanel createAjaxGrid() {
        return new GridPanel(this.getTableWicketId(), this.getModelObject(), this.getModelObject().getEntityType(),
                this.getFields());
    }

    protected void addInputSearch(final Form<T> form) {
        this.setModelSearchByName(new Model<String>(""));
        form.add(new TextField<String>(this.getDefaultInputSearchWicketId(), this.getModelSearchByName()));
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

    public Boolean cantEdit() {
        return true;
    }

    public Boolean cantDelete() {
        return true;
    }

    public void search() {
        if (this.getModelSearchByName() == null || StringUtils.isBlank(this.getModelSearchByName().getObject())) {
            this.getModelObject().search();
        } else {
            this.getModelObject().searchByName(this.getModelSearchByName().getObject());
        }
        if (!this.getModelObject().getResults().isEmpty()) {
            ajaxSectionResult.setVisible(true);
        }
    }

    protected String getBeanName() {
        return ((SearchModel) this.getDefaultModelObject()).getEntityType().getSimpleName();
    }
}
