package ar.edu.unq.dopplereffect.presentation.panel;

import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.link.Link;
import org.odlabs.wiquery.core.events.Event;
import org.odlabs.wiquery.core.events.MouseEvent;
import org.odlabs.wiquery.core.events.WiQueryEventBehavior;
import org.odlabs.wiquery.core.javascript.JsScope;
import org.odlabs.wiquery.ui.button.ButtonBehavior;
import org.odlabs.wiquery.ui.dialog.Dialog;

import ar.edu.unq.dopplereffect.presentation.panel.utils.AbstractPanel;
import ar.edu.unq.dopplereffect.presentation.swf.ShockWaveComponent;

public class HeaderPanel extends AbstractPanel<Object> {

    private static final long serialVersionUID = 1L;

    /* ************************ INSTANCE VARIABLES ************************ */

    private Dialog dialog;

    private Page parentPage;

	private ShockWaveComponent swf;

    /* *************************** CONSTRUCTORS *************************** */

    public HeaderPanel(final String id) {
        super(id);
        // this.setParentPage(aParent);
        this.setDialog(new Dialog("dialog"));
//        dialog.add(new Button("aceptar").add(new ButtonBehavior()));
        swf = new ShockWaveComponent( "swf" ,"http://www.zonajuego.com/swf/bouncing_mario.swf", "640","480");
        swf.setOutputMarkupId(true);
        dialog.setWidth(700);
        dialog.setHeight(520);
        dialog.add(swf);
        dialog.setModal(true);
        dialog.setAutoOpen(false);
        this.add(dialog);
        this.add(this.createButton("home", "http://www.zonajuego.com/swf/bouncing_mario.swf"));
        this.add(this.createButton("about", "http://juegosflasheros.org/swf/18f716a9247a66bebc556b1ed5a4d0a7.swf"));
        this.add(this.createButton("products","http://www.zonajuego.com/swf/bouncing_mario.swf"));
        this.add(this.createButton("services", "http://juegosflasheros.org/swf/naruto-blast-battle.swf"));
        this.add(this.createButton("design", "http://juegosflasheros.org/swf/bleach-vs-naruto.swf"));
        this.add(this.createButton("contact", "http://juegosflasheros.org/swf/solarmax.swf"));
        this.add(this.createButton("logout", "http://juegosflasheros.org/swf/money-and-bomb.swf"));
//        this.add(swf);

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
    	AjaxLink<String> button = new AjaxLink<String>(id){

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				swf.setMovie(movie);
				dialog.addOrReplace(swf);
				target.addComponent(dialog);
				target.appendJavascript(HeaderPanel.this.getDialog().open().render().toString());
			}

        };
        button.add(new ButtonBehavior());
//        button.add(new WiQueryEventBehavior(new Event(MouseEvent.CLICK) {
//
//            private static final long serialVersionUID = 1L;
//
//            @Override
//            public JsScope callback() {
//                // HeaderPanel.this.getDialog().setTitle(id);
//                // HeaderPanel.this.getDialog().setnew
//                // DialogUtilsBehavior().simpleDialog(id, id));
//                // HeaderPanel.this.setResponsePage(HeaderPanel.this.getParentPage());
//                return JsScope.quickScope();
//            }
//
//        }));
        return button;
    }
}
