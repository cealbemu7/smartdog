package co.com.smart.dog.persistence;

import java.util.List;

import co.com.smart.dog.infraestructure.dto.DepartamentoDTO;

public interface DepartamentoFacadeLocal {
	/**
	 * Permite Consultar una departamento
	 * @param filtro
	 * @return departamento
	 */
	public List<DepartamentoDTO> consultarDepartamento (DepartamentoDTO filtro)throws Throwable;

}
