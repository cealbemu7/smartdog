package co.com.smart.dog.persistence;

import java.util.List;

import javax.ejb.Local;

import co.com.smart.dog.infraestructure.dto.EmpresaDTO;
import co.com.smart.dog.infraestructure.dto.EntidadDTO;
import co.com.smart.dog.infraestructure.dto.LoginDTO;
import co.com.smart.dog.infraestructure.dto.MenuDTO;
import co.com.smart.dog.infraestructure.dto.SmartExcepcionSerializada;

@Local
public interface LoginFacadeLocal {
	
	/**
	 * Método Útilizado para validar la existencia del usuario
	 * @param login
	 * @return
	 * @throws SmartExcepcionSerializada
	 */
	public boolean getUserValidate(LoginDTO login)throws SmartExcepcionSerializada;
	
	
	/**
	 * Método Útilizado para obtener los recursos del usuario en sesion
	 * @param login
	 * @return
	 * @throws SmartExcepcionSerializada
	 */
	public List<MenuDTO> getResources(LoginDTO login)throws SmartExcepcionSerializada;
	
	/**
	 * Método Útilizado para consultar las entidades del usuario en session
	 * @param getEntitiesUser
	 * @return
	 * @throws SmartExcepcionSerializada
	 */
	public  List<EntidadDTO> getEntitiesUser(LoginDTO login)throws SmartExcepcionSerializada;
	
	/**
	 * Método Útilizado para consultar la empresa por entidad
	 * @param entidad
	 * @return
	 * @throws SmartExcepcionSerializada
	 */
	public  EmpresaDTO getEmpresaEntidad(EntidadDTO entidad)throws SmartExcepcionSerializada;

}
