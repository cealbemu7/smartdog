package co.com.smart.dog.infraestructure.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PaisDTO extends SmartBaseDTO  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal scpais;
	private String copais;
	private String dspais;
	private String cousuario;
	private Date fhingreso;
	private Date fhmodificacion;
	private Date fhretiro;
	
	public BigDecimal getScpais() {
		return scpais;
	}
	public void setScpais(BigDecimal scpais) {
		this.scpais = scpais;
	}
	public String getCopais() {
		return copais;
	}
	public void setCopais(String copais) {
		this.copais = copais;
	}
	public String getDspais() {
		return dspais;
	}
	public void setDspais(String dspais) {
		this.dspais = dspais;
	}
	public String getCousuario() {
		return cousuario;
	}
	public void setCousuario(String cousuario) {
		this.cousuario = cousuario;
	}
	public Date getFhingreso() {
		return fhingreso;
	}
	public void setFhingreso(Date fhingreso) {
		this.fhingreso = fhingreso;
	}
	public Date getFhmodificacion() {
		return fhmodificacion;
	}
	public void setFhmodificacion(Date fhmodificacion) {
		this.fhmodificacion = fhmodificacion;
	}
	public Date getFhretiro() {
		return fhretiro;
	}
	public void setFhretiro(Date fhretiro) {
		this.fhretiro = fhretiro;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
