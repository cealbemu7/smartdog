package co.com.smart.dog.persistence;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.com.smart.dog.bean.AbstractBean;
import co.com.smart.dog.infraestructure.dto.AsesorDTO;
import co.com.smart.dog.persistence.caller.AsesorCaller;
import co.com.smart.dog.utility.SmartConstant;


@Stateless(name = "AsesorFacade", mappedName = "ejb/AsesorFacade")
public class AsesorFacade extends AbstractBean  implements AsesorFacadeLocal {
	@PersistenceContext(unitName = "SmartDogPU")
	private EntityManager em;

	protected EntityManager getEntityManager() {
		return em;
	}

	
	
	
	
	
	@Override
	public List<AsesorDTO> consultarAsesor(AsesorDTO asesorlist) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AsesorDTO grabarAsesor(AsesorDTO asesor) throws Throwable {

		AsesorDTO asesorreturn = new AsesorDTO();
		try {
			AsesorCaller caller = new AsesorCaller(SmartConstant.JDNI_CONNECTION);
			asesorreturn = caller.grabarAsesor(asesor);

		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return asesorreturn;
	}

	@Override
	public AsesorDTO eliminarAsesor(AsesorDTO asesor) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AsesorDTO actulizarAsesor(AsesorDTO asesor) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

}
