package co.com.smart.dog.bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import co.com.smart.dog.infraestructure.dto.PaisDTO;
import co.com.smart.dog.infraestructure.dto.SmartExcepcionSerializada;
import co.com.smart.dog.persistence.PaisFacadeLocal;
import co.com.smart.dog.session.DelegateContextEJB;

/**
 * Session Bean implementation class PaisBean
 */
@Stateless(name = "PaisBean",
		   mappedName = "ejb/PaisBean")
public class PaisBean extends AbstractBean implements PaisBeanLocal{
	private PaisFacadeLocal facade;
	/**
	 * Default constructor
	 */
	public PaisBean(){
		facade = DelegateContextEJB.getPaisFacade();
	}

	@Override
	public List<PaisDTO> consultarPais(PaisDTO filtro) throws SmartExcepcionSerializada {
		List<PaisDTO> pais = new ArrayList<>();
		try {
			pais = facade.consultarPais(filtro);
		} catch(Throwable ex){
			ex.printStackTrace(System.err);
			SmartExcepcionSerializada smartException = new SmartExcepcionSerializada();
			smartException.setCode(0);
			smartException.setMensaje(ex.getMessage());
			smartException.setStackTrace(ex.getStackTrace());
			throw smartException;
		}
		return pais;
	}

}
