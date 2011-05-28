package ar.edu.unq.dopplereffect.presentation.panel;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.form.Button;
import org.odlabs.wiquery.core.events.Event;
import org.odlabs.wiquery.core.events.MouseEvent;
import org.odlabs.wiquery.core.events.WiQueryEventBehavior;
import org.odlabs.wiquery.core.javascript.JsScope;
import org.odlabs.wiquery.ui.button.ButtonBehavior;
import org.odlabs.wiquery.ui.dialog.Dialog;

import ar.edu.unq.dopplereffect.presentation.panel.utils.AbstractPanel;

/**
 * TODO: description
 */
public class HeaderPanel extends AbstractPanel<Object> {
    private static final long serialVersionUID = 1L;

    private Dialog dialog;

    private Page parentPage;

    public HeaderPanel(final String id) {
        super(id);
        // this.setParentPage(aParent);
        this.setDialog(new Dialog("dialog"));
        dialog.add(new Button("aceptar").add(new ButtonBehavior()));
        this.add(dialog);
        this.add(this.createButton("home"));
        this.add(this.createButton("about"));
        this.add(this.createButton("products"));
        this.add(this.createButton("services"));
        this.add(this.createButton("design"));
        this.add(this.createButton("contact"));
        this.add(this.createButton("logout"));
    }

    private Button createButton(final String id) {

        Button button = new Button(id);
        button.add(new ButtonBehavior());
        button.add(new WiQueryEventBehavior(new Event(MouseEvent.CLICK) {

            private static final long serialVersionUID = 1L;

            @Override
            public JsScope callback() {
                HeaderPanel.this.getDialog().setTitle(id);
                // HeaderPanel.this.getDialog().setnew
                // DialogUtilsBehavior().simpleDialog(id, id));
                // HeaderPanel.this.setResponsePage(HeaderPanel.this.getParentPage());
                return JsScope.quickScope(HeaderPanel.this.getDialog().open().render());
            }

        }));
        return button;
    }

    public void setDialog(final Dialog dialog) {
        this.dialog = dialog;
    }

    public Dialog getDialog() {
        return dialog;
    }

    public void setParentPage(final Page parentPage) {
        this.parentPage = parentPage;
    }

    public Page getParentPage() {
        return parentPage;
    }
}
