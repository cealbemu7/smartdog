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
public class EmpresaBean extends AbstractBean implements EmpresaBeanLocal {
	private EmpresaFacadeLocal facade;
	
	/**
	 * Default constructor
	 */
	public EmpresaBean(){
		facade = DelegateContextEJB.getEmpresaFacade();
	
		}

	@Override
	public EmpresaDTO grabarEmpresa(EmpresaDTO empresa) throws SmartExcepcionSerializada {
		EmpresaDTO returnEmpresa = new EmpresaDTO();
		try {
			returnEmpresa = facade.grabarEmpresa(empresa);
		} catch (Throwable ex) {
			ex.printStackTrace(System.err);
			SmartExcepcionSerializada smartException = new SmartExcepcionSerializada();
			smartException.setCode(0);
			smartException.setMensaje(ex.getMessage());
			smartException.setStackTrace(ex.getStackTrace());
			throw smartException;
		}
		return returnEmpresa;
	}

	@Override
	public List<EmpresaDTO> consultarEmpresa(EmpresaDTO filtro) throws SmartExcepcionSerializada {
		List<EmpresaDTO> findempresa = new ArrayList<>();
		try {
			findempresa = facade.consultarEmpresa(filtro);
		} catch (Throwable ex) {
			ex.printStackTrace(System.err);
			SmartExcepcionSerializada smartException = new SmartExcepcionSerializada();
			smartException.setCode(0);
			smartException.setMensaje(ex.getMessage());
			smartException.setStackTrace(ex.getStackTrace());
			throw smartException;
		}
		return findempresa;
	}

}
