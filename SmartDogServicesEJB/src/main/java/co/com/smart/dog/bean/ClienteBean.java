package co.com.smart.dog.bean;

import javax.ejb.Stateless;

import co.com.smart.dog.infraestructure.dto.ClienteDTO;
import co.com.smart.dog.infraestructure.dto.SmartExcepcionSerializada;
import co.com.smart.dog.persistence.ClienteFacadeLocal;
import co.com.smart.dog.session.DelegateContextEJB;

@Stateless(name = "ClienteBean",
		   mappedName = "ejb/ClienteBean")
public class ClienteBean extends AbstractBean implements ClienteBeanLocal {
	
	private ClienteFacadeLocal facade;
	
	
	
	
	/**
	 * Default constructor
	 */
	public ClienteBean(){
		facade = DelegateContextEJB.getClienteFacade();	
	}
	/**
	 * Metodo utilizado para grabar cliente
	 */
	@Override
	public ClienteDTO GrabarCliente(ClienteDTO cliente) throws SmartExcepcionSerializada {
		ClienteDTO clientes = new ClienteDTO();
		try {
			clientes = facade.GrabarCliente(cliente);
		} catch(Throwable ex){
			ex.printStackTrace(System.err);
			SmartExcepcionSerializada smartException = new SmartExcepcionSerializada();
			smartException.setCode(0);
			smartException.setMensaje(ex.getMessage());
			smartException.setStackTrace(ex.getStackTrace());
			throw smartException;
		}
		return clientes;
	}

}
