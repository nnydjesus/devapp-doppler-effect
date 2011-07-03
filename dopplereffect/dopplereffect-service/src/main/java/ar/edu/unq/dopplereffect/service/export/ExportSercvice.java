package ar.edu.unq.dopplereffect.service.export;

import java.util.HashMap;
import java.util.List;

import ar.edu.unq.dopplereffect.service.Service;
import ar.edu.unq.tpi.util.services.jasper.Report;

/**
 * TODO tratar de inyectarle la clase del bean
 */
public class ExportSercvice<T> implements Service {
    private static final long serialVersionUID = 1L;

    private Class<T> bean;

    private Report<T> report;

    public ExportSercvice(final Class<T> aBean) {
        bean = aBean;
        report = new Report<T>(bean.getSimpleName());
    }

    public void export(final String pathFile, final List<T> beanCollection, final HashMap<String, String> parameters) {
        if (pathFile.endsWith(FormatterExportType.PDF.getExtension())) {
            this.exportToPDF(pathFile, beanCollection, parameters);
        } else if (pathFile.endsWith(FormatterExportType.EXCEL.getExtension())) {
            this.exportToEXEL(pathFile, beanCollection, parameters);
        } else if (pathFile.endsWith(FormatterExportType.RTF.getExtension())) {
            this.exportToEXEL(pathFile, beanCollection, parameters);
        }

    }

    public void exportToPDF(final String pathFile, final List<T> beanCollection,
            final HashMap<String, String> parameters) {
        report.exportPDF(pathFile, beanCollection, parameters);
    }

    public void exportToEXEL(final String pathFile, final List<T> beanCollection,
            final HashMap<String, String> parameters) {
        report.exportEXEL(pathFile, beanCollection, parameters);
    }

    public void exportToRTF(final String pathFile, final List<T> beanCollection,
            final HashMap<String, String> parameters) {
        report.exportRTF(pathFile, beanCollection, parameters);
    }

}
