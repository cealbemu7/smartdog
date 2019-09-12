/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.smart.dog.report.base;

import java.util.Map;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import co.com.smart.dog.infraestructure.dto.SmartExcepcionSerializada;

/**
 * Interface encargada de generar los reportes
 * @author SmartJungle S.A.S
 */
public interface SmartReport {
	/**
     * Metodo que define el nombre del archivo a exportar
     * @param filters
     * @param typeExport
     * @return
     * @throws SmartExcepcionSerializada 
     */
    public String getNameReport(Map<String,String> filters,SmartTypeExporterReport typeExport)throws SmartExcepcionSerializada;
    
    /**
     * Metodo que obtiene la ruta relativa del archivo .jasper que se desea generar
     * @param filters
     * @param typeExport
     * @return 
     */
    public String getJasper(Map<String,String> filters,SmartTypeExporterReport typeExport)throws SmartExcepcionSerializada;
    
    /**
     * Genera el reporte a partir del jasper creado y configurado
     * @param jasperFile
     * @param object
     * @param typeExporter
     * @return 
     */
    public JasperPrint generateReport(JasperReport jasperFile,Map<String,String> filters,SmartTypeExporterReport typeExporter)throws SmartExcepcionSerializada;
    
}
