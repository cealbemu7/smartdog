package co.com.smart.dog.infraestructure.dto;

import java.io.Serializable;

/**
 * 
 * @author SmartJugle S.A.S
 *
 */
public class SmartExcepcionSerializada extends RuntimeException implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private String mensaje;
	private int code;
	
	public SmartExcepcionSerializada() {
		this("");
	}
	
	public SmartExcepcionSerializada(String mensaje) {
		setMensaje(mensaje);
	}
	
	public String getMensaje() {
		
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	@Override
	public String toString() {
		return "ExcepcionSerializada [mensaje=" + mensaje +"]";
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}	
	
	
}
