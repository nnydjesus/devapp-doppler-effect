package ar.edu.unq.dopplereffect.presentation.panel;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import ar.edu.unq.dopplereffect.presentation.util.ReflectionAjaxLink;

public class AjaxReflectionActionPanel<T extends Component> extends Panel {

    private static final long serialVersionUID = 1L;

    public AjaxReflectionActionPanel(final String id, final T host, final String action, final String image) {
        super(id, new Model<String>(""));
        ReflectionAjaxLink<T> ajaxLink = new ReflectionAjaxLink<T>("action", action, host, host);
        ajaxLink.add(new Image("image", new Model<String>(image)));
        this.add(ajaxLink);
    }

}