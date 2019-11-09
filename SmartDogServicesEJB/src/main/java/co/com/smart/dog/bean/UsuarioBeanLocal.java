package co.com.smart.dog.bean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

import co.com.smart.dog.infraestructure.dto.SmartExcepcionSerializada;
import co.com.smart.dog.infraestructure.dto.UsuarioDTO;

public interface UsuarioBeanLocal {
	/**
	 * metodo utilizado para grabar usuario
	 * @param usuario
	 * @return
	 * @throws SmartExcepcionSerializada
	 */
	public UsuarioDTO consultarUsuario (UsuarioDTO usuario)throws SmartExcepcionSerializada;
	
	/**
	 * metodo utilizado para grabar usuario
	 * @param usuario
	 * @return
	 * @throws SmartExcepcionSerializada
	 */
	public UsuarioDTO grabarUsuario(UsuarioDTO usuario)throws SmartExcepcionSerializada;
	
	/**
	 * generateSecureToken
	 * @param json
	 * @return
	 * @throws SmartExcepcionSerializada
	 */
	String generateSecureToken(UsuarioDTO json) throws SmartExcepcionSerializada;
	
	/**
	 * getInfoSecureToken
	 * @param token
	 * @return
	 * @throws SmartExcepcionSerializada
	 */
	Jws<Claims> getInfoSecureToken(String token);

	/**
	 * solicitarRegistroUsuario
	 * @param json
	 * @return
	 */
	public UsuarioDTO solicitarRegistroUsuario(UsuarioDTO json)throws SmartExcepcionSerializada;
	
}
