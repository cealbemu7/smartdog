package co.com.smart.dog.persistence;

import java.util.List;

import co.com.smart.dog.infraestructure.dto.CitaDTO;

public interface CitaFacadeLocal {
	/**
	 * Metodo utilizado para grabar cita
	 * @param cita
	 * @return
	 * @throws Throwable
	 */
	public CitaDTO GrabarCita (CitaDTO cita) throws Throwable;
	
	/**
	 * Metodo utilizado para eliminar cita
	 * @param cita
	 * @return
	 * @throws Throwable
	 */
	public CitaDTO EliminarCita(CitaDTO cita) throws Throwable;
	
	/**
	 * Metodo utilizado para consultar cita
	 * @param cita
	 * @return
	 * @throws Throwable
	 */
	public List<CitaDTO> ConsultarCita (CitaDTO cita) throws Throwable;

}
