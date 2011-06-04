package ar.edu.unq.dopplereffect.presentation.theme;

import org.apache.wicket.ResourceReference;

/**
 * 
 */
public class RedmondTheme extends UITheme {

    private static final long serialVersionUID = 1L;

    public static final ResourceReference THEME = new ResourceReference(Themes.class, "jquery-ui-1.7.1.custom.css");

    private static RedmondTheme instance;

    /**
     * @param name
     */
    private RedmondTheme() {
        super("Redmond");
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

    public static synchronized RedmondTheme getInstance() {
        if (instance == null) {
            instance = new RedmondTheme();
        }
        return instance;
    }

}
