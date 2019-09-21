package co.com.smart.dog.persistence;

import java.util.List;

import co.com.smart.dog.infraestructure.dto.EmpresaDTO;

public interface EmpresaFacadeLocal {
	
	public List<EmpresaDTO> consultarEmpresa (EmpresaDTO empresa ) throws Throwable;
	public EmpresaDTO grabarEmpresa(EmpresaDTO empresa ) throws Throwable;
	
}
