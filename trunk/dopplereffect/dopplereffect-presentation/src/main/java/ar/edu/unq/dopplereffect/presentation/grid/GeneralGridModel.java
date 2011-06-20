package ar.edu.unq.dopplereffect.presentation.grid;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import ar.edu.unq.dopplereffect.presentation.panel.AjaxActionPanel;
import ar.edu.unq.dopplereffect.presentation.search.SearchModel;
import ar.edu.unq.dopplereffect.presentation.util.AjaxCallBack;

import com.wiquery.plugins.jqgrid.model.GridColumnModel;
import com.wiquery.plugins.jqgrid.model.GridModel;
import com.wiquery.plugins.jqgrid.model.ICellPopulator;
import com.wiquery.plugins.jqgrid.model.PropertyPopulator;

/**
 */
public class GeneralGridModel<T extends Serializable> extends GridModel<T> {
    private static final long serialVersionUID = 1L;

    /* ************************ INSTANCE VARIABLES ************************ */

    private AjaxCallBack<Component> callBack;

    private SearchModel<T> search;

    private Class<? extends Component> entityPanel;

    private Panel parentPanel;

    /* *************************** CONSTRUCTORS *************************** */

    // public GeneralGridModel(final Class<T> beanClass, final Panel parent,
    // final String id, final String sortName,
    // final SearchModel<T> aSearch, final AjaxCallBack<Component> aCallBack,
    // final List<String> fields,
    // final Class<? extends Component> abm) {
    public GeneralGridModel(final Class<T> beanClass, final List<String> fields) {
        super(beanClass);

        // this.parentPanel = parent;
        // this.entityPanel = abm;
        // this.search = aSearch;

        // callBack = aCallBack;
        this.setCaption(beanClass.getSimpleName());

        this.addColumns(fields);

        this.setScroll(10);
        this.setRowNum(10);
        this.setPagerpos(HorizontalPosition.left);
    }

    /* **************************** ACCESSORS ***************************** */

    public void setCallBack(final AjaxCallBack<Component> callBack) {
        this.callBack = callBack;
    }

    public AjaxCallBack<Component> getCallBack() {
        return callBack;
    }

    public void setEntityPanel(final Class<? extends Component> entityPanel) {
        this.entityPanel = entityPanel;
    }

    public Class<? extends Component> getEntityPanel() {
        return entityPanel;
    }

    public void setSearch(final SearchModel<T> search) {
        this.search = search;
    }

    public SearchModel<T> getSearch() {
        return search;
    }

    public void setParentPanel(final Panel parentPanel) {
        this.parentPanel = parentPanel;
    }

    public Panel getParentPanel() {
        return parentPanel;
    }

    /* ************************* PROTECTED METHODS ************************** */

    protected void addremoveColumn() {
        this.addColumnModel(new GridColumnModel<T>("", "", new Model<String>("Delete"), 50) {
            private static final long serialVersionUID = 1L;

            @Override
            public ICellPopulator<T> getCellPopulator() {
                return new PropertyPopulator<T>("") {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void populateItem(final Item<ICellPopulator<T>> cellItem, final String componentId,
                            final int row, final int col, final IModel<T> rowModel) {
                        cellItem.add(new AjaxActionPanel(componentId, "delete.png") {

                            private static final long serialVersionUID = 1L;

                            @Override
                            public void onAction(final AjaxRequestTarget target) {
                                // GeneralGridModel.this.search.remove(rowModel.getObject());
                                // target.addComponent(GeneralGridModel.this.getAjaxdataTable());
                            }
                        });
                    }
                };
            }
        });
    }

    protected void addEditColumn() {
        this.addColumnModel(new GridColumnModel<T>("", "", new Model<String>("Edit"), 50) {
            private static final long serialVersionUID = 1L;

            @Override
            public ICellPopulator<T> getCellPopulator() {
                return new PropertyPopulator<T>("") {

                    private static final long serialVersionUID = 1L;

                    @Override
                    public void populateItem(final Item<ICellPopulator<T>> cellItem, final String componentId,
                            final int row, final int col, final IModel<T> rowModel) {
                        cellItem.add(new AjaxActionPanel(componentId, "edit.png") {

                            private static final long serialVersionUID = 1L;

                            @Override
                            public void onAction(final AjaxRequestTarget target) {
                                // Component page =
                                // ReflectionUtils.instanciate(GeneralGridModel.this.entityPanel,
                                // GeneralGridModel.this.parentPanel.getId(),
                                // GeneralGridModel.this.getPagerpos(),
                                // GeneralGridModel.this.getObject(), true);
                            }
                        });
                    }
                };
            }
        });
    }

    protected void addColumns(final List<String> fields) {
        for (String field : fields) {
            this.addColumnModel(this.createColumnModel(field));
        }
        this.addEditColumn();
        this.addremoveColumn();
    }

    protected GridColumnModel<T> createColumnModel(final String field) {
        return new GridColumnModel<T>(field, field, new Model<String>(StringUtils.capitalize(field)), 100);

    }

    /* ****************** EQUALS, HASHCODE, TOSTRING ********************** */

    @Override
    public int hashCode() {
        int prime = 31;
        int result = super.hashCode();
        result = prime * result + (search == null ? 0 : search.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        GeneralGridModel<?> other = (GeneralGridModel<?>) obj;
        if (search == null) {
            if (other.search != null) {
                return false;
            }
        } else if (!search.equals(other.search)) {
            return false;
        }
        return true;
    }
}
