package co.com.smart.dog.persistence;

import java.util.List;

import co.com.smart.dog.infraestructure.dto.MaestroDTO;

public interface MaestroFacadeLocal {
	/**
	 *permite listar maestro
	 * @param filtro
	 * @return List<MaestroDTO>
	 */
	public List<MaestroDTO> listarMaestros(MaestroDTO filtro)throws Throwable;

}
