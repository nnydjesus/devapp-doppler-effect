package ar.edu.unq.dopplereffect.presentation.panel;

import java.util.Locale;

import javax.servlet.http.Cookie;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.protocol.http.WebResponse;

import ar.edu.unq.dopplereffect.mail.LocaleManager;
import ar.edu.unq.dopplereffect.presentation.panel.utils.AbstractPanel;

public class LanguageSelectorPanel extends AbstractPanel<Object> {

    private static final long serialVersionUID = 6557739405115062588L;

    public LanguageSelectorPanel(final String id) {
        super(id);
        this.addLanguageSelectorLinkFor("select_spanish_link", new Locale("es"));
        this.addLanguageSelectorLinkFor("select_english_link", Locale.US);
        this.getSession().setLocale(LocaleManager.getLocaleManager().getLocale());
    }

    private void addLanguageSelectorLinkFor(final String wicketId, final Locale locale) {
        this.add(new Link<Object>(wicketId) {

            private static final long serialVersionUID = 8592087920042466102L;

            @Override
            public void onClick() {
                LanguageSelectorPanel.this.setLocale(locale);
            }
        });
    }

    protected void setLocale(final Locale locale) {
        this.getSession().setLocale(locale);
        LocaleManager.getLocaleManager().setLocale(locale);
        ((WebResponse) this.getRequestCycle().getResponse()).addCookie(new Cookie("locale.language", locale
                .getLanguage()));
        ((WebResponse) this.getRequestCycle().getResponse())
                .addCookie(new Cookie("locale.country", locale.getCountry()));
        ((WebResponse) this.getRequestCycle().getResponse())
                .addCookie(new Cookie("locale.variant", locale.getVariant()));
    }
}
