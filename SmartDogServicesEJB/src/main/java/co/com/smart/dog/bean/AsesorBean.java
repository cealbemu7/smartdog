package co.com.smart.dog.bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import co.com.smart.dog.infraestructure.dto.AsesorDTO;
import co.com.smart.dog.infraestructure.dto.SmartExcepcionSerializada;
import co.com.smart.dog.persistence.AsesorFacadeLocal;
import co.com.smart.dog.session.DelegateContextEJB;

@Stateless(name = "AsesorBean", mappedName = "ejb/AsesorBean")
public class AsesorBean extends AbstractBean implements AsesorBeanLocal {
	private AsesorFacadeLocal facade;

	/**
	 * Default constructor
	 */
	public AsesorBean() {
		facade = DelegateContextEJB.getAsesorFacade();

	}

	
	
	@Override
	public AsesorDTO grabarAsesor(AsesorDTO Asesor) throws SmartExcepcionSerializada {
		AsesorDTO asesorreturn = new AsesorDTO();
		try {
			asesorreturn = facade.grabarAsesor(Asesor);
		} catch (Throwable ex) {
			ex.printStackTrace(System.err);
			SmartExcepcionSerializada smartException = new SmartExcepcionSerializada();
			smartException.setCode(0);
			smartException.setMensaje(ex.getMessage());
			smartException.setStackTrace(ex.getStackTrace());
			throw smartException;
		}
		return asesorreturn;
	}



	@Override
	public List<AsesorDTO> consultarAsesor(AsesorDTO json) throws SmartExcepcionSerializada {
		List<AsesorDTO> aesorlist = new ArrayList<>();
		try {
			aesorlist = facade.consultarAsesor(json);
		} catch (Throwable ex) {
			ex.printStackTrace(System.err);
			SmartExcepcionSerializada smartException = new SmartExcepcionSerializada();
			smartException.setCode(0);
			smartException.setMensaje(ex.getMessage());
			smartException.setStackTrace(ex.getStackTrace());
			throw smartException;
		}
		return aesorlist;
	}



	@Override
	public AsesorDTO eliminarAsesor(AsesorDTO json) throws SmartExcepcionSerializada {
		AsesorDTO returnObject = new AsesorDTO();
		try {
			returnObject = facade.eliminarAsesor(json);
		} catch (Throwable ex) {
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
