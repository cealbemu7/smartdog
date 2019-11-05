package co.com.smart.dog.persistence;

import java.util.List;

import co.com.smart.dog.infraestructure.dto.PropiedadDTO;

public interface PropiedadFacadeLocal {

	public PropiedadDTO grabarPropiedad(PropiedadDTO propiedad) throws Throwable;

	public PropiedadDTO actulizarPropiedad(PropiedadDTO propiedad) throws Throwable;

	public PropiedadDTO eliminarPropiedad(PropiedadDTO propiedad) throws Throwable;

	public List<PropiedadDTO> consultarPropiedad(PropiedadDTO propiedad) throws Throwable;



}
