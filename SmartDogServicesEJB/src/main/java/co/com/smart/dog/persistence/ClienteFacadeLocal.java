package co.com.smart.dog.persistence;

import co.com.smart.dog.infraestructure.dto.ClienteDTO;

public interface ClienteFacadeLocal {
	/**
	 * Metodo Utilizado para grabar clientes
	 * @param cliente
	 * @return
	 * @throws Throwable
	 */
	public ClienteDTO GrabarCliente(ClienteDTO cliente ) throws Throwable;
	
	/**
	 * Metodo utilizado para consultar clientes
	 * @param cliente
	 * @return
	 * @throws Throwable
	 */
	public ClienteDTO ConsultarCliente(ClienteDTO cliente)throws Throwable;
	
}
