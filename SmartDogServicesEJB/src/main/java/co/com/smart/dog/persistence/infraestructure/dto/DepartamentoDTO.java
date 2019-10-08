package co.com.smart.dog.persistence.infraestructure.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class DepartamentoDTO extends SmartBaseDTO  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal scdepartamento;
	private String codepartamento;
	private String dsdepartamento;
	private PaisDTO pais;
	private String cousuario;
	private Date fhingreso;
	private Date fhmodificacion;
	private Date fhretiro;
	
	public BigDecimal getScdepartamento() {
		return scdepartamento;
	}
	public void setScdepartamento(BigDecimal scdepartamento) {
		this.scdepartamento = scdepartamento;
	}
	public String getCodepartamento() {
		return codepartamento;
	}
	public void setCodepartamento(String codepartamento) {
		this.codepartamento = codepartamento;
	}
	public String getDsdepartamento() {
		return dsdepartamento;
	}
	public void setDsdepartamento(String dsdepartamento) {
		this.dsdepartamento = dsdepartamento;
	}
	public PaisDTO getPais() {
		return pais;
	}
	public void setPais(PaisDTO pais) {
		this.pais = pais;
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
