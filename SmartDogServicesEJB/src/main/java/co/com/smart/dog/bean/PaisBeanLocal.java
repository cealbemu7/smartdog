package co.com.smart.dog.bean;

import java.util.List;

import co.com.smart.dog.infraestructure.dto.PaisDTO;
import co.com.smart.dog.infraestructure.dto.SmartExcepcionSerializada;

public interface PaisBeanLocal {
	/**
	 * Permite consultar un Pais
	 * @param filtro
	 * @return Pais
	 * @throws SmartExcepcionSerializada
	 */
	public List<PaisDTO> consultarPais (PaisDTO filtro)throws SmartExcepcionSerializada;



}
