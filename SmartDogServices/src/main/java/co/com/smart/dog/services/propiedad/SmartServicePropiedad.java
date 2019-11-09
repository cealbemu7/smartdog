package co.com.smart.dog.services.propiedad;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import co.com.smart.dog.infraestructure.dto.PropiedadDTO;
import co.com.smart.dog.services.base.SmartResponseBase;
import co.com.smart.dog.session.DelegateContextEJB;

@Path("/SmartServicePropiedad")
public class SmartServicePropiedad extends SmartResponseBase {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Servicio que permite consultar Propiedad
	 * @param json
	 * @return
	 * @throws Throwable 
	 * @throws SmartExcepcionSerializada 
	 */	
	@POST
	@Path("/consultarPropiedad")
	@Produces(MediaType.APPLICATION_JSON)
	public Response consultarPropiedad (PropiedadDTO json){
		return getResponse(DelegateContextEJB.getPropiedadBean().consultarPropiedad(json));
	}
	/**
	 * Servicio que permite grabar Propiedad
	 * @param json
	 * @return
	 */	
	@POST
	@Path("/grabarPrpiedad")
	@Produces(MediaType.APPLICATION_JSON)
	public Response grabarPropiedad(PropiedadDTO json){
		return getResponse(DelegateContextEJB.getPropiedadBean().grabarPropiedad(json));
		
	}
	
	/**
	 * Servicio que permite eliminar Propiedad
	 * @param json
	 * @return
	 */	
	@POST
	@Path("/eliminarPropiedad")
	@Produces(MediaType.APPLICATION_JSON)
	public Response eliminarPropiedad(PropiedadDTO json){
		return getResponse(DelegateContextEJB.getPropiedadBean().eliminarPropiedad(json));
		
	}

}
