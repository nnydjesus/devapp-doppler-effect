package ar.edu.unq.dopplereffect.presentation.panel.upload;

import java.io.File;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.DownloadLink;
import org.apache.wicket.model.PropertyModel;
import org.odlabs.wiquery.ui.dialog.Dialog;

import ar.edu.unq.dopplereffect.presentation.components.CustomComponent;
import ar.edu.unq.dopplereffect.presentation.util.HandlerErrorAction;
import ar.edu.unq.dopplereffect.service.export.FormatterExportType;

/**
 */
public class DialogSelectedTypeToExport extends Dialog {

    private static final long serialVersionUID = 1L;

    private UploadPanel<?> uploadPanel;

    private File file;

    public DialogSelectedTypeToExport(final String id, final UploadPanel<?> uploadPanel) {
        super(id);
        this.setUploadPanel(uploadPanel);
        this.addComponents();
    }

    protected void addComponents() {
        this.createDownloadButton("pdf", FormatterExportType.PDF);
        this.createDownloadButton("rtf", FormatterExportType.RTF);
        this.createDownloadButton("xls", FormatterExportType.EXCEL);
    }

    protected void createDownloadButton(final String id, final FormatterExportType type) {
        final DownloadLink downloadLink = new DownloadLink(id, new PropertyModel<File>(this, "file")) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick() {
                new HandlerErrorAction() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void onExecute() {
                        DialogSelectedTypeToExport.this.setFile(DialogSelectedTypeToExport.this.getUploadPanel()
                                .onExport(type));
                    }
                }.execute();
                super.onClick();
            }

        };
        this.add(CustomComponent.addButtonSking(downloadLink));
    }

    public void setUploadPanel(final UploadPanel<?> uploadPanel) {
        this.uploadPanel = uploadPanel;
    }

    public UploadPanel<?> getUploadPanel() {
        return uploadPanel;
    }

    public void setFile(final File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }
}
