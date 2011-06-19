package ar.edu.unq.dopplereffect.presentation.util;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.odlabs.wiquery.ui.selectable.SelectableAjaxBehavior;

public class SelectableBehavior extends SelectableAjaxBehavior {
    private static final long serialVersionUID = 1L;

    private Component componentModel;

    @Override
    public void onSelection(final Component[] components, final AjaxRequestTarget ajaxRequestTarget) {
        if (components.length > 0) {
            componentModel = components[0];
        }
    }

    public void setComponentModel(final Component componentModel) {
        this.componentModel = componentModel;
    }

    public Component getComponentModel() {
        return componentModel;
    }

    public void clean() {
        componentModel = null;
    }

}
