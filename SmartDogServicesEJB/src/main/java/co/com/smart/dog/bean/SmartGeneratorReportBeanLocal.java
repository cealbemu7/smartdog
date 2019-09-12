package co.com.smart.dog.bean;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import co.com.smart.dog.infraestructure.dto.SmartExcepcionSerializada;

public interface SmartGeneratorReportBeanLocal {
	
    /**
     * Genera el reporte dependiendo de la clase reporte ingresada
     *
     * @param reportClass
     * @param filters
     * @param typeExport
     * @throws SmartExcepcionSerializada, IOException 
     */
	public void export(String reportClass, Map<String,String> filters, String typeExport,HttpServletResponse response)throws SmartExcepcionSerializada, IOException;

}
