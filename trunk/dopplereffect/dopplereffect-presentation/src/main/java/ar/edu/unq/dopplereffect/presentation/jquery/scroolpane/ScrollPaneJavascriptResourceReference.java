package ar.edu.unq.dopplereffect.presentation.jquery.scroolpane;

import org.odlabs.wiquery.core.commons.WiQueryJavaScriptResourceReference;

/**
 */
public class ScrollPaneJavascriptResourceReference extends WiQueryJavaScriptResourceReference {
    private static final long serialVersionUID = 1;

    /**
     * Singleton instance.
     */
    private static ScrollPaneJavascriptResourceReference instance = new ScrollPaneJavascriptResourceReference();;

    /**
     * Builds a new instance of {@link ScrollPaneJavascriptResourceReference}.
     */
    private ScrollPaneJavascriptResourceReference() {
        super(ScrollPaneJavascriptResourceReference.class, "jquery.jscrollpane.min.js");
    }

    /**
     * Returns the {@link ScrollPaneJavascriptResourceReference} instance.
     */
    public static ScrollPaneJavascriptResourceReference get() {
        return instance;
    }
}
