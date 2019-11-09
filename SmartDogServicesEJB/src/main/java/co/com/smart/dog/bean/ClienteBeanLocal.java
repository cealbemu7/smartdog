package co.com.smart.dog.bean;

import co.com.smart.dog.infraestructure.dto.ClienteDTO;
import co.com.smart.dog.infraestructure.dto.SmartExcepcionSerializada;

public interface ClienteBeanLocal {

	public ClienteDTO GrabarCliente(ClienteDTO cliente ) throws SmartExcepcionSerializada;
	
}
