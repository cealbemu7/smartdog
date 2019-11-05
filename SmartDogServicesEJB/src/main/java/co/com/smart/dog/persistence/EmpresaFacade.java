package co.com.smart.dog.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.com.smart.dog.bean.AbstractBean;
import co.com.smart.dog.infraestructure.dto.EmpresaDTO;
import co.com.smart.dog.persistence.caller.EmpresaCaller;
import co.com.smart.dog.utility.SmartConstant;


@Stateless(name = "EmpresaFacade", mappedName = "ejb/EmpresaFacade")
public class EmpresaFacade extends AbstractBean implements EmpresaFacadeLocal {
	@PersistenceContext(unitName = "SmartDogPU")
	private EntityManager em;

	protected EntityManager getEntityManager() {
		return em;
	}

	@Override
	public List<EmpresaDTO> consultarEmpresa(EmpresaDTO empresa) throws Throwable {
		List<EmpresaDTO> empresalist = new ArrayList<>();
		try {
			EmpresaCaller caller = new EmpresaCaller(SmartConstant.JDNI_CONNECTION);
			empresalist = caller.consultarEmpresa(empresa);

		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return empresalist;
	}

	/**
	 * Metodo Eliminar empresa
	 * 
	 * 
	 */
	@Override
	public EmpresaDTO eliminarEmpresa(EmpresaDTO empresa) throws Throwable {
		EmpresaDTO returnObject = new EmpresaDTO();
		try {
			EmpresaCaller caller = new EmpresaCaller(SmartConstant.JDNI_CONNECTION);
			returnObject = caller.eliminarEmpresa(empresa);

		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnObject;
	}

	/**
	 * Metodo grabar empresa
	 * 
	 * 
	 */
	@Override
	public EmpresaDTO grabarEmpresa(EmpresaDTO empresa) throws Throwable {

		EmpresaDTO findempresa = new EmpresaDTO();
		try {
			EmpresaCaller caller = new EmpresaCaller(SmartConstant.JDNI_CONNECTION);
			findempresa = caller.grabarEmpresa(empresa);

		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return findempresa;
	}
}
