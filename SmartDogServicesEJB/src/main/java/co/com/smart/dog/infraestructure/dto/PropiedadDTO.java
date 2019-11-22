package co.com.smart.dog.infraestructure.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;




public class PropiedadDTO extends SmartBaseDTO implements Serializable {
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	private BigDecimal scpropiedad;
	private String copropiedad;
	private String dspropiedad;
	private String dsdireccion;
	private Date fhingreso;
	private Date fhmodificacion;
	private Date fhretiro;
	private String cousuario;
	private CiudadDTO ciudad;
	
	
	
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
	public String getDsdireccion() {
		return dsdireccion;
	}
	public void setDsdireccion(String dsdireccion) {
		this.dsdireccion = dsdireccion;
	}
	public CiudadDTO getCiudad() {
		return ciudad;
	}
	public void setCiudad(CiudadDTO ciudad) {
		this.ciudad = ciudad;
	}
	
	public BigDecimal getScpropiedad() {
		return scpropiedad;
	}
	public void setScpropiedad(BigDecimal scpropiedad) {
		this.scpropiedad = scpropiedad;
	}
	public String getCopropiedad() {
		return copropiedad;
	}
	public void setCopropiedad(String copropiedad) {
		this.copropiedad = copropiedad;
	}
	public String getDspropiedad() {
		return dspropiedad;
	}
	public void setDspropiedad(String dspropiedad) {
		this.dspropiedad = dspropiedad;
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
