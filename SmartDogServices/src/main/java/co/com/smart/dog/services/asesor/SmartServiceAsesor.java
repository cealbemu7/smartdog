package co.com.smart.dog.services.asesor;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import co.com.smart.dog.infraestructure.dto.AsesorDTO;
import co.com.smart.dog.infraestructure.dto.SmartExcepcionSerializada;
import co.com.smart.dog.services.base.SmartResponseBase;
import co.com.smart.dog.session.DelegateContextEJB;

@Path("/SmartServiceAsesor")
public class SmartServiceAsesor extends SmartResponseBase{


/**
 * 
 */
private static final long serialVersionUID = 1L;
/**
 * Servicio que permite consultar Asesor
 * @param json
 * @return
 * @throws Throwable 
 * @throws SmartExcepcionSerializada 
 */	
@POST
@Path("/consultarAsesor")
@Produces(MediaType.APPLICATION_JSON)
public Response consultarAsesor (AsesorDTO json){
	return getResponse(DelegateContextEJB.getAsesorBean().consultarAsesor(json));
}

/**
 * Servicio que permite grabar Asesor
 * @param json
 * @return
 */	
@POST
@Path("/grabarAsesor")
@Produces(MediaType.APPLICATION_JSON)
public Response grabarAsesor(AsesorDTO json){
	return getResponse(DelegateContextEJB.getAsesorBean().grabarAsesor(json));
	
}


/**
 * Servicio que permite eliminar Asesor
 * @param json
 * @return
 */	
@POST
@Path("/eliminarAsesor")
@Produces(MediaType.APPLICATION_JSON)
public Response eliminarAsesor(AsesorDTO json){
	return getResponse(DelegateContextEJB.getAsesorBean().eliminarAsesor(json));
	
}

}
