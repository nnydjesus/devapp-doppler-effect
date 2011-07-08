package ar.edu.unq.dopplereffect.presentation.swf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.util.value.IValueMap;

public class ShockWaveComponent extends ObjectContainer {
    private static final long serialVersionUID = 1L;

    private static final String CONTENTTYPE = "application/x-shockwave-flash";

    private static final String CLSID = "clsid:D27CDB6E-AE6D-11cf-96B8-444553540000";

    private static final String CODEBASE = "http://fpdownload.adobe.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,0,0";

    // valid attributes
    private static final List<String> ATTRIBUTE_NAMES = Arrays.asList(new String[] { "classid", "width", "height",
            "codebase", "align", "base", "data" });

    // valid parameters
    private static final List<String> PARAMETER_NAMES = Arrays.asList(new String[] { "devicefont", "movie", "play",
            "loop", "quality", "bgcolor", "scale", "salign", "menu", "wmode", "allowscriptaccess", "seamlesstabbing" });

    // combined options (to iterate over them)
    private static final List<String> OPTION_NAMES = new ArrayList<String>(ATTRIBUTE_NAMES.size()
            + PARAMETER_NAMES.size());
    static {
        OPTION_NAMES.addAll(ATTRIBUTE_NAMES);
        OPTION_NAMES.addAll(PARAMETER_NAMES);
    }

    private Map<String, String> attributes;

    private Map<String, String> parameters;

    public ShockWaveComponent(final String id) {
        super(id);

        attributes = new HashMap<String, String>();
        parameters = new HashMap<String, String>();
    }

    public ShockWaveComponent(final String id, final String movie, final String width, final String height) {
        this(id);

        this.setValue("movie", movie);
        this.setValue("width", width);
        this.setValue("height", height);
    }

    public void setMovie(final String movie) {
        this.setValue("movie", movie);
    }

    @Override
    public void setValue(final String name, final String value) {
        // IE and other browsers handle movie/data differently. So movie is used
        // for IE, whereas
        // data is used for all other browsers. The class uses movie parameter
        // to handle url and
        // puts the values to the maps depending on the browser information
        String parameter = name.toLowerCase();
        if ("data".equals(parameter)) {
            parameter = "movie";
        }

        if ("movie".equals(parameter) && !this.getClientProperties().isBrowserInternetExplorer()) {
            attributes.put("data", value);
        }

        if (ATTRIBUTE_NAMES.contains(parameter)) {
            attributes.put(parameter, value);
        } else if (PARAMETER_NAMES.contains(parameter)) {
            parameters.put(parameter, value);
        }
    }

    @Override
    public String getValue(final String name) {
        String parameter = name.toLowerCase();
        String value = null;

        if ("data".equals(parameter)) {
            if (this.getClientProperties().isBrowserInternetExplorer()) {
                return null;
            }
            parameter = "movie";
        }

        if (ATTRIBUTE_NAMES.contains(parameter)) {
            value = attributes.get(parameter);
        } else if (PARAMETER_NAMES.contains(parameter)) {
            value = parameters.get(parameter);
        }

        // special treatment of movie to resolve to the url
        if (value != null && parameter.equals("movie")) {
            value = this.resolveResource(value);
        }

        return value;
    }

    @Override
    public void onComponentTag(final ComponentTag tag) {
        // get options from the markup
        IValueMap valueMap = tag.getAttributes();

        // Iterate over valid options
        for (String s : OPTION_NAMES) {
            if (valueMap.containsKey(s)) {
                // if option isn't set programmatically, set value from markup
                if (!attributes.containsKey(s) && !parameters.containsKey(s)) {
                    this.setValue(s, valueMap.getString(s));
                }
                // remove attribute - they are added in super.onComponentTag()
                // to
                // the right place as attribute or param
                valueMap.remove(s);
            }
        }

        super.onComponentTag(tag);
    }

    @Override
    protected String getClsid() {
        return CLSID;
    }

    @Override
    protected String getCodebase() {
        return CODEBASE;
    }

    @Override
    protected String getContentType() {
        return CONTENTTYPE;
    }

    @Override
    protected List<String> getAttributeNames() {
        return ATTRIBUTE_NAMES;
    }

    @Override
    protected List<String> getParameterNames() {
        return PARAMETER_NAMES;
    }
}