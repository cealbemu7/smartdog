package co.com.smart.dog.infraestructure.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AsesorDTO extends SmartBaseDTO  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BigDecimal scasesor;
	
	private BigDecimal sctipoidentificacion;
	private String dsidentificacion;
	

	private Date fhingreso;
	private Date fhmodificacion;
	private Date fhretiro;
	
	private String dspnombre;
	private String dssnombre;
	
	private String dspapellido;
	private String dssapellido;
	
	private String dscelular;
	private String dstelefono;
	
	private String fhnacimineto;
	private String dsemail;
	
	private String  cousuario;
	private String dsdireccion;
	private BigDecimal scsexo;
	private CiudadDTO ciudad;
	private BigDecimal scempresa;

	
	
	public String getDspnombre() {
		return dspnombre;
	}
	public void setDspnombre(String dspnombre) {
		this.dspnombre = dspnombre;
	}
	public BigDecimal getScasesor() {
		return scasesor;
	}
	public void setScasesor(BigDecimal scacesor) {
		this.scasesor = scacesor;
	}
	public BigDecimal getSctipoidentificacion() {
		return sctipoidentificacion;
	}
	public void setSctipoidentificacion(BigDecimal sctipoidentificacion) {
		this.sctipoidentificacion = sctipoidentificacion;
	}
	public String getDsidentificacion() {
		return dsidentificacion;
	}
	public void setDsidentificacion(String dsidentificacion) {
		this.dsidentificacion = dsidentificacion;
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
	public String getDssnombre() {
		return dssnombre;
	}
	public void setDssnombre(String dssnombre) {
		this.dssnombre = dssnombre;
	}
	public String getDspapellido() {
		return dspapellido;
	}
	public void setDspapellido(String dspapellido) {
		this.dspapellido = dspapellido;
	}
	public String getDssapellido() {
		return dssapellido;
	}
	public void setDssapellido(String dssapellido) {
		this.dssapellido = dssapellido;
	}
	public String getDstelefono() {
		return dstelefono;
	}
	public void setDstelefono(String dstelefono) {
		this.dstelefono = dstelefono;
	}
	public String getDscelular() {
		return dscelular;
	}
	public void setDscelular(String dscelular) {
		this.dscelular = dscelular;
	}
	public String getDsemail() {
		return dsemail;
	}
	public void setDsemail(String dsemail) {
		this.dsemail = dsemail;
	}
	public String getFhnacimineto() {
		return fhnacimineto;
	}
	public void setFhnacimineto(String fhnacimineto) {
		this.fhnacimineto = fhnacimineto;
	}
	public BigDecimal getScsexo() {
		return scsexo;
	}
	public void setScsexo(BigDecimal scsexo) {
		this.scsexo = scsexo;
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
