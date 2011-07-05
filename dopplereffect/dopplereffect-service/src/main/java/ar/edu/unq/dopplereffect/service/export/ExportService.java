package ar.edu.unq.dopplereffect.service.export;

import java.util.HashMap;
import java.util.List;

import ar.edu.unq.dopplereffect.service.Service;
import ar.edu.unq.tpi.util.services.jasper.Report;

/**
 */
public class ExportService<T> implements Service {
    private static final long serialVersionUID = 1L;

    private Class<T> bean;

    private Report<T> report;
    
    public ExportService() {
	}

    @SuppressWarnings("unchecked")
	public ExportService(final String classBean) throws ClassNotFoundException {
        bean = (Class<T>) Class.forName(classBean);
        report = new Report<T>(bean.getSimpleName());
    }

    public void export(final String pathFile, final List<T> beanCollection, final HashMap<String, String> parameters) {
        if (pathFile.endsWith(FormatterExportType.PDF.getExtension())) {
            this.exportToPDF(pathFile, beanCollection, parameters);
        } else if (pathFile.endsWith(FormatterExportType.EXCEL.getExtension())) {
            this.exportToEXEL(pathFile, beanCollection, parameters);
        } else if (pathFile.endsWith(FormatterExportType.RTF.getExtension())) {
            this.exportToRTF(pathFile, beanCollection, parameters);
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
