package co.com.smart.dog.bean;

import java.util.List;

import co.com.smart.dog.infraestructure.dto.EmpresaDTO;
import co.com.smart.dog.infraestructure.dto.EntidadDTO;
import co.com.smart.dog.infraestructure.dto.LoginDTO;
import co.com.smart.dog.infraestructure.dto.MenuDTO;
import co.com.smart.dog.infraestructure.dto.SmartExcepcionSerializada;

public interface LoginBeanLocal {
	
	/**
	 * Método Útilizado para consultar los recursos del usuario en session
	 * @param login
	 * @return
	 * @throws SmartExcepcionSerializada
	 */
	public List<MenuDTO> getResources(LoginDTO login)throws SmartExcepcionSerializada;
	
	/**
	 * Método Útilizado para consultar las entidades del usuario en session
	 * @param login
	 * @return
	 * @throws SmartExcepcionSerializada
	 */
	public List<EntidadDTO> getEntitiesUser(LoginDTO login)throws SmartExcepcionSerializada;
	
	/**
	 * Método Útilizado para consultar la empresa por entidad
	 * @param entidad
	 * @return
	 * @throws SmartExcepcionSerializada
	 */
	public EmpresaDTO getEmpresaEntidad(EntidadDTO entidad)throws SmartExcepcionSerializada;

	/**
	 * Metodo encargado de autenticar y entregar token seguro de session
	 * @param json
	 * @return
	 * @throws SmartExcepcionSerializada
	 */
	public LoginDTO generateSecureToken(LoginDTO json)throws SmartExcepcionSerializada;

}
