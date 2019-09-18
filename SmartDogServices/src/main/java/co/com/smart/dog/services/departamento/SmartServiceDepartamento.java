package co.com.smart.dog.services.departamento;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import co.com.smart.dog.infraestructure.dto.DepartamentoDTO;
import co.com.smart.dog.services.base.SmartResponseBase;
import co.com.smart.dog.session.DelegateContextEJB;

@Path("/SmartServiceDepartamento")
public class SmartServiceDepartamento extends SmartResponseBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Servicio que permite listar un Departamento
	 * @param json
	 * @return
	 */	
	@POST
	@Path("/listarDepartamento")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarDepartamento (DepartamentoDTO json){
		return getResponse(DelegateContextEJB.getDepartamentoBean().consultarDepartamento(json));
	}

}
