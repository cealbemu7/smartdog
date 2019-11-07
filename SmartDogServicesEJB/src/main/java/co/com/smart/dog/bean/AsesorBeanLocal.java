package co.com.smart.dog.bean;

import co.com.smart.dog.infraestructure.dto.AsesorDTO;
import co.com.smart.dog.infraestructure.dto.SmartExcepcionSerializada;

public interface AsesorBeanLocal {

	public AsesorDTO grabarAsesor(AsesorDTO Asesor) throws SmartExcepcionSerializada;

}
