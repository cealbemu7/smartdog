package co.com.smart.dog.persistence;

import java.util.List;

import co.com.smart.dog.infraestructure.dto.AsesorDTO;



public interface AsesorFacadeLocal {
	
	public List<AsesorDTO> consultarAsesor (AsesorDTO asesorlist ) throws Throwable;
	
	public AsesorDTO grabarAsesor(AsesorDTO asesor ) throws Throwable;
	
	public AsesorDTO eliminarAsesor(AsesorDTO asesor ) throws Throwable;
	

	

}
