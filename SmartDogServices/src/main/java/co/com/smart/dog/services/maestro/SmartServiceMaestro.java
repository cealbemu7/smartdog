package co.com.smart.dog.services.maestro;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import co.com.smart.dog.infraestructure.dto.MaestroDTO;
import co.com.smart.dog.services.base.SmartResponseBase;
import co.com.smart.dog.session.DelegateContextEJB;

@Path("/SmartServiceMaestro")
public class SmartServiceMaestro extends SmartResponseBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * metodo para listar maestro
	 * @param json
	 * @return Response 
	 */
	@POST
	@Path("/listarMaestros")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarMaestros(MaestroDTO json){
		return getResponse(DelegateContextEJB.getMaestroBean().listarMaestros(json));
	}

}
