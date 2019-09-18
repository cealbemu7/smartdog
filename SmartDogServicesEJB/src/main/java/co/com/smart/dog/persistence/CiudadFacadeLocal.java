package co.com.smart.dog.persistence;

import java.util.List;

import co.com.smart.dog.infraestructure.dto.CiudadDTO;

public interface CiudadFacadeLocal {
	/**
	 * Permite Consultar una Ciudad
	 * @param filtro
	 * @return ciudad
	 */
	public List<CiudadDTO> consultarCiudad (CiudadDTO filtro)throws Throwable;

}
