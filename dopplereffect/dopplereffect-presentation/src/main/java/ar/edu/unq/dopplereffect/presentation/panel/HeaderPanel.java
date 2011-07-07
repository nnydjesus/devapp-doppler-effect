package ar.edu.unq.dopplereffect.presentation.panel;

import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.model.StringResourceModel;
import org.odlabs.wiquery.ui.button.ButtonBehavior;
import org.odlabs.wiquery.ui.dialog.Dialog;

import ar.edu.unq.dopplereffect.presentation.panel.utils.AbstractPanel;
import ar.edu.unq.dopplereffect.presentation.swf.ShockWaveComponent;
import ar.edu.unq.dopplereffect.presentation.util.Model;

public class HeaderPanel extends AbstractPanel<Object> {

    private static final long serialVersionUID = 1L;

    /* ************************ INSTANCE VARIABLES ************************ */

    private Dialog dialog;

    private Page parentPage;

    private ShockWaveComponent swf;

    /* *************************** CONSTRUCTORS *************************** */

    public HeaderPanel(final String id) {
        super(id);
        this.setDialog(new Dialog("dialog"));
        swf = new ShockWaveComponent("swf", "http://www.zonajuego.com/swf/bouncing_mario.swf", "640", "480");
        swf.setOutputMarkupId(true);
        dialog.setWidth(700);
        dialog.setHeight(520);
        dialog.add(swf);
        dialog.setModal(true);
        dialog.setAutoOpen(false);
        this.add(dialog);
        this.add(this.createButton("game1", "http://www.zonajuego.com/swf/bouncing_mario.swf"));
        this.add(this.createButton("game2", "http://juegosflasheros.org/swf/18f716a9247a66bebc556b1ed5a4d0a7.swf"));
        this.add(this.createButton("game3", "http://www.zonajuego.com/swf/bouncing_mario.swf"));
        this.add(this.createButton("game4", "http://juegosflasheros.org/swf/naruto-blast-battle.swf"));
        this.add(this.createButton("game5", "http://juegosflasheros.org/swf/bleach-vs-naruto.swf"));
        this.add(this.createButton("game6", "http://juegosflasheros.org/swf/solarmax.swf"));
        this.add(this.createButton("game7", "http://juegosflasheros.org/swf/money-and-bomb.swf"));
        // this.add(swf);

    }

    /* **************************** ACCESSORS ***************************** */

    public Dialog getDialog() {
        return dialog;
    }

    public void setDialog(final Dialog dialog) {
        this.dialog = dialog;
    }

    public Page getParentPage() {
        return parentPage;
    }

    public void setParentPage(final Page parentPage) {
        this.parentPage = parentPage;
    }

    /* ************************* PRIVATE METHODS ************************** */

    private AjaxLink<String> createButton(final String id, final String movie) {
        AjaxLink<String> button = new AjaxLink<String>(id, new StringResourceModel(id, new Model<String>())) {

            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(final AjaxRequestTarget target) {
                swf.setMovie(movie);
                dialog.addOrReplace(swf);
                HeaderPanel.this.getDialog().setTitle(new StringResourceModel(id, new Model<String>()));
                target.addComponent(dialog);
                target.appendJavascript(HeaderPanel.this.getDialog().open().render().toString());
            }

        };
        button.add(new ButtonBehavior());
        return button;
    }
}
