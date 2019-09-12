package co.com.smart.dog.bean;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.log4j.Logger;

import co.com.smart.dog.infraestructure.dto.SmartExcepcionSerializada;
import co.com.smart.dog.report.base.SmartReport;
import co.com.smart.dog.report.base.SmartTypeExporterReport;
import co.com.smart.dog.utility.SmartConstant;

@Stateless(name = "SmartGeneratorReportBean",
mappedName = "ejb/SmartGeneratorReportBean")
public class SmartGeneratorReportBean implements SmartGeneratorReportBeanLocal {
	 private final Logger logger = Logger.getLogger(SmartConstant.LOGGER_NAME);
	/**
	 * Default constructor
	 */
	public SmartGeneratorReportBean(){
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void export(String reportClass, Map<String,String> filters, String type,
			HttpServletResponse response) throws SmartExcepcionSerializada, IOException {
		SmartTypeExporterReport typeExport = SmartTypeExporterReport.valueOf(type);
		SmartReport report;
        Class clazz = null;
        OutputStream outputStream = response.getOutputStream();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            clazz = Class.forName("co.com.smart.dog.report."+reportClass);
        } catch (ClassNotFoundException ee) {
            throw new SmartExcepcionSerializada("La clase report [" + reportClass + "] no existe.");
        }

        try {
            report = (SmartReport) clazz.getMethod("getInstance", new Class[]{}).invoke(clazz, new Object[]{});//invoke CALLER REPORT
            InputStream inputJasper = SmartGeneratorReportBean.class.getResourceAsStream(((SmartReport) report).getJasper(filters, typeExport));
            String filePath = ((SmartReport) report).getNameReport(filters, typeExport);
			
            response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename="+filePath+ "." + typeExport.name().toLowerCase());
			
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(inputJasper);
            jasperReport.setProperty("net.sf.jasperreports.print.keep.full.text", "true");

            JasperPrint jasperPrint = ((SmartReport) report).generateReport(jasperReport, filters, typeExport);
            	
            switch (typeExport) {
                case XLS:
                    JRXlsExporter exporter = new JRXlsExporter();
                    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                    exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
                    exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
                    exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
                    exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
//                    exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, file.getName());
                    exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, output);
                    exporter.exportReport();
                    outputStream.write(output.toByteArray());
                    break;
                case CSV:
                    JRCsvExporter exporterCSV = new JRCsvExporter();
                    exporterCSV.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
                    exporterCSV.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, output);
//                    exporterCSV.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, file.getName());
                    exporterCSV.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
                    exporterCSV.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
                    exporterCSV.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.TRUE);
                    exporterCSV.exportReport();
                    outputStream.write(output.toByteArray());
                    break;
                case PDF:
                    JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
                    break;
                case XLSX:
                    JRXlsxExporter exporterxlsx = new JRXlsxExporter();
                    exporterxlsx.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                    exporterxlsx.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
                    exporterxlsx.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
                    exporterxlsx.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
                    exporterxlsx.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
//                    exporterxlsx.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, file.getName());
                    exporterxlsx.setParameter(JRExporterParameter.OUTPUT_STREAM, output);
                    exporterxlsx.exportReport();
                    outputStream.write(output.toByteArray());
                    break;
            }
        } catch (SmartExcepcionSerializada | IOException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException | JRException ex) {
            logger.error(ex.getLocalizedMessage(),ex);
            throw new SmartExcepcionSerializada(ex.getMessage());
        } catch (Throwable thr){    
             logger.error(thr.getLocalizedMessage(),thr);
             throw new SmartExcepcionSerializada(thr.getMessage());
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                }
            } catch (IOException ee) {
                logger.error(ee.getLocalizedMessage(),ee);
            }
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException ee) {
                logger.error(ee.getLocalizedMessage(),ee);
            }
        }
	}
	
    
}
