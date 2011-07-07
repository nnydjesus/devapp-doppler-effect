package ar.edu.unq.dopplereffect.service.export;

/**
 */
public enum FormatterExportType {
    PDF("pdf"), RTF("rtf"), EXCEL("xls");

    private String extension;

    private FormatterExportType(final String value) {
        extension = value;
    }

    public void setExtension(final String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }

}
