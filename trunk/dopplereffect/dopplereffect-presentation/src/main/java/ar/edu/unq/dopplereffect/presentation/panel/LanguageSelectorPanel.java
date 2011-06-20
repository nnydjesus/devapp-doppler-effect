package ar.edu.unq.dopplereffect.presentation.panel;

import java.util.Locale;

import org.apache.wicket.markup.html.link.Link;

import ar.edu.unq.dopplereffect.presentation.panel.utils.AbstractPanel;

public class LanguageSelectorPanel extends AbstractPanel<Object> {

    private static final long serialVersionUID = 6557739405115062588L;

    public LanguageSelectorPanel(final String id) {
        super(id);
        this.addLanguageSelectorLinkFor("select_spanish_link", new Locale("es"));
        this.addLanguageSelectorLinkFor("select_english_link", Locale.US);
    }

    private void addLanguageSelectorLinkFor(final String wicketId, final Locale locale) {
        this.add(new Link<Object>(wicketId) {

            private static final long serialVersionUID = 8592087920042466102L;

            @Override
            public void onClick() {
                this.getSession().setLocale(locale);
            }
        });
    }
}
