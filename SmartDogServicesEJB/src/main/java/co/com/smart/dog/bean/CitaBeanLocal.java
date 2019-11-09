package co.com.smart.dog.bean;

import java.util.List;

import co.com.smart.dog.infraestructure.dto.CitaDTO;
import co.com.smart.dog.infraestructure.dto.SmartExcepcionSerializada;

public interface CitaBeanLocal {
	/**
	 * Metodo utilizado para grabar ciat
	 * @param cita
	 * @return
	 * @throws SmartExcepcionSerializada
	 */
	public CitaDTO GrabarCita (CitaDTO cita) throws SmartExcepcionSerializada;
	
	/**
	 * Metodo utilizado para eliminar cita
	 * @param cita
	 * @return
	 * @throws SmartExcepcionSerializada
	 */
	public CitaDTO EliminarCita(CitaDTO cita) throws SmartExcepcionSerializada;
	
	/**
	 * Metodo utilizado para consultar cita
	 * @param cita
	 * @return
	 * @throws SmartExcepcionSerializada
	 */
	public List<CitaDTO> ConsultarCita (CitaDTO cita) throws SmartExcepcionSerializada;

}
