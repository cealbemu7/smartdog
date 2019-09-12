package co.com.smart.dog.session.base;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.smart.dog.bean.SmartGeneratorReportBeanLocal;
import co.com.smart.dog.infraestructure.dto.SmartExcepcionSerializada;
import co.com.smart.dog.utility.SmartConstant;
import co.com.smart.dog.utility.SmartUtilities;

/**
 * 
 * @author SmartJugle S.A.S
 *
 */
public class SmartContextLookUp {
	private static final Logger logger = Logger.getLogger(SmartConstant.LOGGER_NAME);
	
	private static SmartGeneratorReportBeanLocal getSmartGeneratorReportBean;

	/**
	 * Obtiene la instancia del contexto del bean ingresado
	 * @param bean
	 * @return
	 */
	public static Object getSmartContext(String bean) {
		Object beanContext = null;
		try {
			InitialContext context = new InitialContext();
			beanContext = context.lookup("java:global/"+SmartUtilities.getProperty("ear")+"/"+SmartUtilities.getProperty("ejb")+"/"+bean);
		} catch (NamingException e) {
			logger.debug(e.getMessage(),e);
			SmartExcepcionSerializada exception = new SmartExcepcionSerializada();
			exception.setMensaje("Error inicializando el contexto del servicio ["+ bean + "]");
			exception.setStackTrace(e.getStackTrace());
			throw exception;
		}
		return beanContext;
	}
	
	
	/**
	  * Instancia unica del bean SmartGeneratorReportBeanLocal
	  * @return
	  */
	 public static synchronized SmartGeneratorReportBeanLocal getSmartGeneratorReportBean() {

	  if (getSmartGeneratorReportBean == null) {
		  getSmartGeneratorReportBean = (SmartGeneratorReportBeanLocal) getSmartContext("SmartGeneratorReportBean");
	  }

	  return getSmartGeneratorReportBean;
	 }
	
	
}
