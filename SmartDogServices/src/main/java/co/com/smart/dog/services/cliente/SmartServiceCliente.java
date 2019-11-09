package co.com.smart.dog.services.cliente;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import co.com.smart.dog.infraestructure.dto.ClienteDTO;
import co.com.smart.dog.services.annotations.SmartSecurity;
import co.com.smart.dog.services.base.SmartResponseBase;
import co.com.smart.dog.session.DelegateContextEJB;

@Path("/SmartServiceCliente")
public class SmartServiceCliente extends SmartResponseBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Servicio que permite grabar cliente
	 * @param json
	 * @return
	 * @throws Throwable 
	 */	
	@POST
	@Path("/GrabarCliente")
	@Produces(MediaType.APPLICATION_JSON)
	@SmartSecurity
	public Response GrabarCliente(ClienteDTO json){
		return getResponse(DelegateContextEJB.getClienteBean().GrabarCliente(json));		
	}
}
