package co.com.smart.dog.bean;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import co.com.smart.dog.infraestructure.dto.EmpresaDTO;
import co.com.smart.dog.infraestructure.dto.EntidadDTO;
import co.com.smart.dog.infraestructure.dto.LoginDTO;
import co.com.smart.dog.infraestructure.dto.MenuDTO;
import co.com.smart.dog.infraestructure.dto.SmartExcepcionSerializada;
import co.com.smart.dog.persistence.LoginFacadeLocal;
import co.com.smart.dog.session.DelegateContextEJB;
import co.com.smart.dog.session.base.SmartContextLookUp;
import co.com.smart.dog.utility.SmartConstant;
import co.com.smart.dog.utility.SmartUtilities;

@Stateless(name = "LoginBean",
mappedName = "ejb/LoginBean")
public class LoginBean extends SmartContextLookUp implements LoginBeanLocal {
	
	private LoginFacadeLocal facade;
	/**
	 * Default constructor
	 */
	public LoginBean(){
		facade = DelegateContextEJB.getLoginFacade();
	}
	@Override
	public List<MenuDTO> getResources(LoginDTO login)throws SmartExcepcionSerializada {
		return facade.getResources(login);
	}
	@Override
	public List<EntidadDTO> getEntitiesUser(LoginDTO login)throws SmartExcepcionSerializada {
		return facade.getEntitiesUser(login);
	}
	@Override
	public EmpresaDTO getEmpresaEntidad(EntidadDTO entidad)throws SmartExcepcionSerializada {
		return facade.getEmpresaEntidad(entidad);
	}
	
	@Override
	public LoginDTO generateSecureToken(LoginDTO json)throws SmartExcepcionSerializada {
		
		if(facade.getUserValidate(json)){
			long time = System.currentTimeMillis();
			long timeExpiration = Long.parseLong(SmartUtilities.getProperty(SmartConstant.EXPIRATION));
			String responseToken = Jwts.builder()
									   .signWith(SignatureAlgorithm.HS256, SmartUtilities.decodeToString(SmartUtilities.getProperty(SmartConstant.CONF_KEY),true))
									   .setSubject(SmartUtilities.decodeToString(SmartUtilities.getProperty(SmartConstant.SUBJECT),true))
									   .setIssuedAt(new Date(time))
									   .setExpiration(new Date(time+timeExpiration))
									   .claim("user-name", json.getUser())
									   .claim("user-application", json.getApplication())
									   .claim("user-password", json.getPassword())
									   .compact();
			LoginDTO response = new LoginDTO();
			response.setSecureToken(responseToken);
			response.setApplication(json.getApplication());
			response.setUser(json.getUser());
			return response;
		}
		return new LoginDTO();
	}

}
