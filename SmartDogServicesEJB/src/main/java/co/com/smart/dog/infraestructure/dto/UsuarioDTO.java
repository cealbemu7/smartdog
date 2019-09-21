package co.com.smart.dog.infraestructure.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class UsuarioDTO extends SmartBaseDTO implements Serializable{

	/**
	 * Objecto del modulo ingreso al sistema
	 * Fecha Creacion: 2019/09/21
	 *  
	 */
	private static final long serialVersionUID = 1L;
	
	private BigDecimal scusuario;
    private String dsusuario;
    private String dscontrasena;
    private String dsemail;
    private Date fhingreso;
    private Date fhmodificacion;
    private Date fhretiro;
	public BigDecimal getScusuario() {
		return scusuario;
	}
	public void setScusuario(BigDecimal scusuario) {
		this.scusuario = scusuario;
	}
	public String getDsusuario() {
		return dsusuario;
	}
	public void setDsusuario(String dsusuario) {
		this.dsusuario = dsusuario;
	}
	public String getDscontrasena() {
		return dscontrasena;
	}
	public void setDscontrasena(String dscontrasena) {
		this.dscontrasena = dscontrasena;
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
    
}
