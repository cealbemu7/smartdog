package co.com.smart.dog.persistence;

import java.sql.SQLException;
import java.util.Map;

import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.com.smart.dog.bean.AbstractBean;
import co.com.smart.dog.infraestructure.dto.UsuarioDTO;
import co.com.smart.dog.persistence.caller.ParametersCaller;
import co.com.smart.dog.persistence.caller.UsuarioCaller;
import co.com.smart.dog.utility.SmartConstant;

/**
 * Session Bean implementation class UsuarioFacade 
 * @author deymer
 */
@Stateless(name = "UsuarioFacade", mappedName = "ejb/UsuarioFacade")
public class UsuarioFacade extends AbstractBean implements UsuarioFacadeLocal {
	@PersistenceContext(unitName = "SmartDogPU")
	private EntityManager em;

	protected EntityManager getEntityManager() {
		return em;
	}
	/***
	 * consultar Usuario
	 */
	@Override
	public UsuarioDTO consultarUsuario(UsuarioDTO usuario) throws Throwable {
		UsuarioDTO usuarioDTO = new UsuarioDTO(); 
		try {
			UsuarioCaller caller = new UsuarioCaller(SmartConstant.JDNI_CONNECTION);
			usuarioDTO = caller.consultarUsuario(usuario);
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuarioDTO;
	}
	/**
	 * metodo utilizado para grabar usuario
	 */
	@Override
	public UsuarioDTO grabarUsuario(UsuarioDTO usuario) throws Throwable {
		UsuarioDTO usuarios = new UsuarioDTO();
		 try {
				UsuarioCaller caller = new UsuarioCaller(SmartConstant.JDNI_CONNECTION);
				usuarios = caller.grabarUsuario(usuario);
				
			} catch (NamingException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return usuarios;
	}
	/**
	 * get params
	 */
	@Override
	public Map<String, String> getParams(String dsparameter)throws NamingException, SQLException {
		ParametersCaller caller = new ParametersCaller(SmartConstant.JDNI_CONNECTION);
		return caller.getParams(dsparameter);
	}
	/**
	 * solicitarRegistroUsuario
	 */
	@Override
	public UsuarioDTO solicitarRegistroUsuario(UsuarioDTO json) throws Throwable {
		UsuarioDTO response = new UsuarioDTO();
		 try {
				UsuarioCaller caller = new UsuarioCaller(SmartConstant.JDNI_CONNECTION);
				response = caller.grabarUsuario(json);
				
			} catch (NamingException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return response;
	}
	
}
