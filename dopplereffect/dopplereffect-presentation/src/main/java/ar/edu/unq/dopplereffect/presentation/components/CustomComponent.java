package ar.edu.unq.dopplereffect.presentation.components;

import org.apache.wicket.Component;
import org.odlabs.wiquery.ui.button.ButtonBehavior;

public class CustomComponent {

    private CustomComponent() {
        // x
    }

    public static Component addButtonSking(final Component component) {
        return component.add(new ButtonBehavior());
    }

}
