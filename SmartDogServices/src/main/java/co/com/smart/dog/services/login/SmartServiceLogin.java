package co.com.smart.dog.services.login;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import co.com.smart.dog.infraestructure.dto.EntidadDTO;
import co.com.smart.dog.infraestructure.dto.LoginDTO;
import co.com.smart.dog.services.annotations.SmartSecurity;
import co.com.smart.dog.services.base.SmartResponseBase;
import co.com.smart.dog.session.DelegateContextEJB;

@Path("/SmartServiceLogin")
public class SmartServiceLogin extends SmartResponseBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * login
	 * @param json
	 * @return
	 */	
	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@SmartSecurity(apply = false)
	public Response login(LoginDTO json){
		 return getResponse(DelegateContextEJB.getLoginBean().generateSecureToken(json));
	}
	
	/**
	 * login
	 * @param json
	 * @return
	 */	
	@POST
	@Path("/resources")
	@Produces(MediaType.APPLICATION_JSON)
	@SmartSecurity
	public Response getResources(LoginDTO json){
		return getResponse(DelegateContextEJB.getLoginBean().getResources(json));
	}

	/**
	 * getEntityUser
	 * @param json
	 * @return
	 */	
	@POST
	@Path("/entitiesUser")
	@Produces(MediaType.APPLICATION_JSON)
	@SmartSecurity
	public Response getEntitiesUser(LoginDTO json){
		return getResponse(DelegateContextEJB.getLoginBean().getEntitiesUser(json));
	}
	
	/**
	 * getEntityUser
	 * @param json
	 * @return
	 */	
	@POST
	@Path("/getEmpresaEntidad")
	@Produces(MediaType.APPLICATION_JSON)
	@SmartSecurity
	public Response getEmpresaEntidad(EntidadDTO json){
		return getResponse(DelegateContextEJB.getLoginBean().getEmpresaEntidad(json));
	}

	
}
