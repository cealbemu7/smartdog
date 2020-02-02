package co.com.smart.dog.infraestructure.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CitaDTO extends SmartBaseDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BigDecimal sccita;
	private PropiedadDTO propiedad;
	private ClienteDTO cliente;
	private String fhhorainicio;
	private String fhhoracita;
	private String fhcita;
	private UsuarioDTO usuario;
	private Date fhingreso;
	private String fhhorafin;
	private AsesorDTO asesor;
	private DatosMaestroDTO estado;
	private EmpresaDTO empresa;
    private Date fhmodificacion;
    private Date fhretiro;
    private DatosMaestroDTO tipoinmueble;
    
	public BigDecimal getSccita() {
		return sccita;
	}
	public void setSccita(BigDecimal sccita) {
		this.sccita = sccita;
	}
	public PropiedadDTO getPropiedad() {
		return propiedad;
	}
	public void setPropiedad(PropiedadDTO propiedad) {
		this.propiedad = propiedad;
	}
	public ClienteDTO getCliente() {
		return cliente;
	}
	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}
	public String getFhhorainicio() {
		return fhhorainicio;
	}
	public void setFhhorainicio(String fhhorainicio) {
		this.fhhorainicio = fhhorainicio;
	}
	public UsuarioDTO getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}
	public Date getFhingreso() {
		return fhingreso;
	}
	public void setFhingreso(Date fhingreso) {
		this.fhingreso = fhingreso;
	}
	public String getFhhorafin() {
		return fhhorafin;
	}
	public void setFhhorafin(String fhhorafin) {
		this.fhhorafin = fhhorafin;
	}
	public AsesorDTO getAsesor() {
		return asesor;
	}
	public void setAsesor(AsesorDTO asesor) {
		this.asesor = asesor;
	}
	public DatosMaestroDTO getEstado() {
		return estado;
	}
	public void setEstado(DatosMaestroDTO estado) {
		this.estado = estado;
	}
	public EmpresaDTO getEmpresa() {
		return empresa;
	}
	public void setEmpresa(EmpresaDTO empresa) {
		this.empresa = empresa;
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
	public DatosMaestroDTO getTipoinmueble() {
		return tipoinmueble;
	}
	public void setTipoinmueble(DatosMaestroDTO tipoinmueble) {
		this.tipoinmueble = tipoinmueble;
	}
	public String getFhhoracita() {
		return fhhoracita;
	}
	public void setFhhoracita(String fhhoracita) {
		this.fhhoracita = fhhoracita;
	}
	public String getFhcita() {
		return fhcita;
	}
	public void setFhcita(String fhcita) {
		this.fhcita = fhcita;
	}
}
