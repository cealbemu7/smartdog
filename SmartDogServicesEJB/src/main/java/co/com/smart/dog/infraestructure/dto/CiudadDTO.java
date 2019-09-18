package co.com.smart.dog.infraestructure.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CiudadDTO extends SmartBaseDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal scciudad;
    private String cociudad;
    private String dsciudad;
    private String cousuario;
    private Date fhingreso;
    private Date fhmodificacion;
    private Date fhretiro;    
    private DepartamentoDTO departamentoDTO;
    
	public BigDecimal getScciudad() {
		return scciudad;
	}
	public void setScciudad(BigDecimal scciudad) {
		this.scciudad = scciudad;
	}
	public String getCociudad() {
		return cociudad;
	}
	public void setCociudad(String cociudad) {
		this.cociudad = cociudad;
	}
	public String getDsciudad() {
		return dsciudad;
	}
	public void setDsciudad(String dsciudad) {
		this.dsciudad = dsciudad;
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
	public DepartamentoDTO getDepartamentoDTO() {
		return departamentoDTO;
	}
	public void setDepartamentoDTO(DepartamentoDTO departamentoDTO) {
		this.departamentoDTO = departamentoDTO;
	}
}
