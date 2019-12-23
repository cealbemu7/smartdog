package co.com.smart.dog.infraestructure.dto;

import java.io.Serializable;

public class EntidadDTO extends SmartBaseDTO implements Serializable{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long entity;
	private String dsentity;
	private String dsnit;
	private String verify;
	private String nmmatriculamercantil;
	
	public long getEntity() {
		return entity;
	}
	public void setEntity(long entity) {
		this.entity = entity;
	}
	public String getDsentity() {
		return dsentity;
	}
	public void setDsentity(String dsentity) {
		this.dsentity = dsentity;
	}
	public String getDsnit() {
		return dsnit;
	}
	public void setDsnit(String dsnit) {
		this.dsnit = dsnit;
	}
	public String getVerify() {
		return verify;
	}
	public void setVerify(String verify) {
		this.verify = verify;
	}
	public String getNmMatriculaMercantil() {
		return nmmatriculamercantil;
	}
	public void setNmMatriculaMercantil(String nmmatriculamercantil) {
		this.nmmatriculamercantil = nmmatriculamercantil;
	}
	
}
