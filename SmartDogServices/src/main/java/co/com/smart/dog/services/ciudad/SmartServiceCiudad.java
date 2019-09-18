package co.com.smart.dog.services.ciudad;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import co.com.smart.dog.infraestructure.dto.CiudadDTO;
import co.com.smart.dog.services.base.SmartResponseBase;
import co.com.smart.dog.session.DelegateContextEJB;

@Path("/SmartServiceCiudad")
public class SmartServiceCiudad extends SmartResponseBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Servicio que permite listar una ciudad
	 * @param json
	 * @return
	 */	
	@POST
	@Path("/listarCiudad")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarCiudad (CiudadDTO json){
		return getResponse(DelegateContextEJB.getCiudadBean().consultarCiudad(json));
	}

}
