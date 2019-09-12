package co.com.smart.dog.bean;

import co.com.smart.dog.infraestructure.dto.SmartExcepcionSerializada;

public class AbstractBean {
	
	/**
	 * Build a SmartExcepcionSerializada
	 * @param message
	 * @param ex
	 * @return
	 */
	protected SmartExcepcionSerializada buildError(String message, Throwable ex){
		SmartExcepcionSerializada smartException = new SmartExcepcionSerializada();
		smartException.setCode(0);
		smartException.setMensaje(message+" " +ex.getMessage());
		smartException.setStackTrace(ex.getStackTrace());
		return smartException;
	}

}
