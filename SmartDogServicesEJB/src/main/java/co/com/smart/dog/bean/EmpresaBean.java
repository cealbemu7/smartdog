package co.com.smart.dog.bean;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import co.com.smart.dog.infraestructure.dto.EmpresaDTO;
import co.com.smart.dog.infraestructure.dto.SmartExcepcionSerializada;
import co.com.smart.dog.persistence.EmpresaFacadeLocal;
import co.com.smart.dog.session.DelegateContextEJB;
@Stateless(name = "EmpresaBean",
mappedName = "ejb/EmpresaBean")
public class EmpresaBean implements EmpresaBeanLocal {
private EmpresaFacadeLocal facade;
	
	/**
	 * Default constructor
	 */
	public EmpresaBean(){
		facade = DelegateContextEJB.getEmpresaFacade();
	
		}
	@Override
	public List<EmpresaDTO> consultarEmpresa(EmpresaDTO empresa) throws SmartExcepcionSerializada {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmpresaDTO grabarEmpresa(EmpresaDTO empresa) throws SmartExcepcionSerializada {
		// TODO Auto-generated method stub
		return null;
	}

}
