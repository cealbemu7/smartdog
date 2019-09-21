package co.com.smart.dog.bean;

import java.util.List;

import co.com.smart.dog.infraestructure.dto.SmartExcepcionSerializada;
import co.com.smart.dog.infraestructure.dto.UsuarioDTO;

public interface UsuarioBeanLocal {
	public List<UsuarioDTO> consultarUsuario (UsuarioDTO usuario)throws SmartExcepcionSerializada;
}
