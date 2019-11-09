package co.com.smart.dog.persistence;

import co.com.smart.dog.infraestructure.dto.ClienteDTO;

public interface ClienteFacadeLocal {

	public ClienteDTO GrabarCliente(ClienteDTO cliente ) throws Throwable;
	
}
