package co.com.smart.dog.persistence;

import java.sql.SQLException;
import java.util.Map;

import javax.naming.NamingException;

import co.com.smart.dog.infraestructure.dto.UsuarioDTO;

public interface UsuarioFacadeLocal {
	/**
	 * Permite Consultar Usuario
	 * @param usuario
	 * @return usuario
	 */
	public UsuarioDTO consultarUsuario (UsuarioDTO usuario)throws Throwable;
	/**
	 * Metodo utilizado para grabar usuario
	 * @param usuario
	 * @return
	 * @throws Throwable
	 */
	public UsuarioDTO grabarUsuario(UsuarioDTO usuario)throws Throwable;
	/**
	 * getParams
	 * @param dsparameter
	 * @return
	 * @throws NamingException
	 * @throws SQLException
	 */
	Map<String, String> getParams(String dsparameter) throws NamingException,
			SQLException;
	/**
	 * solicitarRegistroUsuario
	 * @param json
	 * @return
	 * @throws Throwable
	 */
	public UsuarioDTO solicitarRegistroUsuario(UsuarioDTO json)throws Throwable; 
}
