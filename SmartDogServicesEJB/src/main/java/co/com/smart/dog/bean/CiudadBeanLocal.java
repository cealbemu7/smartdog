package co.com.smart.dog.bean;

import java.util.List;

import co.com.smart.dog.infraestructure.dto.CiudadDTO;
import co.com.smart.dog.infraestructure.dto.SmartExcepcionSerializada;

public interface CiudadBeanLocal {
	/**
	 * Permite consultar una Ciudad
	 * @param filtro
	 * @return ciudad
	 * @throws SmartExcepcionSerializada
	 */
	public List<CiudadDTO> consultarCiudad (CiudadDTO filtro)throws SmartExcepcionSerializada;

}
