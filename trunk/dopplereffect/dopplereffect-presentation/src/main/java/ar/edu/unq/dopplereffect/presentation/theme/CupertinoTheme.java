package ar.edu.unq.dopplereffect.presentation.theme;

import org.apache.wicket.ResourceReference;

/**
 */
public class CupertinoTheme extends UITheme {

    private static final long serialVersionUID = 1L;

    public static final ResourceReference THEME = new ResourceReference(Theme.class,
            "cupertino/jquery-ui-1.7.2.custom.css");

    private static CupertinoTheme instance;

    /**
     * @param name
     */
    private CupertinoTheme() {
        super("Cupertino");
    }

    /*
     * (non-Javadoc)
     * 
     * @see es.liberty.fwkdemo.web.jquery.themes.UITheme#getTheme()
     */
    @Override
    public ResourceReference getTheme() {
        return THEME;
    }

    public static synchronized CupertinoTheme getInstance() {
        if (instance == null) {
            instance = new CupertinoTheme();
        }
        return instance;
    }

}
