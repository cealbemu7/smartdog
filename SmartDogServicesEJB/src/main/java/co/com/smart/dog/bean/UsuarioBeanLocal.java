package co.com.smart.dog.bean;

import java.util.List;

import co.com.smart.dog.infraestructure.dto.SmartExcepcionSerializada;
import co.com.smart.dog.infraestructure.dto.UsuarioDTO;

public interface UsuarioBeanLocal {
	/**
	 * metodo utilizado para grabar usuario
	 * @param usuario
	 * @return
	 * @throws SmartExcepcionSerializada
	 */
	public List<UsuarioDTO> consultarUsuario (UsuarioDTO usuario)throws SmartExcepcionSerializada;
	
	/**
	 * metodo utilizado para grabar usuario
	 * @param usuario
	 * @return
	 * @throws SmartExcepcionSerializada
	 */
	public UsuarioDTO grabarUsuario(UsuarioDTO usuario)throws SmartExcepcionSerializada;
}
