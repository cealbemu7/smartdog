package co.com.smart.dog.persistence;

import java.util.List;

import co.com.smart.dog.infraestructure.dto.UsuarioDTO;

public interface UsuarioFacadeLocal {
	/**
	 * Permite Consultar Usuario
	 * @param usuario
	 * @return usuario
	 */
	public List<UsuarioDTO> consultarUsuario (UsuarioDTO usuario)throws Throwable;
}