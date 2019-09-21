package co.com.smart.dog.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import co.com.smart.dog.infraestructure.dto.EmpresaDTO;
import co.com.smart.dog.persistence.caller.EmpresaCaller;



public class EmpresaFacade implements EmpresaFacadeLocal {

	@Override
	public List<EmpresaDTO> consultarEmpresa(EmpresaDTO empresa) throws Throwable {
		List<EmpresaDTO> empresacon = new ArrayList<>();
		try {
			EmpresaCaller caller = new EmpresaCaller ();
			empresacon = caller.consultarEmpresa(empresa);
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public EmpresaDTO grabarEmpresa(EmpresaDTO empresa) throws Throwable {
		EmpresaDTO empresacon = new EmpresaDTO();
		 try {
				EmpresaCaller caller = new EmpresaCaller();
				empresacon = caller.grabarEmpresa(empresa);
				
			} catch (NamingException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return empresacon;
	}
}
