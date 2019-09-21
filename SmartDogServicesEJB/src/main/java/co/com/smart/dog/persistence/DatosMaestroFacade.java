package co.com.smart.dog.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.com.smart.dog.bean.AbstractBean;
import co.com.smart.dog.infraestructure.dto.DatosMaestroDTO;
import co.com.smart.dog.persistence.caller.DatosMaestroCaller;
import co.com.smart.dog.utility.SmartConstant;
/**
*Session Bean implementation class ActividadesFacede
 */
@Stateless(name = "DatosMaestroFacade",
mappedName = "ejb/DatosMaestroFacade")
public class DatosMaestroFacade extends AbstractBean implements DatosMaestroFacadeLocal{
	@PersistenceContext(unitName = "SmartDogPU")
	private EntityManager em;
	
	protected EntityManager getEntityManager() {
		return em;
	}

	@Override
	public List<DatosMaestroDTO> consultarDatosMestros(DatosMaestroDTO filtro)
			throws Throwable {
		List<DatosMaestroDTO> listDatosMaestro = new ArrayList<>();
		try {
			DatosMaestroCaller caller= new DatosMaestroCaller(SmartConstant.JDNI_CONNECTION);
			listDatosMaestro = caller.consultarDatosMestros(filtro); 
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		return listDatosMaestro;
	}

}
