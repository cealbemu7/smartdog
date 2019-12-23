package co.com.smart.dog.infraestructure.dto;

import java.io.Serializable;

/**
 * 
 * @author SmartJugle S.A.S
 *
 */
public class LoginDTO extends SmartBaseDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String user;
	private String password;
	private String confirmPassword;
	private String application;
	private EntidadDTO entity;
	private String roles;
	private EmpresaDTO empresa;
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getApplication() {
		return application;
	}
	public void setApplication(String application) {
		this.application = application;
	}
	public EmpresaDTO getEmpresa() {
		return empresa;
	}
	public void setEmpresa(EmpresaDTO empresa) {
		this.empresa = empresa;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public EntidadDTO getEntity() {
		return entity;
	}
	public void setEntity(EntidadDTO entity) {
		this.entity = entity;
	}
}
