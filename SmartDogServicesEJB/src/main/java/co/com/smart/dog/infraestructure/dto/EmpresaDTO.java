package co.com.smart.dog.infraestructure.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

public class EmpresaDTO extends SmartBaseDTO implements Serializable{

		
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	private String nitempresa;
	private String dsrazonsocial;
	private String cousuario;
	private Date fhingreso;
	private Date fhmodificacion;
	private Date fhretiro;
	private CiudadDTO ciudad;
	private String dsdireccion;
	private String dstelefono;
	private String dsemail;
	private BigDecimal scempresa;
	
	
	public String getNitempresa() {
		return nitempresa;
	}
	public void setNitempresa(String nitempresa) {
		this.nitempresa = nitempresa;
	}
	public String getDsrazonsocial() {
		return dsrazonsocial;
	}
	public void setDsrazonsocial(String dsrazonsocial) {
		this.dsrazonsocial = dsrazonsocial;
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
	public CiudadDTO getCiudad() {
		return ciudad;
	}
	public void setCiudad(CiudadDTO ciudad) {
		this.ciudad = ciudad;
	}
	public String getDsdireccion() {
		return dsdireccion;
	}
	public void setDsdireccion(String dsdireccion) {
		this.dsdireccion = dsdireccion;
	}
	public String getDstelefono() {
		return dstelefono;
	}
	public void setDstelefono(String dstelefono) {
		this.dstelefono = dstelefono;
	}
	public String getDsemail() {
		return dsemail;
	}
	public void setDsemail(String dsemail) {
		this.dsemail = dsemail;
	}
	public BigDecimal getScempresa() {
		return scempresa;
	}
	public void setScempresa(BigDecimal scempresa) {
		this.scempresa = scempresa;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

	