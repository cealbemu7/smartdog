package co.com.smart.dog.bean;

import java.util.List;

import co.com.smart.dog.infraestructure.dto.PropiedadDTO;
import co.com.smart.dog.infraestructure.dto.SmartExcepcionSerializada;

public interface PropiedadBeanLocal {
	
	public List<PropiedadDTO> consultarPropiedad(PropiedadDTO propiedad) throws  SmartExcepcionSerializada;

	public PropiedadDTO grabarPropiedad(PropiedadDTO propiedad) throws SmartExcepcionSerializada;
	
	public PropiedadDTO actulizarPropiedad(PropiedadDTO propiedad) throws SmartExcepcionSerializada;

	
	public PropiedadDTO eliminarPropiedad(PropiedadDTO propiedad) throws SmartExcepcionSerializada;

}
