package co.com.smart.dog.services.cargainicial;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import co.com.smart.dog.infraestructure.dto.ObjectListaDTO;
import co.com.smart.dog.services.annotations.SmartSecurity;
import co.com.smart.dog.services.base.SmartResponseBase;
import co.com.smart.dog.session.DelegateContextEJB;

@Path("/SmartServiceCargaInicial")
public class SmartServiceCargaInicial extends SmartResponseBase{
	/**
	 * Servicio utilizado para la carga de combobox de los datos maestros
	 */
	private static final long serialVersionUID = 1L;

	@POST
	@Path("/cargarListaInicial")
	@Produces(MediaType.APPLICATION_JSON)
	@SmartSecurity
	public Response cargarListaInicial(ObjectListaDTO json){
		ObjectListaDTO returnListas = new ObjectListaDTO();
		if(json.getTipodocumento()!=null){
			returnListas.setListatipodocumento(DelegateContextEJB.getDatosMaestroBean().consultarDatosMestros(json.getTipodocumento()));
		}
		if(json.getSexo()!=null){	
			returnListas.setListasexo(DelegateContextEJB.getDatosMaestroBean().consultarDatosMestros(json.getSexo()));
		}		
		return getResponse(returnListas);
	}

}
