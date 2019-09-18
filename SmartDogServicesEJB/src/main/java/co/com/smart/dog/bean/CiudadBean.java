package co.com.smart.dog.bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import co.com.smart.dog.infraestructure.dto.CiudadDTO;
import co.com.smart.dog.infraestructure.dto.SmartExcepcionSerializada;
import co.com.smart.dog.persistence.CiudadFacadeLocal;
import co.com.smart.dog.session.DelegateContextEJB;
/**
 * Session Bean implementation class CiudadBean
 */
@Stateless(name = "CiudadBean",
		   mappedName = "ejb/CiudadBean")
public class CiudadBean extends AbstractBean implements CiudadBeanLocal{
	private CiudadFacadeLocal facade;
	/**
	 * Default constructor
	 */
	public CiudadBean(){
		facade = DelegateContextEJB.getCiudadFacade();
	}

	@Override
	public List<CiudadDTO> consultarCiudad(CiudadDTO filtro)throws SmartExcepcionSerializada {
		List<CiudadDTO> ciudadlis = new ArrayList<>();
		try {
			ciudadlis = facade.consultarCiudad(filtro);
		} catch(Throwable ex){
			ex.printStackTrace(System.err);
			SmartExcepcionSerializada smartException = new SmartExcepcionSerializada();
			smartException.setCode(0);
			smartException.setMensaje(ex.getMessage());
			smartException.setStackTrace(ex.getStackTrace());
			throw smartException;
		}
		return ciudadlis;
	}

}
