package co.com.smart.dog.services.pais;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import co.com.smart.dog.infraestructure.dto.PaisDTO;
import co.com.smart.dog.services.base.SmartResponseBase;
import co.com.smart.dog.session.DelegateContextEJB;

@Path("/SmartServicePais")
public class SmartServicePais extends SmartResponseBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Servicio que permite listar un Pais
	 * @param json
	 * @return
	 */	
	@POST
	@Path("/consultarPais")
	@Produces(MediaType.APPLICATION_JSON)
	public Response consultarPais (PaisDTO json){
		return getResponse(DelegateContextEJB.getPaisBean().consultarPais(json));
	}

}
