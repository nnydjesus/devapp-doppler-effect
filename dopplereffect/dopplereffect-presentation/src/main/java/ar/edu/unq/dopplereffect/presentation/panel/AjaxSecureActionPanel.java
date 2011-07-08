package ar.edu.unq.dopplereffect.presentation.panel;

import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeAction;

import ar.edu.unq.dopplereffect.presentation.util.Model;

@AuthorizeAction(action = "RENDER", roles = { "ROLE_ADMIN" })
public abstract class AjaxSecureActionPanel extends AjaxActionPanel {

    private static final long serialVersionUID = 7012194580862868658L;

    public AjaxSecureActionPanel(final String id) {
        super(id);
    }

    public AjaxSecureActionPanel(final String id, final String image) {
        super(id, image);
    }

    public AjaxSecureActionPanel(final String id, final Model<?> model) {
        super(id, model);
    }
}
