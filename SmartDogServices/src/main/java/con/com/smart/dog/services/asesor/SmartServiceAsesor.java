package con.com.smart.dog.services.asesor;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import co.com.smart.dog.infraestructure.dto.AsesorDTO;
import co.com.smart.dog.services.annotations.SmartSecurity;
import co.com.smart.dog.services.base.SmartResponseBase;
import co.com.smart.dog.session.DelegateContextEJB;

@Path("/SmartServiceAsesor")
public class SmartServiceAsesor  extends SmartResponseBase {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @param json
	 * @return 
	 */

	@POST
	@Path("/grabarAsesor")
	@Produces(MediaType.APPLICATION_JSON)
	@SmartSecurity
	public Response grabarAsesor (AsesorDTO json){
		return getResponse(DelegateContextEJB.getAsesorBean().grabarAsesor(json));
	}

	
}

