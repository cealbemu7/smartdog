package co.com.smart.dog.services.datosmaestro;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import co.com.smart.dog.infraestructure.dto.DatosMaestroDTO;
import co.com.smart.dog.services.base.SmartResponseBase;
import co.com.smart.dog.session.DelegateContextEJB;

@Path("/SmartServiceDatosMaestros")
public class SmartServiceDatosMaestros extends SmartResponseBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@POST
	@Path("/consultarDatosMestros")
	@Produces(MediaType.APPLICATION_JSON)
	public Response consultarDatosMestros(DatosMaestroDTO json){
		return getResponse(DelegateContextEJB.getDatosMaestroBean().consultarDatosMestros(json));
	}

}
