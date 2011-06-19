package ar.edu.unq.dopplereffect.presentation.panel.utils;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.odlabs.wiquery.ui.sortable.SortableAjaxBehavior;

/**
 */
public class SortableAjax<T extends Component> extends SortableAjaxBehavior<T> {
    private static final long serialVersionUID = 1L;

    public SortableAjax(final SortedEvent... callbacks) {
        super(callbacks);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.odlabs.wiquery.ui.sortable.SortableAjaxBehavior#onReceive(org.apache
     * .wicket.Component, int, org.apache.wicket.Component,
     * org.apache.wicket.ajax.AjaxRequestTarget)
     */
    @Override
    public void onReceive(final T sortedComponent, final int index, final Component parentSortedComponent,
            final AjaxRequestTarget ajaxRequestTarget) {
        // ajaxRequestTarget.appendJavascript("alert('received  : " +
        // sortedComponent.getMarkupId() + " - "
        // + sortedComponent.getDefaultModelObject().toString() + " - index : "
        // + index + "')");
        // ajaxRequestTarget.appendJavascript("alert('received from  : " +
        // parentSortedComponent.getMarkupId() + "')");
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.odlabs.wiquery.ui.sortable.SortableAjaxBehavior#onUpdate(org.apache
     * .wicket.Component, int, org.apache.wicket.ajax.AjaxRequestTarget)
     */
    @Override
    public void onUpdate(final T sortedComponent, final int index, final AjaxRequestTarget ajaxRequestTarget) {
        // ajaxRequestTarget.appendJavascript("alert('updated  : " +
        // sortedComponent.getMarkupId() + " - "
        // + sortedComponent.getDefaultModelObject().toString() + " - index : "
        // + index + "')");
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.odlabs.wiquery.ui.sortable.SortableAjaxBehavior#onRemove(org.apache
     * .wicket.Component, org.apache.wicket.ajax.AjaxRequestTarget)
     */
    @Override
    public void onRemove(final T sortedComponent, final AjaxRequestTarget ajaxRequestTarget) {
        // ajaxRequestTarget.appendJavascript("alert('removed  : " +
        // sortedComponent.getMarkupId() + " - "
        // + sortedComponent.getDefaultModelObject().toString() + "')");
    }

}
