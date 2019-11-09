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
	
	private DatosMaestroDTO tipoidentificacion;
	public DatosMaestroDTO getTipoidentificacion() {
		return tipoidentificacion;
	}
	public void setTipoidentificacion(DatosMaestroDTO tipoidentificacion) {
		this.tipoidentificacion = tipoidentificacion;
	}
	public DatosMaestroDTO getSexo() {
		return sexo;
	}
	public void setSexo(DatosMaestroDTO sexo) {
		this.sexo = sexo;
	}
	private String coidentificacion;
	

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
	private DatosMaestroDTO sexo;
	private CiudadDTO ciudad;
	private EmpresaDTO empresa;

	
	
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

	public String getCoidentificacion() {
		return coidentificacion;
	}
	public void setCoidentificacion(String dsidentificacion) {
		this.coidentificacion = dsidentificacion;
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

	
	public EmpresaDTO getEmpresa() {
		return empresa;
	}
	public void setEmpresa(EmpresaDTO empresa) {
		this.empresa = empresa;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

	
	

	
	
	
	

}
