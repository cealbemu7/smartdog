package co.com.smart.dog.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import co.com.smart.dog.infraestructure.dto.EmpresaDTO;
import co.com.smart.dog.infraestructure.dto.EntidadDTO;
import co.com.smart.dog.infraestructure.dto.LoginDTO;
import co.com.smart.dog.infraestructure.dto.MenuDTO;
import co.com.smart.dog.infraestructure.dto.SmartExcepcionSerializada;
import co.com.smart.dog.persistence.caller.LoginCaller;
import co.com.smart.dog.utility.SmartConstant;

@Stateless(name = "LoginFacade",
mappedName = "ejb/LoginFacade")
public class LoginFacade implements LoginFacadeLocal {
	
	private static final Logger logger = Logger.getLogger(SmartConstant.LOGGER_NAME);
	
	@PersistenceContext(unitName = "SmartDogPU")
	private EntityManager em;
	protected EntityManager getEntityManager() {
		return em;
	}
	
	@Override
	public boolean getUserValidate(LoginDTO login) throws SmartExcepcionSerializada {
		try {
			LoginCaller caller = new LoginCaller(SmartConstant.JDNI_EAGLE_CONNECTION);
			return caller.getUserValidate(login);
		} catch (SQLException | NamingException e) {
			logger.error(e.getMessage(),e);
			throw new SmartExcepcionSerializada(e.getMessage());
		}
	}
	
	@Override
	public List<MenuDTO> getResources(LoginDTO login)throws SmartExcepcionSerializada {
		List<MenuDTO> returnResources = new ArrayList<MenuDTO> (); 
		try {
			LoginCaller caller = new LoginCaller(SmartConstant.JDNI_EAGLE_CONNECTION);
			returnResources = caller.getResource(login);
		} catch (SQLException | NamingException e) {
			logger.error(e.getMessage(),e);
			throw new SmartExcepcionSerializada(e.getMessage());
		}
		return returnResources;
	}

	@Override
	public List<EntidadDTO> getEntitiesUser(LoginDTO login)
			throws SmartExcepcionSerializada {
		List<EntidadDTO> returnEntities = new ArrayList<EntidadDTO> (); 
		try {
			LoginCaller caller = new LoginCaller(SmartConstant.JDNI_EAGLE_CONNECTION);
			returnEntities = caller.getEntitiesUser(login);
		} catch (SQLException | NamingException e) {
			logger.error(e.getMessage(),e);
			throw new SmartExcepcionSerializada(e.getMessage());
		}
		return returnEntities;
	}

	@Override
	public EmpresaDTO getEmpresaEntidad(EntidadDTO entidad)throws SmartExcepcionSerializada {
		EmpresaDTO returnEmpresa = new EmpresaDTO (); 
		try {
			LoginCaller caller = new LoginCaller(SmartConstant.JDNI_CONNECTION);
			returnEmpresa = caller.getEmpresaEntidad(entidad);
		} catch (SQLException | NamingException e) {
			logger.error(e.getMessage(),e);
			throw new SmartExcepcionSerializada(e.getMessage());
		}
		return returnEmpresa;
	}
	
}
