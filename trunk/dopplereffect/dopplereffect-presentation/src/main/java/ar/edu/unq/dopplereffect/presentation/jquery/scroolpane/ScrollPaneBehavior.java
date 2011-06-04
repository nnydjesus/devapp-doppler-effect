package ar.edu.unq.dopplereffect.presentation.jquery.scroolpane;

import org.apache.wicket.ResourceReference;
import org.odlabs.wiquery.core.behavior.WiQueryAbstractBehavior;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.ui.commons.WiQueryUIPlugin;

/**
 */
@WiQueryUIPlugin
public class ScrollPaneBehavior extends WiQueryAbstractBehavior {

    private static final long serialVersionUID = 1L;

    private static ResourceReference aCSS = new ResourceReference(ScrollPaneBehavior.class, "jquery.jscrollpane.css");

    private Options options;

    private boolean includeDefaultCss = true;

    /**
     * Constructor.
     */
    public ScrollPaneBehavior() {
        super();
        this.setOptions(new Options());
    }

    @Override
    public void contribute(final WiQueryResourceManager wiQueryResourceManager) {
        wiQueryResourceManager.addJavaScriptResource(ScrollPaneJavascriptResourceReference.get());
        if (this.isIncludeDefaultCss()) {
            wiQueryResourceManager.addCssResource(aCSS);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.odlabs.wiquery.core.behavior.WiQueryAbstractBehavior#statement()
     */
    @Override
    public JsStatement statement() {
        return new JsQuery(this.getComponent()).$().chain("jScrollPane", this.getOptions().getJavaScriptOptions());
    }

    /**
     * Whether arrows should be shown on the generated scroll pane. When set to
     * false only the scrollbar track and drag will be shown, if set to true
     * then arrows buttons will also be shown. Default is false;
     * 
     * @param showArrows
     * @return this behavior.
     */
    public ScrollPaneBehavior setShowArrows(final boolean showArrows) {
        this.getOptions().put("showArrows", showArrows);
        return this;
    }

    /**
     * @return Whether arrows should be shown or not.
     */
    public boolean isShowArrows() {
        Boolean showArrows = this.getOptions().getBoolean("showArrows");
        return showArrows != null ? showArrows.booleanValue() : false;
    }

    /**
     * Whether the scrollpane should attempt to maintain it's position whenever
     * it is reinitialised. If true then the viewport of the scrollpane will
     * remain the same when it is reinitialised, if false then the viewport will
     * jump back up to the top when the scrollpane is reinitialised.
     * 
     * Default value true.
     * 
     * @param maintainPosition
     * @return this behavior.
     */
    public ScrollPaneBehavior setMaintainPosition(final boolean maintainPosition) {
        this.getOptions().put("maintainPosition", maintainPosition);
        return this;
    }

    /**
     * @return Whether position should maintained or not.
     */
    public boolean isMaintainPosition() {
        Boolean maintainPosition = this.getOptions().getBoolean("maintainPosition");
        return maintainPosition != null ? maintainPosition.booleanValue() : true;
    }

    /**
     * @return if default CCS should be included.
     */
    public boolean isIncludeDefaultCss() {
        return includeDefaultCss;
    }

    /**
     * Allows to set whether default CCS will be included or not.
     * 
     * @param includeCss
     */
    public ScrollPaneBehavior setIncludeDefaultCss(final boolean includeCss) {
        includeDefaultCss = includeCss;
        return this;
    }

    public void setOptions(final Options options) {
        this.options = options;
    }

    public Options getOptions() {
        return options;
    }

}