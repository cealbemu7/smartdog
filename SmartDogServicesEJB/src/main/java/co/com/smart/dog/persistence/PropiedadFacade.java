package co.com.smart.dog.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.com.smart.dog.bean.AbstractBean;
import co.com.smart.dog.infraestructure.dto.PropiedadDTO;
import co.com.smart.dog.persistence.caller.PropiedadCaller;
import co.com.smart.dog.utility.SmartConstant;

@Stateless(name = "PropiedadFacade", mappedName = "ejb/PropiedadFacade")
public class PropiedadFacade extends AbstractBean implements PropiedadFacadeLocal {
	@PersistenceContext(unitName = "SmartDogPU")
	private EntityManager em;

	protected EntityManager getEntityManager() {
		return em;
	}

	@Override
	public List<PropiedadDTO> consultarPropiedad(PropiedadDTO propiedad) throws Throwable {
		List<PropiedadDTO> propiedadlist = new ArrayList<>();
		try {
			PropiedadCaller caller = new PropiedadCaller(SmartConstant.JDNI_CONNECTION);
			propiedadlist = caller.consultarEmpresa(propiedad);

		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return propiedadlist;
	}

	@Override
	public PropiedadDTO grabarPropiedad(PropiedadDTO propiedad) throws Throwable {
		PropiedadDTO findpropiedad = new PropiedadDTO();
		try {
			PropiedadCaller caller = new PropiedadCaller(SmartConstant.JDNI_CONNECTION);
			findpropiedad = caller.grabarPropiedad(propiedad);

		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return findpropiedad;
	}

	@Override
	public PropiedadDTO actulizarPropiedad(PropiedadDTO propiedad) throws Throwable {
		PropiedadDTO findpropiedad = new PropiedadDTO();
		try {
			PropiedadCaller caller = new PropiedadCaller(SmartConstant.JDNI_CONNECTION);
			findpropiedad = caller.actulizarPropiedad(propiedad);

		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return findpropiedad;
	}

	@Override
	public PropiedadDTO eliminarPropiedad(PropiedadDTO propiedad) throws Throwable {
		PropiedadDTO returnObject = new PropiedadDTO();
		try {
			PropiedadCaller caller = new PropiedadCaller(SmartConstant.JDNI_CONNECTION);
			returnObject = caller.eliminarPropiedad(propiedad);
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		return returnObject;
	}

}
