package co.com.smart.dog.bean;

import java.util.List;

import co.com.smart.dog.infraestructure.dto.DepartamentoDTO;
import co.com.smart.dog.infraestructure.dto.SmartExcepcionSerializada;

public interface DepartamentoBeanLocal {
	/**
	 * Permite consultar un Departamento
	 * @param filtro
	 * @return departamento
	 * @throws SmartExcepcionSerializada
	 */
	public List<DepartamentoDTO> consultarDepartamento (DepartamentoDTO filtro)throws SmartExcepcionSerializada;


}
