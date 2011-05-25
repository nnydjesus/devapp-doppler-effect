package ar.edu.unq.dopplereffect.presentation.panel;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

public abstract class AjaxActionPanel extends Panel {

    private static final long serialVersionUID = 1L;

    public AjaxActionPanel(final String id, final String image) {
        super(id, new Model<String>(""));
        AjaxLink<String> ajaxLink = new AjaxLink<String>("action") {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(final AjaxRequestTarget target) {
                AjaxActionPanel.this.onAction(target);
            }

        };
        ajaxLink.add(new Image("image", new Model<String>("../Images/" + image)));
        this.add(ajaxLink);

    }

    public abstract void onAction(final AjaxRequestTarget target);
}