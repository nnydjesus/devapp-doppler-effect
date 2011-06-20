package ar.edu.unq.dopplereffect.presentation.panel;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

/**
 * Representa un panel que internamente contiene un link.
 */
public abstract class ActionPanel extends Panel {

    private static final long serialVersionUID = 1L;

    public ActionPanel(final String id) {
        super(id, new Model<String>(""));
        this.add(new Link<String>("action") {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick() {
                ActionPanel.this.onAction();
            }

        });
    }

    public abstract void onAction();
}