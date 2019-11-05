package co.com.smart.dog.bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import co.com.smart.dog.infraestructure.dto.PropiedadDTO;
import co.com.smart.dog.infraestructure.dto.SmartExcepcionSerializada;
import co.com.smart.dog.persistence.PropiedadFacadeLocal;
import co.com.smart.dog.session.DelegateContextEJB;

@Stateless(name = "PropiedadBean",
mappedName = "ejb/PropiedadBean")
public class PropiedadBean extends AbstractBean implements PropiedadBeanLocal {
	private PropiedadFacadeLocal facade;
	
	/**
	 * Default constructor
	 */
	public PropiedadBean(){
		facade = DelegateContextEJB.getPropiedadFacade();
	
		}
 /**
  * 
  */
	@Override
	public List<PropiedadDTO> consultarPropiedad(PropiedadDTO propiedad) throws SmartExcepcionSerializada {
		List<PropiedadDTO> Propiedadlist = new ArrayList<>();
		try {
			Propiedadlist = facade.consultarPropiedad(propiedad);
		} catch(Throwable ex){
			ex.printStackTrace(System.err);
			SmartExcepcionSerializada smartException = new SmartExcepcionSerializada();
			smartException.setCode(0);
			smartException.setMensaje(ex.getMessage());
			smartException.setStackTrace(ex.getStackTrace());
			throw smartException;
		}
		return  Propiedadlist;
		
	}

	@Override
	public PropiedadDTO grabarPropiedad(PropiedadDTO propiedad) throws SmartExcepcionSerializada {
		PropiedadDTO Propiedadreturn = new PropiedadDTO();
		try {
			Propiedadreturn = facade.grabarPropiedad(propiedad);
		} catch(Throwable ex){
			ex.printStackTrace(System.err);
			SmartExcepcionSerializada smartException = new SmartExcepcionSerializada();
			smartException.setCode(0);
			smartException.setMensaje(ex.getMessage());
			smartException.setStackTrace(ex.getStackTrace());
			throw smartException;
		}
		return Propiedadreturn;
	}

	@Override
	public PropiedadDTO actulizarPropiedad(PropiedadDTO propiedad) throws SmartExcepcionSerializada {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PropiedadDTO eliminarPropiedad(PropiedadDTO propiedad) throws SmartExcepcionSerializada {
		PropiedadDTO returnObject = new PropiedadDTO();
		try {
			returnObject = facade.eliminarPropiedad(propiedad);
		} catch(Throwable ex){
			ex.printStackTrace(System.err);
			SmartExcepcionSerializada smartException = new SmartExcepcionSerializada();
			smartException.setCode(0);
			smartException.setMensaje(ex.getMessage());
			smartException.setStackTrace(ex.getStackTrace());
			throw smartException;
		}
		return returnObject;
	}

}
