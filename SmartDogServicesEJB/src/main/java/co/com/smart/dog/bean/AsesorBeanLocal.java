package co.com.smart.dog.bean;

import java.util.List;

import co.com.smart.dog.infraestructure.dto.AsesorDTO;

import co.com.smart.dog.infraestructure.dto.SmartExcepcionSerializada;

public interface AsesorBeanLocal {

	public AsesorDTO grabarAsesor(AsesorDTO Asesor) throws SmartExcepcionSerializada;

	public List<AsesorDTO>  consultarAsesor(AsesorDTO json) throws SmartExcepcionSerializada;

	public AsesorDTO  eliminarAsesor(AsesorDTO json) throws SmartExcepcionSerializada;

}
