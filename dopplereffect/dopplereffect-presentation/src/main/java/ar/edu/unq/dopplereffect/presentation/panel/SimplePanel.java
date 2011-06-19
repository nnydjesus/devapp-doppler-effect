package ar.edu.unq.dopplereffect.presentation.panel;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

/**
 */
public class SimplePanel extends Panel {
    private static final long serialVersionUID = 1L;

    public SimplePanel(final String id) {
        super(id, new Model<String>(""));
        this.setOutputMarkupId(true);
    }

}
