package co.com.smart.dog.persistence.infraestructure.dto;

import java.io.Serializable;

/**
 * DTO base para todos los objetos transaccionales y de negocio en el contexto smart
 * @author SmartJugle S.A.S
 *
 */
public class SmartBaseDTO extends MensajeSQLDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String secureToken;
	
	public String getSecureToken() {
		return secureToken;
	}
	public void setSecureToken(String secureToken) {
		this.secureToken = secureToken;
	}
	
}
