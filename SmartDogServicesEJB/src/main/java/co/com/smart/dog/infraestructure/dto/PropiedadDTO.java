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
	private String cousuario;
	
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
