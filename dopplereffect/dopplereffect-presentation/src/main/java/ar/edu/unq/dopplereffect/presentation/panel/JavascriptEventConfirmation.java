package ar.edu.unq.dopplereffect.presentation.panel;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.model.Model;

public class JavascriptEventConfirmation extends AttributeModifier {

    private static final long serialVersionUID = -2670304657203649735L;

    public JavascriptEventConfirmation(final String event, final String msg) {
        super(event, true, new Model<String>(msg));
    }

    @Override
    protected String newValue(final String currentValue, final String replacementValue) {
        String prefix = "var conf = confirm('" + replacementValue + "'); " + "if (!conf) return false; ";
        String result = prefix;
        if (currentValue != null) {
            result = prefix + currentValue;
        }
        return result;
    }
}