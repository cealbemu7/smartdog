package co.com.smart.dog.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.com.smart.dog.bean.AbstractBean;
import co.com.smart.dog.infraestructure.dto.UsuarioDTO;
import co.com.smart.dog.persistence.caller.UsuarioCaller;
import co.com.smart.dog.utility.SmartConstant;

/**
 * Session Bean implementation class UsuarioFacade
 * 
 * @author deymer
 *
 */
@Stateless(name = "UsuarioFacade", mappedName = "ejb/UsuarioFacade")
public class UsuarioFacade extends AbstractBean implements UsuarioFacadeLocal {
	@PersistenceContext(unitName = "SmartDogPU")
	private EntityManager em;

	protected EntityManager getEntityManager() {
		return em;
	}

	@Override
	public List<UsuarioDTO> consultarUsuario(UsuarioDTO usuario) throws Throwable {
		List<UsuarioDTO> usuarios = new ArrayList<>();
		try {
			UsuarioCaller caller = new UsuarioCaller(SmartConstant.JDNI_CONNECTION);
			usuarios = caller.consultarUsuario(usuario);
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuarios;
	}

}
