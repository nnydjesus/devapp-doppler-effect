package ar.edu.unq.dopplereffect.presentation.swf;

import java.io.Serializable;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.Response;
import org.apache.wicket.Session;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.protocol.http.ClientProperties;
import org.apache.wicket.protocol.http.WebRequestCycle;
import org.apache.wicket.protocol.http.request.WebClientInfo;
import org.apache.wicket.request.ClientInfo;
import org.apache.wicket.util.value.IValueMap;

public abstract class ObjectContainer extends WebMarkupContainer implements Serializable {
    private static final long serialVersionUID = 1L;

    // Some general attributes for the object tag:
    private static final String ATTRIBUTE_CONTENTTYPE = "type";

    private static final String ATTRIBUTE_CLASSID = "classid";

    private static final String ATTRIBUTE_CODEBASE = "codebase";

    // This is used for browser specific adjustments
    private ClientProperties clientProperties = null;

    public ObjectContainer(final String id) {
        super(id);
    }

    // Set an attribute/property
    public abstract void setValue(String name, String value);

    // Get an attribute/property
    public abstract String getValue(String name);

    // Set the object's content type
    protected abstract String getContentType();

    // Set the object's clsid (for IE)
    protected abstract String getClsid();

    // Where to get the browser plugin (for IE)
    protected abstract String getCodebase();

    // Object's valid attribute names
    protected abstract List<String> getAttributeNames();

    // Object's valid parameter names
    protected abstract List<String> getParameterNames();

    // Utility function to get the URL for the object's data
    protected String resolveResource(final String src) {
        // if it's an absolute path, return it:
        if (src.startsWith("/") || src.startsWith("http://") || src.startsWith("https://")) {
            return src;
        }

        // use the parent container class to resolve the resource reference
        Component parent = this.getParent();
        if (parent != null) {
            ResourceReference resRef = new ResourceReference(parent.getClass(), src);
            return this.urlFor(resRef).toString();
        }

        return src;
    }

    @Override
    public void onComponentTag(final ComponentTag tag) {
        super.onComponentTag(tag);

        // get the attributes from the html-source
        IValueMap attributeMap = tag.getAttributes();

        // set the content type
        this.setContentType(attributeMap);

        // set clsid and codebase for IE
        this.setCLSIDAndCodeBaseForIE(attributeMap);

        // add all attributes
        for (String name : this.getAttributeNames()) {
            String value = this.getValue(name);
            if (value != null) {
                attributeMap.put(name, value);
            }
        }
    }

    public void setCLSIDAndCodeBaseForIE(final IValueMap attributeMap) {
        if (this.getClientProperties().isBrowserInternetExplorer()) {
            String clsid = this.getClsid();
            String codeBase = this.getCodebase();

            if (clsid != null && !"".equals(clsid)) {
                attributeMap.put(ATTRIBUTE_CLASSID, clsid);
            }
            if (codeBase != null && !"".equals(codeBase)) {
                attributeMap.put(ATTRIBUTE_CODEBASE, codeBase);
            }
        }
    }

    public void setContentType(final IValueMap attributeMap) {
        String contentType = this.getContentType();
        if (contentType != null && !"".equals(contentType)) {
            attributeMap.put(ATTRIBUTE_CONTENTTYPE, contentType);
        }
    }

    @Override
    public void onComponentTagBody(final MarkupStream markupStream, final ComponentTag openTag) {
        Response response = this.getResponse();
        response.write("\n");

        // add all object's parameters:
        for (String name : this.getParameterNames()) {
            String value = this.getValue(name);
            if (value != null) {
                response.write("<param name=\"");
                response.write(name);
                response.write("\" value=\"");
                response.write(value);
                response.write("\"/>\n");
            }
        }

        super.onComponentTagBody(markupStream, openTag);
    }

    // shortcut to the client properties:
    protected ClientProperties getClientProperties() {
        if (clientProperties == null) {
            ClientInfo clientInfo = Session.get().getClientInfo();

            if (clientInfo == null || !(clientInfo instanceof WebClientInfo)) {
                clientInfo = new WebClientInfo((WebRequestCycle) this.getRequestCycle());
                Session.get().setClientInfo(clientInfo);
            }

            clientProperties = ((WebClientInfo) clientInfo).getProperties();
        }
        return clientProperties;
    }
}