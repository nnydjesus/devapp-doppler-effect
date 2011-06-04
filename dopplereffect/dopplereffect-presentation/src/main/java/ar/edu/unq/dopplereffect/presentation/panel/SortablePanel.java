package ar.edu.unq.dopplereffect.presentation.panel;

import java.io.Serializable;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.odlabs.wiquery.ui.sortable.SortableAjaxBehavior.SortedEvent;

import ar.edu.unq.dopplereffect.presentation.panel.utils.SortableAjax;

/**
 */
public class SortablePanel<T extends Serializable> extends Panel {

    private static final long serialVersionUID = 1L;

    private String message = "No sorting done yet!";

    private WebMarkupContainer sortableW;

    private boolean showPlaceholder = false;

    private boolean restrictToYAxis = false;

    private SortableAjax<WebMarkupContainer> sortable;

    private List<T> list;

    /**
     * Constructor that is invoked when page is invoked without a session.
     * 
     * @param parameters
     *            Page parameters
     */
    public SortablePanel(final String idd, final List<T> list, final boolean onAction) {
        super(idd);
        this.list = list;
        this.setSortable(new SortableAjax<WebMarkupContainer>(SortedEvent.RECEIVE, SortedEvent.REMOVE) {
            private static final long serialVersionUID = 1L;

            @SuppressWarnings("unchecked")
            @Override
            public void onReceive(final WebMarkupContainer sortedComponent, final int index,
                    final Component parentSorted, final AjaxRequestTarget ajaxRequestTarget) {
                if (onAction) {
                    Label label = (Label) sortedComponent.get("item");
                    SortablePanel.this.getList().add((T) label.getDefaultModelObject());
                    // ajaxRequestTarget.addComponent(SortablePanel.this);
                }

            }

            @Override
            public void onRemove(final WebMarkupContainer sortedComponent, final AjaxRequestTarget ajaxRequestTarget) {
                if (onAction) {

                    Label label = (Label) sortedComponent.get("item");
                    SortablePanel.this.getList().remove(label.getDefaultModelObject());
                    // ajaxRequestTarget.addComponent(SortablePanel.this);
                }

            }

        });
        this.setSortableW(new WebMarkupContainer("sortable"));
        this.getSortableW().setOutputMarkupId(true).add(this.getSortable());
        this.getSortable().setConnectWith(".sortable-example");
        this.add(this.getSortableW());

        final ListView<T> listView = new ListView<T>("items", list) {
            private static final long serialVersionUID = 1L;

            /*
             * (non-Javadoc)
             * 
             * @see
             * org.apache.wicket.markup.html.list.ListView#populateItem(org.
             * apache.wicket.markup.html.list.ListItem)
             */
            @Override
            protected void populateItem(final ListItem<T> item) {
                Label label = new Label("item", item.getModel());
                label.setOutputMarkupId(true);
                item.add(label);
                item.setOutputMarkupId(true);
            }
        };

        listView.setOutputMarkupId(true);

        this.getSortableW().add(listView);

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public boolean isShowPlaceholder() {
        return showPlaceholder;
    }

    public void setShowPlaceholder(final boolean showPlaceholder) {
        this.showPlaceholder = showPlaceholder;
    }

    public boolean isRestrictToYAxis() {
        return restrictToYAxis;
    }

    public void setRestrictToYAxis(final boolean restrictToYAxis) {
        this.restrictToYAxis = restrictToYAxis;
    }

    public void setList(final List<T> list) {
        this.list = list;
    }

    public List<T> getList() {
        return list;
    }

    public void setSortable(final SortableAjax<WebMarkupContainer> sortable) {
        this.sortable = sortable;
    }

    public SortableAjax<WebMarkupContainer> getSortable() {
        return sortable;
    }

    public void setSortableW(final WebMarkupContainer sortableW) {
        this.sortableW = sortableW;
    }

    public WebMarkupContainer getSortableW() {
        return sortableW;
    }
}
