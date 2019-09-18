package co.com.smart.dog.persistence;

import java.util.List;

import co.com.smart.dog.infraestructure.dto.PaisDTO;

public interface PaisFacadeLocal {
	/**
	 * Permite Consultar un pais
	 * @param filtro
	 * @return Pais
	 */
	public List<PaisDTO> consultarPais (PaisDTO filtro)throws Throwable;

}
