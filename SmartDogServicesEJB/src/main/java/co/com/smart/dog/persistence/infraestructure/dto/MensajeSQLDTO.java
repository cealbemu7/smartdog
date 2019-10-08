package co.com.smart.dog.infraestructure.dto;

/**
 * DTO base de mensajes de respuesta SQL para todos los objetos transaccionales y de negocio en el contexto smart
 * @author SmartJugle S.A.S
 *
 */
public class MensajeSQLDTO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String descripcion;
	private String codigo;
	 
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	 
	 
}
