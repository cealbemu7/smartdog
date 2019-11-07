package co.com.smart.dog.infraestructure.dto;

import java.io.Serializable;
import java.util.List;

public class ObjectListaDTO extends SmartBaseDTO implements Serializable{

	/**
	 * Objeto utilizado para relacionar objetos para la carga inicial 
	 */
	private static final long serialVersionUID = 1L;
	
	private DatosMaestroDTO tipodocumento;
	private String fechaactual;
	private DatosMaestroDTO sexo;
	private DatosMaestroDTO tipoinmueble;
	
	public DatosMaestroDTO getTipodocumento() {
		return tipodocumento;
	}
	public void setTipodocumento(DatosMaestroDTO tipodocumento) {
		this.tipodocumento = tipodocumento;
	}
	public String getFechaactual() {
		return fechaactual;
	}
	public void setFechaactual(String fechaactual) {
		this.fechaactual = fechaactual;
	}
	public DatosMaestroDTO getSexo() {
		return sexo;
	}
	public void setSexo(DatosMaestroDTO sexo) {
		this.sexo = sexo;
	}

	public DatosMaestroDTO getTipoinmueble() {
		return tipoinmueble;
	}
	public void setTipoinmueble(DatosMaestroDTO tipoinmueble) {
		this.tipoinmueble = tipoinmueble;
	}

	/**
	 * Listas que retornan los maestros 
	 */	
	private List<DatosMaestroDTO> listatipodocumento;
	private List<DatosMaestroDTO> listasexo;
	private List<DatosMaestroDTO> listatipoinmueble;
	
	public List<DatosMaestroDTO> getListatipodocumento() {
		return listatipodocumento;
	}
	public void setListatipodocumento(List<DatosMaestroDTO> listatipodocumento) {
		this.listatipodocumento = listatipodocumento;
	}
	public List<DatosMaestroDTO> getListasexo() {
		return listasexo;
	}
	public void setListasexo(List<DatosMaestroDTO> listasexo) {
		this.listasexo = listasexo;
	}
	
	public List<DatosMaestroDTO> getListaTipoInmueble() {
		return listatipoinmueble;
	}
	public void setListaTipoInmueble(List<DatosMaestroDTO> listatipoinmueble) {
		this.listatipoinmueble = listatipoinmueble;
	}
}
