package co.com.smart.dog.bean;

import java.util.List;

import co.com.smart.dog.infraestructure.dto.EmpresaDTO;
import co.com.smart.dog.infraestructure.dto.SmartExcepcionSerializada;

public interface EmpresaBeanLocal {

	public List<EmpresaDTO> consultarEmpresa(EmpresaDTO empresa) throws SmartExcepcionSerializada;

	public EmpresaDTO grabarEmpresa(EmpresaDTO empresa) throws SmartExcepcionSerializada;

}
