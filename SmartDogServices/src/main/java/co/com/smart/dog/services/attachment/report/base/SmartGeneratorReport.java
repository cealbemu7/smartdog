/**
 * 
 */
package co.com.smart.dog.services.attachment.report.base;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import co.com.smart.dog.session.DelegateContextEJB;
import co.com.smart.dog.utility.SmartConstant;

/**
 * @author SmartJungle S.A.S
 *
 */
public class SmartGeneratorReport extends HttpServlet{
	private static final long serialVersionUID = 1L;
    private final Logger logger = Logger.getLogger(SmartConstant.LOGGER_NAME);
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SmartGeneratorReport() {
        super();
    }
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String reportClass = request.getParameter("class");
    	String type = request.getParameter("type").toUpperCase();
    	Map<String,String> filters = new HashMap<>();
    	logger.info("--------- filtros reporte: "+reportClass+" ------");
    	for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
    		String logRequest = "filtro = [" + entry.getKey() + "], valor = [" + (entry.getValue() != null && entry.getValue().length >0 ?entry.getValue()[0]:"")+"]";
    		logger.info(logRequest);
    		filters.put(entry.getKey(), entry.getValue()[0] != null && entry.getValue().length >0 && !"null".equals(entry.getValue()[0])?entry.getValue()[0]:null);
    	}
    	DelegateContextEJB.getSmartGeneratorReportBean().export(reportClass,filters,type,response);
    }
    
}
