package ar.edu.unq.dopplereffect.mail;

import java.util.Locale;

/**
 */
public class LocaleManager {

    private Locale locale = Locale.US;

    private static Object mutex = new Object();

    private static LocaleManager INSTANCE;

    private LocaleManager() {
        super();
    }

    public static LocaleManager getLocaleManager() {
        synchronized (mutex) {
            if (INSTANCE == null) {
                INSTANCE = new LocaleManager();
            }
        }
        return INSTANCE;
    }

    public void setLocale(final Locale locale) {
        this.locale = locale;
    }

    public Locale getLocale() {
        return locale;
    }

}
