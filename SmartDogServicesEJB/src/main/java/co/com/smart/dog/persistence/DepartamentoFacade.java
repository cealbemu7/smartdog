package co.com.smart.dog.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.com.smart.dog.bean.AbstractBean;
import co.com.smart.dog.infraestructure.dto.DepartamentoDTO;
import co.com.smart.dog.persistence.caller.DepartamentoCaller;
import co.com.smart.dog.utility.SmartConstant;

/**
*Session Bean implementation class DepartamentoFacade
 */
@Stateless(name = "DepartamentoFacade",
		   mappedName = "ejb/DepartamentoFacade")
public class DepartamentoFacade extends AbstractBean implements DepartamentoFacadeLocal{
	@PersistenceContext(unitName = "SmartDogPU")
	private EntityManager em;
	protected EntityManager getEntityManager() {
		return em;
	}

	@Override
	public List<DepartamentoDTO> consultarDepartamento(DepartamentoDTO filtro)throws Throwable{
		List<DepartamentoDTO> departamentocon = new ArrayList<>();
		
		try {
			DepartamentoCaller caller = new DepartamentoCaller(SmartConstant.JDNI_CONNECTION);
			departamentocon = caller.consultarDepartamento(filtro);
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return departamentocon;
	}

}
