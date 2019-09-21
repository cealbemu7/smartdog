package co.com.smart.dog.bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import co.com.smart.dog.infraestructure.dto.MaestroDTO;
import co.com.smart.dog.infraestructure.dto.SmartExcepcionSerializada;
import co.com.smart.dog.persistence.MaestroFacadeLocal;
import co.com.smart.dog.session.DelegateContextEJB;

/**
*Session Bean implementation class MaestroBean
*/
@Stateless(name = "MaestroBean",
mappedName = "ejb/MaestroBean")
public class MaestroBean extends AbstractBean implements MaestroBeanLocal{
private MaestroFacadeLocal facade;
	
    /**
     * Default constructor. 
     */
    public MaestroBean() {
    	facade = DelegateContextEJB.getMaestroFacade();
    }

	@Override
	public List<MaestroDTO> listarMaestros(MaestroDTO filtro)
			throws SmartExcepcionSerializada {
		List<MaestroDTO> listmaestro = new ArrayList<>();
		try {
			System.err.println("Llego por la bean");
			listmaestro=facade.listarMaestros(filtro);
		}catch(Throwable ex){
			SmartExcepcionSerializada smartException = new SmartExcepcionSerializada();
			smartException.setCode(0);
			smartException.setMensaje("a ocurrido un error al listar maestros");
			smartException.setStackTrace(ex.getStackTrace());
			throw smartException;
		}
		return listmaestro;
	}

}
