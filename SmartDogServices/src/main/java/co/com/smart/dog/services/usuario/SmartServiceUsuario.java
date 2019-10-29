package co.com.smart.dog.services.usuario;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import co.com.smart.dog.infraestructure.dto.UsuarioDTO;
import co.com.smart.dog.services.annotations.SmartSecurity;
import co.com.smart.dog.services.base.SmartResponseBase;
import co.com.smart.dog.session.DelegateContextEJB;

@Path("/SmartServiceUsuario")
public class SmartServiceUsuario extends SmartResponseBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Servicio que permite grabar usuario
	 * @param json
	 * @return
	 */	
	@POST
	@Path("/solicitarRegistroUsuario")
	@Produces(MediaType.APPLICATION_JSON)
	@SmartSecurity
	public Response solicitarRegistroUsuario (UsuarioDTO json){
		return getResponse(DelegateContextEJB.getUsuarioBean().solicitarRegistroUsuario(json));
	}
	
	/**
	 * Servicio que permite consultar usuario
	 * @param json
	 * @return
	 */	
	@POST
	@Path("/consultarUsuario")
	@Produces(MediaType.APPLICATION_JSON)
	@SmartSecurity
	public Response consultarUsuario (UsuarioDTO json){
		return getResponse(DelegateContextEJB.getUsuarioBean().consultarUsuario(json));
	}
	/**
	 * Servicio que permite grabar usuario
	 * @param json
	 * @return
	 */	
	@POST
	@Path("/grabarUsuario")
	@Produces(MediaType.APPLICATION_JSON)
	@SmartSecurity
	public Response grabarUsuario (UsuarioDTO json){
		return getResponse(DelegateContextEJB.getUsuarioBean().grabarUsuario(json));
	}
}
