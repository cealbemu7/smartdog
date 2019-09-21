package co.com.smart.dog.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.com.smart.dog.bean.AbstractBean;
import co.com.smart.dog.infraestructure.dto.CiudadDTO;
import co.com.smart.dog.persistence.caller.CiudadCaller;
import co.com.smart.dog.utility.SmartConstant;
/**
*Session Bean implementation class EmpresaFacade
 */
@Stateless(name = "CiudadFacade",
mappedName = "ejb/CiudadFacade")
public class CiudadFacade extends AbstractBean implements CiudadFacadeLocal{
	@PersistenceContext(unitName = "SmartDogPU")
	private EntityManager em;
	protected EntityManager getEntityManager() {
		return em;
	}

	@Override
	public List<CiudadDTO> consultarCiudad(CiudadDTO filtro) throws Throwable {
		List<CiudadDTO> ciudadcon = new ArrayList<>();
		try {
			CiudadCaller caller = new CiudadCaller(SmartConstant.JDNI_CONNECTION);
			ciudadcon = caller.consultarCiudad(filtro);
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ciudadcon;
	}

}
