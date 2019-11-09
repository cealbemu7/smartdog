package co.com.smart.dog.services.cita;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import co.com.smart.dog.infraestructure.dto.CitaDTO;
import co.com.smart.dog.services.annotations.SmartSecurity;
import co.com.smart.dog.services.base.SmartResponseBase;
import co.com.smart.dog.session.DelegateContextEJB;

@Path("/SmartServiceCita")
public class SmartServiceCita extends SmartResponseBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Servicio que permite grabar cita
	 * @param json
	 * @return
	 * @throws Throwable 
	 */	
	@POST
	@Path("/GrabarCita")
	@Produces(MediaType.APPLICATION_JSON)
	@SmartSecurity
	public Response GrabarCita(CitaDTO json){
		return getResponse(DelegateContextEJB.getCitaBean().GrabarCita(json));		
	}
	
	/**
	 * Servicio que permite eliminar cita
	 * @param json
	 * @return
	 * @throws Throwable 
	 */	
	@POST
	@Path("/EliminarCita")
	@Produces(MediaType.APPLICATION_JSON)
	@SmartSecurity
	public Response EliminarCita(CitaDTO json){
		return getResponse(DelegateContextEJB.getCitaBean().EliminarCita(json));		
	}
	
	/**
	 * Servicio que permite consultar cita
	 * @param json
	 * @return
	 * @throws Throwable 
	 */	
	@POST
	@Path("/ConsultarCita")
	@Produces(MediaType.APPLICATION_JSON)
	@SmartSecurity
	public Response ConsultarCita(CitaDTO json){
		return getResponse(DelegateContextEJB.getCitaBean().ConsultarCita(json));		
	}

}
