package ar.edu.unq.dopplereffect.presentation.panel;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import ar.edu.unq.dopplereffect.presentation.util.ReflextionAjaxLink;

public class AjaxReflextionActionPanel<T extends Component> extends Panel {

    private static final long serialVersionUID = 1L;

    public AjaxReflextionActionPanel(final String id, final T host, final String action, final String image,
            final String prevPath) {
        super(id, new Model<String>(""));
        ReflextionAjaxLink<T> ajaxLink = new ReflextionAjaxLink<T>("action", action, host, host);
        ajaxLink.add(new Image("image", new Model<String>(image)));
        this.add(ajaxLink);
    }

}