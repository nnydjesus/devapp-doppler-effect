package ar.edu.unq.dopplereffect.presentation.grid;

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.Panel;

import ar.edu.unq.dopplereffect.presentation.search.AbstractSearchPanel;
import ar.edu.unq.dopplereffect.presentation.util.GenericSortableDataProvider;
import ar.edu.unq.dopplereffect.presentation.util.ITable;
import ar.edu.unq.dopplereffect.presentation.util.Model;
import ar.edu.unq.dopplereffect.service.DTO;

/**
 * 
 */
public class GridPanel<T extends DTO> extends Panel implements ITable {

    private static final long serialVersionUID = 1L;

    private boolean scoll = false;

    /**
     * @param id
     */
    public GridPanel(final String id, final Object defaultmodel, final Class<T> beanClass, final List<String> fields) {
        super(id, new Model<Object>(defaultmodel));

        GeneralGridModel<T> model = new GeneralGridModel<T>(beanClass, fields);
        GeneralGrid<T> grid = new GeneralGrid<T>("grid", model, new GenericSortableDataProvider<T>(id,
                this.getDefaultModelObject(), "name"));

        this.add(grid);
    }

    public boolean isScoll() {
        return scoll;
    }

    public void setScoll(final boolean scoll) {
        this.scoll = scoll;
    }

    @Override
    public Component getSortableAjaxWicket() {
        return this;
    }

    @Override
    public void setParentPage(final AbstractSearchPanel<?> panel) {
        // TODO
        this.getClass();
    }

}
