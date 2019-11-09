package co.com.smart.dog.infraestructure.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ClienteDTO extends SmartBaseDTO implements Serializable{

	/**
	 * objeto utilizado para el manejo de terceros
	 * Fecha Creacion: 2019/10/06 
	 */
	private static final long serialVersionUID = 1L;
	
	private BigDecimal sccliente;
	private String dspnombre;
	private String dssnombre;
	private String dspapellido;
	private String dssapellido;
	private DatosMaestroDTO tipoidentificacion;
	private String dsidentificacion;
	private String dsdireccion;
	private String dstelefono;
	private String dscelular;
	private String dsemail;
	private Date fhingreso;
    private Date fhmodificacion;
    private Date fhretiro;
    private DatosMaestroDTO sexo;
    private String fhnacimiento;
    private UsuarioDTO usuario;
    private String nombrecompleto;
    
	public BigDecimal getSccliente() {
		return sccliente;
	}
	public void setSccliente(BigDecimal sccliente) {
		this.sccliente = sccliente;
	}
	public String getDspnombre() {
		return dspnombre;
	}
	public void setDspnombre(String dspnombre) {
		this.dspnombre = dspnombre;
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
	public DatosMaestroDTO getTipoidentificacion() {
		return tipoidentificacion;
	}
	public void setTipoidentificacion(DatosMaestroDTO tipoidentificacion) {
		this.tipoidentificacion = tipoidentificacion;
	}
	public String getDsidentificacion() {
		return dsidentificacion;
	}
	public void setDsidentificacion(String dsidentificacion) {
		this.dsidentificacion = dsidentificacion;
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
	public DatosMaestroDTO getSexo() {
		return sexo;
	}
	public void setSexo(DatosMaestroDTO sexo) {
		this.sexo = sexo;
	}
	public String getFhnacimiento() {
		return fhnacimiento;
	}
	public void setFhnacimiento(String fhnacimiento) {
		this.fhnacimiento = fhnacimiento;
	}
	public UsuarioDTO getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}
	public String getNombrecompleto() {
		return nombrecompleto;
	}
	public void setNombrecompleto(String nombrecompleto) {
		this.nombrecompleto = nombrecompleto;
	}
	
}
