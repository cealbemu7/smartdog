package co.com.smart.dog.bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import co.com.smart.dog.infraestructure.dto.DatosMaestroDTO;
import co.com.smart.dog.infraestructure.dto.SmartExcepcionSerializada;
import co.com.smart.dog.persistence.DatosMaestroFacadeLocal;
import co.com.smart.dog.session.DelegateContextEJB;
/**
*Session Bean implementation class ActividadesFacede
 */
@Stateless(name = "DatosMaestroBean",
mappedName = "ejb/DatosMaestroBean")
public class DatosMaestroBean extends AbstractBean implements DatosMaestroBeanLocal{
	private DatosMaestroFacadeLocal facade;

	 /**
	  * Default constructor
	  */
	 public DatosMaestroBean(){
	  setFacade(DelegateContextEJB.getDatosMaestroFacade());
	 }

	public DatosMaestroFacadeLocal getFacade() {
		return facade;
	}

	public void setFacade(DatosMaestroFacadeLocal facade) {
		this.facade = facade;
	}

	@Override
	public List<DatosMaestroDTO> consultarDatosMestros(DatosMaestroDTO filtro)
			throws SmartExcepcionSerializada {
		List<DatosMaestroDTO> listdatosmaestros = new ArrayList<>();
		try {
			listdatosmaestros=facade.consultarDatosMestros(filtro);
		}catch(Throwable ex){
			SmartExcepcionSerializada smartException = new SmartExcepcionSerializada();
			smartException.setCode(0);
			smartException.setMensaje("a ocurrido un error al listar datos maestros");
			smartException.setStackTrace(ex.getStackTrace());
			throw smartException;
		}
		return listdatosmaestros;
	}

}
