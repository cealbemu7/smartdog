package co.com.smart.dog.bean;

import java.util.List;

import co.com.smart.dog.infraestructure.dto.MaestroDTO;
import co.com.smart.dog.infraestructure.dto.SmartExcepcionSerializada;

public interface MaestroBeanLocal {
	/**
	 * metodo que permite listar maestro
	 * @param filtro
	 * @return listarMaestros
	 * @throws SmartExcepcionSerializada
	 */
	public List<MaestroDTO> listarMaestros(MaestroDTO filtro) throws SmartExcepcionSerializada;


}
