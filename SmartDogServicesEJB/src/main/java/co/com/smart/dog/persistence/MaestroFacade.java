package co.com.smart.dog.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.com.smart.dog.bean.AbstractBean;
import co.com.smart.dog.infraestructure.dto.MaestroDTO;
import co.com.smart.dog.persistence.caller.MaestroCaller;
import co.com.smart.dog.utility.SmartConstant;
/**
*Session Bean implementation class MaestroFacede
*/
@Stateless(name = "MaestroFacade",
mappedName = "ejb/MaestroFacade")
public class MaestroFacade extends AbstractBean implements MaestroFacadeLocal{
	@PersistenceContext(unitName = "SmartDogPU")
	private EntityManager em;
	
	protected EntityManager getEntityManager() {
		return em;
	}

	@Override
	public List<MaestroDTO> listarMaestros(MaestroDTO filtro) throws Throwable {
		List<MaestroDTO> listmaestro = new ArrayList<>();
		try {
			System.err.println("Llego por la fachada");
			MaestroCaller caller= new MaestroCaller(SmartConstant.JDNI_CONNECTION);
			listmaestro = caller.listarMaestros(filtro);
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		return listmaestro;
	}

}
