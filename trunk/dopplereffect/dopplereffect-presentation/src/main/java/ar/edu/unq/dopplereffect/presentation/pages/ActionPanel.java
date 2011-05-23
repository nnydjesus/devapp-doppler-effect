package ar.edu.unq.dopplereffect.presentation.pages;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

public abstract class ActionPanel extends Panel {

	private static final long serialVersionUID = 1L;

	public ActionPanel(final String id) {
		super(id, new Model(""));
		this.add(new Link("action") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				ActionPanel.this.onAction();
			}

		});
	}

	abstract public void onAction();
}
