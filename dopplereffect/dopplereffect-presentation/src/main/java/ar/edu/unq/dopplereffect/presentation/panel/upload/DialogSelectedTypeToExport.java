package ar.edu.unq.dopplereffect.presentation.panel.upload;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.odlabs.wiquery.ui.button.ButtonElement;
import org.odlabs.wiquery.ui.button.ButtonRadioSet;
import org.odlabs.wiquery.ui.dialog.Dialog;

import ar.edu.unq.dopplereffect.presentation.components.CustomComponent;
import ar.edu.unq.dopplereffect.presentation.util.Model;
import ar.edu.unq.dopplereffect.service.export.FormatterExportType;

/**
 * TODO: description
 */
public class DialogSelectedTypeToExport extends Dialog {

    private static final long serialVersionUID = 1L;

    private UploadPanel<?> uploadPanel;

    private ButtonRadioSet<String> radioGroup;

    private String selected = FormatterExportType.PDF.getExtension();

    public DialogSelectedTypeToExport(final String id, final UploadPanel<?> uploadPanel) {
        super(id);
        this.setUploadPanel(uploadPanel);
        this.addComponents();
    }

    protected void addComponents() {
        radioGroup = new ButtonRadioSet<String>("group", this.createRadios()) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onSelectionChanged(final Object newSelection) {
                DialogSelectedTypeToExport.this.setSelected((String) newSelection);
            }

        };

        this.add(radioGroup);
        this.add(CustomComponent.addButtonSking(new AjaxLink<String>("acept") {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(final AjaxRequestTarget target) {
                DialogSelectedTypeToExport.this.getUploadPanel().onExport(target,
                        DialogSelectedTypeToExport.this.getSelected());
                target.appendJavascript(DialogSelectedTypeToExport.this.close().render().toString());
            }

        }));

    }

    private List<? extends ButtonElement<String>> createRadios() {
        ArrayList<ButtonElement<String>> result = new ArrayList<ButtonElement<String>>();
        result.add(this.createButtonElement(FormatterExportType.PDF.getExtension(), "PDF"));
        result.add(this.createButtonElement(FormatterExportType.EXCEL.getExtension(), "Excel"));
        result.add(this.createButtonElement(FormatterExportType.RTF.getExtension(), "RTF"));
        return result;

    }

    protected ButtonElement<String> createButtonElement(final String model, final String label) {
        return new ButtonElement<String>(new Model<String>(model), new Model<String>(label));
    }

    public void setSelected(final String selected) {
        this.selected = selected;
    }

    public String getSelected() {
        return selected;
    }

    public void setUploadPanel(final UploadPanel<?> uploadPanel) {
        this.uploadPanel = uploadPanel;
    }

    public UploadPanel<?> getUploadPanel() {
        return uploadPanel;
    }
}
