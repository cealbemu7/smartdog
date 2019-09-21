package co.com.smart.dog.bean;

import java.util.List;

import co.com.smart.dog.infraestructure.dto.DatosMaestroDTO;
import co.com.smart.dog.infraestructure.dto.SmartExcepcionSerializada;

public interface DatosMaestroBeanLocal {
	/**
	 * metodo que permite grabar los tipos de datos maestros
	 * @param filtro
	 * @return consultarDatosMestros
	 * @throws SmartExcepcionSerializada
	 */
	
	public List<DatosMaestroDTO> consultarDatosMestros(DatosMaestroDTO filtro) throws SmartExcepcionSerializada;
	

}
