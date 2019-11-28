package co.com.smart.dog.bean;

import co.com.smart.dog.infraestructure.dto.ClienteDTO;
import co.com.smart.dog.infraestructure.dto.SmartExcepcionSerializada;

public interface ClienteBeanLocal {
	
	/**
	 * Metodo utilizado para grabar cliente
	 * @param cliente
	 * @return
	 * @throws SmartExcepcionSerializada
	 */
	public ClienteDTO GrabarCliente(ClienteDTO cliente ) throws SmartExcepcionSerializada;
	
	/**
	 * Metodo utilizado para consultar cliente
	 * @param cliente
	 * @return
	 * @throws SmartExcepcionSerializada
	 */
	public ClienteDTO ConsultarCliente (ClienteDTO cliente) throws SmartExcepcionSerializada;
}
