package co.com.smart.dog.infraestructure.dto;

import java.io.Serializable;

public class MenuDTO extends MensajeSQLDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long idemenu;
	private String description;
	private String uri;
	private long idemenufather;
	private String contextPath;
	private String application;
	
	public long getIdemenu() {
		return idemenu;
	}
	public void setIdemenu(long idemenu) {
		this.idemenu = idemenu;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public long getIdemenufather() {
		return idemenufather;
	}
	public void setIdemenufather(long idemenufather) {
		this.idemenufather = idemenufather;
	}
	public String getContextPath() {
		return contextPath;
	}
	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}
	public String getApplication() {
		return application;
	}
	public void setApplication(String application) {
		this.application = application;
	}
	
}
