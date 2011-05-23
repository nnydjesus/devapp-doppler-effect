package ar.edu.unq.dopplereffect.presentation.pages;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

public abstract class AjaxActionPanel extends Panel {

	private static final long serialVersionUID = 1L;

	public AjaxActionPanel(final String id) {
		super(id, new Model(""));
		this.add(new AjaxLink("action") {
			private static final long serialVersionUID = 1L;

			@Override
            public void onClick(final AjaxRequestTarget target) {
				AjaxActionPanel.this.onAction(target);
			}

		});
	}

	abstract public void onAction(final AjaxRequestTarget target);
}