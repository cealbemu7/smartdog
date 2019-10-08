package co.com.smart.dog.infraestructure.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MaestroDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal scmaestro;
	private String comaestro;
	private String dsmaestro;
	private Date fhretiro;
	private Date fhingreso;
	private Date fhmodificacion;
	private String cousuario;
	
	public BigDecimal getScmaestro() {
		return scmaestro;
	}
	public void setScmaestro(BigDecimal scmaestro) {
		this.scmaestro = scmaestro;
	}
	public String getComaestro() {
		return comaestro;
	}
	public void setComaestro(String comaestro) {
		this.comaestro = comaestro;
	}
	public String getDsmaestro() {
		return dsmaestro;
	}
	public void setDsmaestro(String dsmaestro) {
		this.dsmaestro = dsmaestro;
	}
	public Date getFhretiro() {
		return fhretiro;
	}
	public void setFhretiro(Date fhretiro) {
		this.fhretiro = fhretiro;
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
	public String getCousuario() {
		return cousuario;
	}
	public void setCousuario(String cousuario) {
		this.cousuario = cousuario;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
