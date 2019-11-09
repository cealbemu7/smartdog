package co.com.smart.dog.bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import co.com.smart.dog.infraestructure.dto.CitaDTO;
import co.com.smart.dog.infraestructure.dto.SmartExcepcionSerializada;
import co.com.smart.dog.persistence.CitaFacadeLocal;
import co.com.smart.dog.session.DelegateContextEJB;

@Stateless(name = "CitaBean", mappedName = "ejb/CitaBean")
public class CitaBean extends AbstractBean implements CitaBeanLocal {
	
	private CitaFacadeLocal facade;
	/**
	 * Default constructor
	 */
	public CitaBean(){
		facade = DelegateContextEJB.getCitaFacade();	
	}
	
	/**
	 * Metodo utilizado para grabar cita
	 */
	@Override
	public CitaDTO GrabarCita(CitaDTO cita) throws SmartExcepcionSerializada {
		CitaDTO citas = new CitaDTO();
		try {
			citas = facade.GrabarCita(cita);
		} catch(Throwable ex){
			ex.printStackTrace(System.err);
			SmartExcepcionSerializada smartException = new SmartExcepcionSerializada();
			smartException.setCode(0);
			smartException.setMensaje(ex.getMessage());
			smartException.setStackTrace(ex.getStackTrace());
			throw smartException;
		}
		return citas;
	}
	
	/**
	 * Metodo utilizado para eliminar cita
	 */
	@Override
	public CitaDTO EliminarCita(CitaDTO cita) throws SmartExcepcionSerializada {
		CitaDTO returnObject = new CitaDTO();
		try {
			returnObject = facade.EliminarCita(cita);
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
	
	/**
	 * Metodo utilizado para consultar cita
	 */
	@Override
	public List<CitaDTO> ConsultarCita(CitaDTO cita) throws SmartExcepcionSerializada {
		List<CitaDTO> listarcita = new ArrayList<>();
		try {
			listarcita = facade.ConsultarCita(cita);
		} catch (Throwable ex) {
			ex.printStackTrace(System.err);
			SmartExcepcionSerializada smartException = new SmartExcepcionSerializada();
			smartException.setCode(0);
			smartException.setMensaje(ex.getMessage());
			smartException.setStackTrace(ex.getStackTrace());
			throw smartException;
		}
		return listarcita;
	}

}
