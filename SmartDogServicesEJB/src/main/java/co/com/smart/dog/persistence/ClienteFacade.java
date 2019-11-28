package co.com.smart.dog.persistence;

import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.com.smart.dog.bean.AbstractBean;
import co.com.smart.dog.infraestructure.dto.ClienteDTO;
import co.com.smart.dog.persistence.caller.ClienteCaller;
import co.com.smart.dog.utility.SmartConstant;

/**
 * Session Bean implementation class ClienteFacade 
 * @author deymer
 */
@Stateless(name = "ClienteFacade", 
           mappedName = "ejb/ClienteFacade")
public class ClienteFacade	extends AbstractBean  implements ClienteFacadeLocal {
	
	@PersistenceContext(unitName = "SmartDogPU")
	private EntityManager em;

	protected EntityManager getEntityManager() {
		return em;
	}
	
	/**
	 * Metodo utilizado para grabar cliente
	 */
	@Override
	public ClienteDTO GrabarCliente(ClienteDTO cliente) throws Throwable {
		ClienteDTO clientes = new ClienteDTO();
		 try {
				ClienteCaller caller = new ClienteCaller(SmartConstant.JDNI_CONNECTION);
				clientes = caller.GrabarCliente(cliente);
				
			} catch (NamingException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return clientes;
	}
	/**
	 * Metodo utilizado para consultar cliente
	 */
	@Override
	public ClienteDTO ConsultarCliente(ClienteDTO cliente) throws Throwable {
		ClienteDTO returncliente = new ClienteDTO();
		try {
			ClienteCaller caller = new ClienteCaller(SmartConstant.JDNI_CONNECTION);
			returncliente = caller.ConsultarCliente(cliente);

		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returncliente;
	}

}
