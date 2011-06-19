package ar.edu.unq.dopplereffect.presentation.panel;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;

import ar.edu.unq.dopplereffect.presentation.util.Model;

public abstract class AjaxActionPanel extends Panel {

    private static final long serialVersionUID = 1L;

    private AjaxLink<String> ajaxLink;

    public AjaxActionPanel(final String id) {
        super(id, new Model<String>(""));
        this.setAjaxLink(new AjaxLink<String>("action") {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(final AjaxRequestTarget target) {
                AjaxActionPanel.this.onAction(target);
            }

        });
    }

    public AjaxActionPanel(final String id, final String image, final String prevPath) {
        this(id);
        this.getAjaxLink().add(new Image("image", new Model<String>(image)));
        this.add(this.getAjaxLink());
    }

    public AjaxActionPanel(final String id, final Model<?> model) {
        this(id);
        this.getAjaxLink().add(new Label("image", model));
        this.add(this.getAjaxLink());
    }

    public AjaxActionPanel(final String id, final String image) {
        this(id, image, "");
    }

    public abstract void onAction(final AjaxRequestTarget target);

    public void setAjaxLink(final AjaxLink<String> ajaxLink) {
        this.ajaxLink = ajaxLink;
    }

    public AjaxLink<String> getAjaxLink() {
        return ajaxLink;
    }
}