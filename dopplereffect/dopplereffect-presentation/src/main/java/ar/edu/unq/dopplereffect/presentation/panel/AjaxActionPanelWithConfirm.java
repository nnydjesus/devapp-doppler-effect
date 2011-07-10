package ar.edu.unq.dopplereffect.presentation.panel;

import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeAction;

/**
 * Representa un panel con un link, el cual al ser clickeado pregunta primero
 * usando un cuadro de dialogo.
 */
@AuthorizeAction(action = "RENDER", roles = { "ROLE_ADMIN" })
public abstract class AjaxActionPanelWithConfirm extends AjaxActionPanel {

    private static final long serialVersionUID = 5303309712802671748L;

    /* ************************ INSTANCE VARIABLES ************************ */

    /* *************************** CONSTRUCTORS *************************** */

    public AjaxActionPanelWithConfirm(final String id, final String image) {
        this(id, image, "Are you sure?");
    }

    public AjaxActionPanelWithConfirm(final String id, final String image, final String confirmation) {
        super(id, image);
        this.getAjaxLink().add(new JavascriptEventConfirmation("onclick", confirmation));
    }

    /* **************************** ACCESSORS ***************************** */

}
