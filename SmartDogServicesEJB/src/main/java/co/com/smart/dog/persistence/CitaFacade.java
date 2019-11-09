package co.com.smart.dog.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.com.smart.dog.infraestructure.dto.CitaDTO;
import co.com.smart.dog.persistence.caller.CitaCaller;
import co.com.smart.dog.utility.SmartConstant;

/**
 * Session Bean implementation class CitaFacade 
 * @author deymer
 */
@Stateless(name = "CitaFacade", mappedName = "ejb/CitaFacade")
public class CitaFacade implements CitaFacadeLocal {
	
	@PersistenceContext(unitName = "SmartDogPU")
	private EntityManager em;

	protected EntityManager getEntityManager() {
		return em;
	}
	
	/**
	 * Metodo utilizada para grabar cita
	 */
	@Override
	public CitaDTO GrabarCita(CitaDTO cita) throws Throwable {
		CitaDTO citas = new CitaDTO();
		 try {
				CitaCaller caller = new CitaCaller(SmartConstant.JDNI_CONNECTION);
				citas = caller.GrabarCita(cita);
				
			} catch (NamingException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return citas;
	}
	
	/**
	 * Metodo utilizado para eliminar cita
	 */
	@Override
	public CitaDTO EliminarCita(CitaDTO cita) throws Throwable {
		CitaDTO returnObject = new CitaDTO();
		try {
			CitaCaller caller = new CitaCaller(SmartConstant.JDNI_CONNECTION);
			returnObject = caller.EliminarCita(cita);

		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnObject;
	}
	
	/**
	 * Metodo utilizado para consultar cita
	 */
	@Override
	public List<CitaDTO> ConsultarCita(CitaDTO cita) throws Throwable {
		List<CitaDTO> citas = new ArrayList<>();
		try {
			CitaCaller caller = new CitaCaller(SmartConstant.JDNI_CONNECTION);
			citas = caller.ConsultarCita(cita);

		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return citas;
	}

}
