package ar.edu.unq.dopplereffect.presentation.util;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.odlabs.wiquery.ui.selectable.SelectableAjaxBehavior;

/**
 * TODO: description
 */
public class SelectTable extends SelectableAjaxBehavior {
    private static final long serialVersionUID = 1L;

    private Component componentModel;

    // public SelectTable() {
    // super();
    // // this.setComponentModel(aComponent);
    // }

    @Override
    public void onSelection(final Component[] components, final AjaxRequestTarget ajaxRequestTarget) {
        // StringBuffer buffer = new StringBuffer();
        // for (Component c : components) {
        // buffer.append("[");
        // buffer.append(c.getDefaultModelObject().toString());
        // buffer.append("]");
        // }
        // this.getComponentModel().setDefaultModelObject(buffer.toString());
        // ajaxRequestTarget.addComponent(this.getComponentModel());
    }

    public void setComponentModel(final Component componentModel) {
        this.componentModel = componentModel;
    }

    public Component getComponentModel() {
        return componentModel;
    }

}
