package co.com.smart.dog.persistence.infraestructure.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class DatosMaestroDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal scdatmaestro;
	private String codatmaestro;
	private String dsdatmaestro;
	private String dsvalor;
	private MaestroDTO maestro;
	private Date fhingreso;
	private Date fhmodificacion;
	private Date fhretiro;
	private String cousuario;
	
	public BigDecimal getScdatmaestro() {
		return scdatmaestro;
	}
	public void setScdatmaestro(BigDecimal scdatmaestro) {
		this.scdatmaestro = scdatmaestro;
	}
	public String getCodatmaestro() {
		return codatmaestro;
	}
	public void setCodatmaestro(String codatmaestro) {
		this.codatmaestro = codatmaestro;
	}
	public String getDsdatmaestro() {
		return dsdatmaestro;
	}
	public void setDsdatmaestro(String dsdatmaestro) {
		this.dsdatmaestro = dsdatmaestro;
	}
	public String getDsvalor() {
		return dsvalor;
	}
	public void setDsvalor(String dsvalor) {
		this.dsvalor = dsvalor;
	}
	public MaestroDTO getMaestro() {
		return maestro;
	}
	public void setMaestro(MaestroDTO maestro) {
		this.maestro = maestro;
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