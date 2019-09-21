package co.com.smart.dog.bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import co.com.smart.dog.infraestructure.dto.SmartExcepcionSerializada;
import co.com.smart.dog.infraestructure.dto.UsuarioDTO;
import co.com.smart.dog.persistence.UsuarioFacadeLocal;
import co.com.smart.dog.session.DelegateContextEJB;

/**
 * Session Bean implementation class UsuarioBean
 */
@Stateless(name = "UsuarioBean",
mappedName = "ejb/UsuarioBean")
public class UsuarioBean extends AbstractBean implements UsuarioFacadeLocal {
	private UsuarioFacadeLocal facade;
	/**
	 * Default constructor
	 */
	public UsuarioBean(){
		facade = DelegateContextEJB.getUsuarioFacade();
	}
	@Override
	public List<UsuarioDTO> consultarUsuario(UsuarioDTO usuario) throws Throwable {
		List<UsuarioDTO> usuarios = new ArrayList<>();
		try {
			usuarios = facade.consultarUsuario(usuario);
		} catch(Throwable ex){
			ex.printStackTrace(System.err);
			SmartExcepcionSerializada smartException = new SmartExcepcionSerializada();
			smartException.setCode(0);
			smartException.setMensaje(ex.getMessage());
			smartException.setStackTrace(ex.getStackTrace());
			throw smartException;
		}
		return usuarios;
	}

}
