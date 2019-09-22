package co.com.smart.dog.services.empresa;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import co.com.smart.dog.infraestructure.dto.EmpresaDTO;
import co.com.smart.dog.services.base.SmartResponseBase;
import co.com.smart.dog.session.DelegateContextEJB;

@Path("/SmartServiceEmpresa")
public class SmartServiceEmpresa extends SmartResponseBase{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Servicio que permite consultar Empresa
	 * @param json
	 * @return
	 */	
	@POST
	@Path("/consultarUsuario")
	@Produces(MediaType.APPLICATION_JSON)
	public Response consultarEmpresa (EmpresaDTO json){
		return getResponse(DelegateContextEJB.getEmpresaBean().consultarEmpresa(json));
	}

}
