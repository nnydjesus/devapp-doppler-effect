package ar.edu.unq.dopplereffect.presentation.grid;

import java.io.Serializable;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;

import com.wiquery.plugins.jqgrid.component.Grid;
import com.wiquery.plugins.jqgrid.component.event.OnGridCompleteAjaxEvent;
import com.wiquery.plugins.jqgrid.component.event.OnHeaderClickAjaxEvent;
import com.wiquery.plugins.jqgrid.component.event.OnHeaderClickAjaxEvent.GridState;
import com.wiquery.plugins.jqgrid.component.event.OnPagingAjaxEvent;
import com.wiquery.plugins.jqgrid.component.event.OnPagingAjaxEvent.PageButton;
import com.wiquery.plugins.jqgrid.component.event.OnResizeStopAjaxEvent;
import com.wiquery.plugins.jqgrid.component.event.OnRightClickRowAjaxEvent;
import com.wiquery.plugins.jqgrid.component.event.OnSelectRowAjaxEvent;
import com.wiquery.plugins.jqgrid.component.event.OnSortColAjaxEvent;
import com.wiquery.plugins.jqgrid.component.event.OndblClickRowAjaxEvent;
import com.wiquery.plugins.jqgrid.model.GridModel;
import com.wiquery.plugins.jqgrid.model.IColumn;
import com.wiquery.plugins.jqgrid.model.SortOrder;

/**
 * Clase que represena a una grilla skineada con jquery... TODO: todaiva le
 * falta pulir... no muestra las filas!
 */
@SuppressWarnings("unused")
public class GeneralGrid<T extends Serializable> extends Grid<T> {
    private static final long serialVersionUID = 1L;

    private Component resultSection;

    public GeneralGrid(final String arg0, final GridModel<T> arg1, final IDataProvider<T> arg2) {
        super(arg0, arg1, arg2);
        this.addEventListener();
        this.setOutputMarkupId(true);
    }

    public GeneralGrid(final String arg0, final GridModel<T> arg1) {
        this(arg0, arg1, null);
    }

    public GeneralGrid(final String arg0) {
        this(arg0, null, null);
    }

    private void addEventListener() {

        this.addEvent(new OnSelectRowAjaxEvent<T>() {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSelectRow(final AjaxRequestTarget target, final int row, final IModel<T> rowModel,
                    final boolean status) {
                GeneralGrid.this.onSelectedRow(target, row, rowModel, status);
            }
        });

        this.addEvent(new OnGridCompleteAjaxEvent<T>() {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onGridComplete(final AjaxRequestTarget target, final Grid<T> grid) {
                GeneralGrid.this.onGridCompleteAjaxEvent(target, grid);
            }

        });
        this.addEvent(new OndblClickRowAjaxEvent<T>() {
            private static final long serialVersionUID = 1L;

            @Override
            protected void ondblClickRow(final AjaxRequestTarget target, final int row, final int col,
                    final IModel<T> rowModel) {
                GeneralGrid.this.onClickRowAjaxevent(target, row, col, rowModel);
            }
        });

        this.addEvent(new OnRightClickRowAjaxEvent<T>() {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onRightClickRow(final AjaxRequestTarget target, final int row, final int col,
                    final IModel<T> rowModel) {
                GeneralGrid.this.onRightclickRowAjaxEvent(target, row, col, rowModel);
            }

        });

        this.addEvent(new OnHeaderClickAjaxEvent<T>() {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onHeaderClick(final AjaxRequestTarget target, final Grid<T> grid,
                    final com.wiquery.plugins.jqgrid.component.event.OnHeaderClickAjaxEvent.GridState gridState) {
                GeneralGrid.this.onClickHeaderAjaxEvent(target, grid, gridState);
            }

        });

        this.addEvent(new OnSortColAjaxEvent<T>() {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSortCol(final AjaxRequestTarget target, final Grid<T> grid, final IColumn<T> column,
                    final int col, final String sortProperty, final SortOrder order) {
                GeneralGrid.this.onsortAjaxEvent(target, grid, column, col, sortProperty, order);
            }
        });

        this.addEvent(new OnPagingAjaxEvent<T>() {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onPaging(final AjaxRequestTarget target, final Grid<T> grid, final PageButton button) {
                GeneralGrid.this.onPaginAjaxEvent(target, grid, button);
            }

        });

        this.addEvent(new OnResizeStopAjaxEvent<T>() {
            private static final long serialVersionUID = 1L;

            @Override
            protected void resizeStop(final AjaxRequestTarget target, final Grid<T> grid, final IColumn<T> column,
                    final int newwidth) {
                GeneralGrid.this.onResizeAjaxEvent(target, grid, column, newwidth);
            }

        });
    }

    // ///////////////////// HANDLERS//////////////////////////

    protected void onResizeAjaxEvent(final AjaxRequestTarget target, final Grid<T> grid, final IColumn<T> column,
            final int newwidth) {
        this.getClass();
    }

    protected void onPaginAjaxEvent(final AjaxRequestTarget target, final Grid<T> grid, final PageButton button) {
        this.getClass();
    }

    protected void onsortAjaxEvent(final AjaxRequestTarget target, final Grid<T> grid, final IColumn<T> column,
            final int col, final String sortProperty, final SortOrder order) {
        this.getClass();
    }

    protected void onClickHeaderAjaxEvent(final AjaxRequestTarget target, final Grid<T> grid, final GridState gridState) {
        this.getClass();
    }

    protected void onRightclickRowAjaxEvent(final AjaxRequestTarget target, final int row, final int col,
            final IModel<T> rowModel) {
        this.getClass();
    }

    protected void onClickRowAjaxevent(final AjaxRequestTarget target, final int row, final int col,
            final IModel<T> rowModel) {
        this.getClass();
    }

    protected void onGridCompleteAjaxEvent(final AjaxRequestTarget target, final Grid<T> grid) {
        this.getClass();
    }

    protected void onSelectedRow(final AjaxRequestTarget target, final int row, final IModel<T> rowModel,
            final boolean status) {
        this.getClass();
    }

    public void setResultSection(final Component resultSection) {
        this.resultSection = resultSection;
    }

    public Component getResultSection() {
        return resultSection;
    }
}
