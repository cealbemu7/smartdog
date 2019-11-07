package co.com.smart.dog.persistence;

import co.com.smart.dog.infraestructure.dto.ClienteDTO;

public interface ClienteFacadeLocal {
	
	/**
	 * Metodo utilizado para grabar clientes 
	 * @param empresa
	 * @return
	 * @throws Throwable
	 */
	public ClienteDTO GrabarCliente(ClienteDTO cliente ) throws Throwable;
}
