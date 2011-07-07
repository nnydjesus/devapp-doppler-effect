package ar.edu.unq.dopplereffect.service.export;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import ar.edu.unq.dopplereffect.service.Service;
import ar.edu.unq.tpi.util.services.jasper.Report;

public class ExportService<T> implements Service {
    private static final long serialVersionUID = 1L;

    private Class<T> bean;

    private Report<T> report;

    public ExportService() {
    }

    @SuppressWarnings("unchecked")
    public ExportService(final String classBean) throws ClassNotFoundException {
        bean = (Class<T>) Class.forName(classBean);
        this.setReport(new Report<T>(bean.getSimpleName()));
    }

    public File export(final String pathFile, final List<T> beanCollection, final HashMap<String, String> parameters) {
        if (pathFile.endsWith(FormatterExportType.PDF.getExtension())) {
            return this.exportToPDF(pathFile, beanCollection, parameters);
        } else if (pathFile.endsWith(FormatterExportType.EXCEL.getExtension())) {
            return this.exportToEXEL(pathFile, beanCollection, parameters);
        } else if (pathFile.endsWith(FormatterExportType.RTF.getExtension())) {
            return this.exportToEXEL(pathFile, beanCollection, parameters);
        }
        return null;

    }

    public File exportToPDF(final String pathFile, final List<T> beanCollection,
            final HashMap<String, String> parameters) {
        return report.exportPDF(pathFile, beanCollection, parameters);
    }

    public File exportToEXEL(final String pathFile, final List<T> beanCollection,
            final HashMap<String, String> parameters) {
        return report.exportEXEL(pathFile, beanCollection, parameters);
    }

    public File exportToRTF(final String pathFile, final List<T> beanCollection,
            final HashMap<String, String> parameters) {
        return report.exportRTF(pathFile, beanCollection, parameters);
    }

    public void setReport(final Report<T> report) {
        this.report = report;
    }

    public Report<T> getReport() {
        return report;
    }

}
