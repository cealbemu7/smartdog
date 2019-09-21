package co.com.smart.dog.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.com.smart.dog.bean.AbstractBean;
import co.com.smart.dog.infraestructure.dto.PaisDTO;
import co.com.smart.dog.persistence.caller.PaisCaller;
import co.com.smart.dog.utility.SmartConstant;

/**
*Session Facade implementation class PaisFacade
 */
@Stateless(name = "PaisFacade",
		   mappedName = "ejb/PaisFacade")
public class PaisFacade extends AbstractBean implements PaisFacadeLocal{
	
	@PersistenceContext(unitName = "SmartDogPU")
	private EntityManager em;
	protected EntityManager getEntityManager() {
		return em;
	}

	@Override
	public List<PaisDTO> consultarPais(PaisDTO filtro)throws Throwable {
List<PaisDTO> pais = new ArrayList<>();
		
		try {
			PaisCaller caller = new PaisCaller(SmartConstant.JDNI_CONNECTION);
			pais = caller.consultarPais(filtro);
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pais;
	}

}
