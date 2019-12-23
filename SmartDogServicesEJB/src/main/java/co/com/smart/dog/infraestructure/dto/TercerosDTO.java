package co.com.smart.dog.infraestructure.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TercerosDTO extends SmartBaseDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal sctercero;
	private DatosMaestroDTO tipodocumento;
	private EmpresaDTO empresa;
	private String coidentificacion;
	private String dsprimernombre;
	private String dssegundonombre;
	private String dsprimerapell;
	private String dssegundoapell;
	private String dsrazonsocial;
	private String dsnombrecont;
	private String cotelefono;
	private String dsextension;
	private String dscelular;
	private String dsemail;
	private String dsdireccion;
	private CiudadDTO ciudad;
	private DatosMaestroDTO tipotercero;
	private DatosMaestroDTO tipoempresa;
	private String fhnacimiento;
	private Date fhmodificacion;
	private Date fhingreso;
	private Date fhretiro;
	private String cousuario;
	private String nombrecontatenado;
	private String snrepresentantelegal;
	private String snproveedor;
	private String dscliente;
	private String fhdesde;
	private String fhhasta;
	private String Dsnombre;
	private DatosMaestroDTO sexo;
	
	public BigDecimal getSctercero() {
		return sctercero;
	}
	public void setSctercero(BigDecimal sctercero) {
		this.sctercero = sctercero;
	}
	public DatosMaestroDTO getTipodocumento() {
		return tipodocumento;
	}
	public void setTipodocumento(DatosMaestroDTO tipodocumento) {
		this.tipodocumento = tipodocumento;
	}
	public EmpresaDTO getEmpresa() {
		return empresa;
	}
	public void setEmpresa(EmpresaDTO empresa) {
		this.empresa = empresa;
	}
	public String getCoidentificacion() {
		return coidentificacion;
	}
	public void setCoidentificacion(String coidentificacion) {
		this.coidentificacion = coidentificacion;
	}
	public String getDsprimernombre() {
		return dsprimernombre;
	}
	public void setDsprimernombre(String dsprimernombre) {
		this.dsprimernombre = dsprimernombre;
	}
	public String getDssegundonombre() {
		return dssegundonombre;
	}
	public void setDssegundonombre(String dssegundonombre) {
		this.dssegundonombre = dssegundonombre;
	}
	public String getDsprimerapell() {
		return dsprimerapell;
	}
	public void setDsprimerapell(String dsprimerapell) {
		this.dsprimerapell = dsprimerapell;
	}
	public String getDssegundoapell() {
		return dssegundoapell;
	}
	public void setDssegundoapell(String dssegundoapell) {
		this.dssegundoapell = dssegundoapell;
	}
	public String getDsrazonsocial() {
		return dsrazonsocial;
	}
	public void setDsrazonsocial(String dsrazonsocial) {
		this.dsrazonsocial = dsrazonsocial;
	}
	public String getDsnombrecont() {
		return dsnombrecont;
	}
	public void setDsnombrecont(String dsnombrecont) {
		this.dsnombrecont = dsnombrecont;
	}
	public String getCotelefono() {
		return cotelefono;
	}
	public void setCotelefono(String cotelefono) {
		this.cotelefono = cotelefono;
	}
	public String getDsextension() {
		return dsextension;
	}
	public void setDsextension(String dsextension) {
		this.dsextension = dsextension;
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
	public DatosMaestroDTO getTipotercero() {
		return tipotercero;
	}
	public void setTipotercero(DatosMaestroDTO tipotercero) {
		this.tipotercero = tipotercero;
	}
	public DatosMaestroDTO getTipoempresa() {
		return tipoempresa;
	}
	public void setTipoempresa(DatosMaestroDTO tipoempresa) {
		this.tipoempresa = tipoempresa;
	}
	public String getFhnacimiento() {
		return fhnacimiento;
	}
	public void setFhnacimiento(String fhnacimiento) {
		this.fhnacimiento = fhnacimiento;
	}
	public Date getFhmodificacion() {
		return fhmodificacion;
	}
	public void setFhmodificacion(Date fhmodificacion) {
		this.fhmodificacion = fhmodificacion;
	}
	public Date getFhingreso() {
		return fhingreso;
	}
	public void setFhingreso(Date fhingreso) {
		this.fhingreso = fhingreso;
	}
	public Date getFhretiro() {
		return fhretiro;
	}
	public void setFhretiro(Date fhretiro) {
		this.fhretiro = fhretiro;
	}
	public String getCousuario() {
		return cousuario;
	}
	public void setCousuario(String cousuario) {
		this.cousuario = cousuario;
	}
	public String getNombrecontatenado() {
		return nombrecontatenado;
	}
	public void setNombrecontatenado(String nombrecontatenado) {
		this.nombrecontatenado = nombrecontatenado;
	}
	public String getSnrepresentantelegal() {
		return snrepresentantelegal;
	}
	public void setSnrepresentantelegal(String snrepresentantelegal) {
		this.snrepresentantelegal = snrepresentantelegal;
	}
	public String getSnproveedor() {
		return snproveedor;
	}
	public void setSnproveedor(String snproveedor) {
		this.snproveedor = snproveedor;
	}
	public String getDscliente() {
		return dscliente;
	}
	public void setDscliente(String dscliente) {
		this.dscliente = dscliente;
	}
	public String getFhdesde() {
		return fhdesde;
	}
	public void setFhdesde(String fhdesde) {
		this.fhdesde = fhdesde;
	}
	public String getFhhasta() {
		return fhhasta;
	}
	public void setFhhasta(String fhhasta) {
		this.fhhasta = fhhasta;
	}
	public String getDsnombre() {
		return Dsnombre;
	}
	public void setDsnombre(String dsnombre) {
		Dsnombre = dsnombre;
	}
	public DatosMaestroDTO getSexo() {
		return sexo;
	}
	public void setSexo(DatosMaestroDTO sexo) {
		this.sexo = sexo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
