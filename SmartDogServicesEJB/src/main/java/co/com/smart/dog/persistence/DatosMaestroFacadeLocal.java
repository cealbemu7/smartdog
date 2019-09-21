package co.com.smart.dog.persistence;

import java.util.List;

import co.com.smart.dog.infraestructure.dto.DatosMaestroDTO;

public interface DatosMaestroFacadeLocal {
	/**
	 * permime listar datosMaestro
	 * @param filtro
	 * @return List<DatosMaestroDTO> 
	 */
	public List<DatosMaestroDTO> consultarDatosMestros(DatosMaestroDTO filtro)throws Throwable;

}
