package co.com.smart.dog.bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import co.com.smart.dog.infraestructure.dto.DepartamentoDTO;
import co.com.smart.dog.infraestructure.dto.SmartExcepcionSerializada;
import co.com.smart.dog.persistence.DepartamentoFacadeLocal;
import co.com.smart.dog.session.DelegateContextEJB;
/**
 * Session Bean implementation class DepartamentoBean
 */
@Stateless(name = "DepartamentoBean",
		   mappedName = "ejb/DepartamentoBean")
public class DepartamentoBean extends AbstractBean implements DepartamentoBeanLocal{
	private DepartamentoFacadeLocal facade;
	/**
	 * Default constructor
	 */
	public DepartamentoBean(){
		facade = DelegateContextEJB.getDepartamentoFacade();
	}

	@Override
	public List<DepartamentoDTO> consultarDepartamento(DepartamentoDTO filtro)
			throws SmartExcepcionSerializada {
		List<DepartamentoDTO> departamentolis = new ArrayList<>();
		try {
			departamentolis = facade.consultarDepartamento(filtro);
		} catch(Throwable ex){
			ex.printStackTrace(System.err);
			SmartExcepcionSerializada smartException = new SmartExcepcionSerializada();
			smartException.setCode(0);
			smartException.setMensaje(ex.getMessage());
			smartException.setStackTrace(ex.getStackTrace());
			throw smartException;
		}
		return departamentolis;
	}

}
