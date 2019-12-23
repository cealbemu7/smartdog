package co.com.smart.dog.session;

import co.com.smart.dog.bean.AsesorBeanLocal;
import co.com.smart.dog.bean.CitaBeanLocal;
import co.com.smart.dog.bean.CiudadBeanLocal;
import co.com.smart.dog.bean.ClienteBeanLocal;
import co.com.smart.dog.bean.DatosMaestroBeanLocal;
import co.com.smart.dog.bean.DepartamentoBeanLocal;
import co.com.smart.dog.bean.EmpresaBeanLocal;
import co.com.smart.dog.bean.LoginBeanLocal;
import co.com.smart.dog.bean.MaestroBeanLocal;
import co.com.smart.dog.bean.PaisBeanLocal;
import co.com.smart.dog.bean.PropiedadBeanLocal;
import co.com.smart.dog.bean.UsuarioBeanLocal;
import co.com.smart.dog.persistence.AsesorFacadeLocal;
import co.com.smart.dog.persistence.CitaFacadeLocal;
import co.com.smart.dog.persistence.CiudadFacadeLocal;
import co.com.smart.dog.persistence.ClienteFacadeLocal;
import co.com.smart.dog.persistence.DatosMaestroFacadeLocal;
import co.com.smart.dog.persistence.DepartamentoFacadeLocal;
import co.com.smart.dog.persistence.EmpresaFacadeLocal;
import co.com.smart.dog.persistence.LoginFacadeLocal;
import co.com.smart.dog.persistence.MaestroFacadeLocal;
import co.com.smart.dog.persistence.PaisFacadeLocal;
import co.com.smart.dog.persistence.PropiedadFacadeLocal;
import co.com.smart.dog.persistence.UsuarioFacadeLocal;
import co.com.smart.dog.session.base.SmartContextLookUp;

/**
 * Define la comunicacion entre BEAN y FACADE usando el patron singleton de
 * forma que delegue la transaccion sincrona y unica
 *
 * @author SmartJugle S.A.S
 *
 */
public class DelegateContextEJB extends SmartContextLookUp {

	/**
	 * Bean
	 */

	private static PaisBeanLocal getPaisBean;
	private static DepartamentoBeanLocal getDepartamentoBean;
	private static CiudadBeanLocal getCiudadBean;
	private static MaestroBeanLocal getMaestroBean;
	private static DatosMaestroBeanLocal getDatosMaestroBean;
	private static UsuarioBeanLocal getUsuarioBean;
	private static EmpresaBeanLocal getEmpresaBean;
	private static PropiedadBeanLocal getPropiedadBean;
	private static AsesorBeanLocal getAsesorBean;
	private static ClienteBeanLocal getClienteBean;
	private static CitaBeanLocal getCitaBean;
	private static LoginBeanLocal getLoginBean;
	
	/**
	 * Instancia unica del bean getPaisBean
	 *
	 * @return
	 */
	public static synchronized PaisBeanLocal getPaisBean() {

		if (getPaisBean == null) {
			getPaisBean = (PaisBeanLocal) getSmartContext("PaisBean");
		}

		return getPaisBean;
	}

	/**
	 * Instancia unica de la bean getDepartamentoBean
	 *
	 * @return
	 */
	public static synchronized DepartamentoBeanLocal getDepartamentoBean() {

		if (getDepartamentoBean == null) {
			getDepartamentoBean = (DepartamentoBeanLocal) getSmartContext("DepartamentoBean");
		}

		return getDepartamentoBean;
	}

	/**
	 * Instancia unica de la bean getCiudadBean
	 *
	 * @return
	 */
	public static synchronized CiudadBeanLocal getCiudadBean() {

		if (getCiudadBean == null) {
			getCiudadBean = (CiudadBeanLocal) getSmartContext("CiudadBean");
		}

		return getCiudadBean;
	}

	/**
	 * Instancia unica de la bean getGetMaestroBean
	 *
	 * @return
	 */
	public static synchronized MaestroBeanLocal getMaestroBean() {
		if (getMaestroBean == null) {
			getMaestroBean = (MaestroBeanLocal) getSmartContext("MaestroBean");
		}
		return getMaestroBean;
	}

	/**
	 * Instancia unica de la bean getDatosMaestroBean
	 *
	 * @return
	 */
	public static synchronized DatosMaestroBeanLocal getDatosMaestroBean() {
		if (getDatosMaestroBean == null) {
			getDatosMaestroBean = (DatosMaestroBeanLocal) getSmartContext("DatosMaestroBean");
		}
		return getDatosMaestroBean;
	}

	/**
	 * Instancia unica del bean getUsuarioBean
	 *
	 * @return
	 */
	public static synchronized UsuarioBeanLocal getUsuarioBean() {

		if (getUsuarioBean == null) {
			getUsuarioBean = (UsuarioBeanLocal) getSmartContext("UsuarioBean");
		}

		return getUsuarioBean;
	}

	/**
	 * Instancia unica del bean getEmpresaBean
	 *
	 * @return
	 */
	public static synchronized EmpresaBeanLocal getEmpresaBean() {

		if (getEmpresaBean == null) {
			getEmpresaBean = (EmpresaBeanLocal) getSmartContext("EmpresaBean");
		}

		return getEmpresaBean;
	}

	/**
	 * Instancia unica del bean getPropiedadBean
	 *
	 * @return
	 */
	public static synchronized PropiedadBeanLocal getPropiedadBean() {

		if (getPropiedadBean == null) {
			getPropiedadBean = (PropiedadBeanLocal) getSmartContext("PropiedadBean");
		}

		return getPropiedadBean;
	}
	
	
	
	/**
	 * Instancia unica del bean getAsesorBean
	 *
	 * @return
	 */
	public static synchronized AsesorBeanLocal getAsesorBean() {

		if (getAsesorBean == null) {
			getAsesorBean = (AsesorBeanLocal) getSmartContext("AsesorBean");
		}

		return getAsesorBean;
	}
	

	/**
	 * Instancia unica del bean getClienteBean
	 *
	 * @return
	 */
	public static synchronized ClienteBeanLocal getClienteBean() {

		if (getClienteBean == null) {
			getClienteBean = (ClienteBeanLocal) getSmartContext("ClienteBean");
		}

		return getClienteBean;
	}
	
	/**
	 * Instancia unica del bean getCitaBean
	 *
	 * @return
	 */
	public static synchronized CitaBeanLocal getCitaBean() {

		if (getCitaBean == null) {
			getCitaBean = (CitaBeanLocal) getSmartContext("CitaBean");
		}

		return getCitaBean;
	}
	
	/**
	 * Instancia unica del bean de LoginBean
	 * 
	 * @return
	 */
	public static synchronized LoginBeanLocal getLoginBean() {

		if (getLoginBean == null) {
			getLoginBean = (LoginBeanLocal) getSmartContext("LoginBean");
		}

		return getLoginBean;
	}

	/**
	 * Facade ----------------------------------------------
	 */

	private static PaisFacadeLocal getPaisFacade;
	private static DepartamentoFacadeLocal getDepartamentoFacade;
	private static CiudadFacadeLocal getCiudadFacade;
	private static MaestroFacadeLocal getMaestroFacade;
	private static DatosMaestroFacadeLocal getDatosMaestroFacade;
	private static UsuarioFacadeLocal getUsuarioFacade;
	private static EmpresaFacadeLocal getEmpresaFacade;
	private static PropiedadFacadeLocal getPropiedadFacade;
	private static AsesorFacadeLocal getAsesorFacade;
	private static ClienteFacadeLocal getClienteFacade;
	private static CitaFacadeLocal getCitaFacade;
	private static LoginFacadeLocal getLoginFacade;

	/**
	 * Instancia unica de la facade getPaisFacade
	 *
	 * @return
	 */
	public static synchronized PaisFacadeLocal getPaisFacade() {		if (getPaisFacade == null) {
			getPaisFacade = (PaisFacadeLocal) getSmartContext("PaisFacade");
		}

		return getPaisFacade;
	}

	/**
	 * Instancia unica de la fachada getDepartamentoFacade
	 *
	 * @return
	 */
	public static synchronized DepartamentoFacadeLocal getDepartamentoFacade() {

		if (getDepartamentoFacade == null) {
			getDepartamentoFacade = (DepartamentoFacadeLocal) getSmartContext("DepartamentoFacade");
		}

		return getDepartamentoFacade;
	}

	/**
	 * Instancia unica de la fachada getCiudadFacade
	 *
	 * @return
	 */
	public static synchronized CiudadFacadeLocal getCiudadFacade() {

		if (getCiudadFacade == null) {
			getCiudadFacade = (CiudadFacadeLocal) getSmartContext("CiudadFacade");
		}

		return getCiudadFacade;
	}

	/**
	 * Instancia unica de la fachada getMaestroFacade
	 *
	 * @return
	 */
	public static synchronized MaestroFacadeLocal getMaestroFacade() {

		if (getMaestroFacade == null) {
			getMaestroFacade = (MaestroFacadeLocal) getSmartContext("MaestroFacade");
		}

		return getMaestroFacade;
	}

	/**
	 * Instancia unica de la fachada getDatosMaestroFacade
	 *
	 * @return
	 */
	public static synchronized DatosMaestroFacadeLocal getDatosMaestroFacade() {

		if (getDatosMaestroFacade == null) {
			getDatosMaestroFacade = (DatosMaestroFacadeLocal) getSmartContext("DatosMaestroFacade");
		}

		return getDatosMaestroFacade;
	}

	/**
	 * Instancia unica de la fachada getUsuarioFacade
	 *
	 * @return
	 */
	public static synchronized UsuarioFacadeLocal getUsuarioFacade() {

		if (getUsuarioFacade == null) {
			getUsuarioFacade = (UsuarioFacadeLocal) getSmartContext("UsuarioFacade");
		}

		return getUsuarioFacade;
	}

	/**
	 * Instancia unica de la fachada getEmpresaFacade
	 *
	 * @return
	 */
	public static synchronized EmpresaFacadeLocal getEmpresaFacade() {
		if (getEmpresaFacade == null) {
			getEmpresaFacade = (EmpresaFacadeLocal) getSmartContext("EmpresaFacade");
		}
		return getEmpresaFacade;
	}

	/**
	 * Instancia unica de la fachada getPropiedadFacade
	 *
	 * @return
	 */

	public static synchronized PropiedadFacadeLocal getPropiedadFacade() {
		if (getPropiedadFacade == null) {
			getPropiedadFacade = (PropiedadFacadeLocal) getSmartContext("PropiedadFacade");
		}
		return getPropiedadFacade;
	}

	 /**
	  * Instancia unica de la facade 
	  * @return
	  */
	 public static synchronized AsesorFacadeLocal getAsesorFacade() {

		if (getAsesorFacade == null) {
			getAsesorFacade = (AsesorFacadeLocal) getSmartContext("AsesorFacade");
		}

		return getAsesorFacade;
	 }
	 /**
	  * Instancia unica de la facade ClienteFacade
	  * @return
	  */
	public static synchronized ClienteFacadeLocal getClienteFacade() {
		if (getClienteFacade == null) {
			getClienteFacade = (ClienteFacadeLocal) getSmartContext("ClienteFacade");
		}
		return getClienteFacade;
	}
	 /**
	  * Instancia unica de la facade CitaFacade
	  * @return
	  */
	public static synchronized CitaFacadeLocal getCitaFacade() {
		if (getCitaFacade == null) {
			getCitaFacade = (CitaFacadeLocal) getSmartContext("CitaFacade");
		}
		return getCitaFacade;
	}
	
	/**
	 * Instancia unica de la facade getLoginFacade
	 * @return
	 */
	public static synchronized LoginFacadeLocal getLoginFacade() {

		if (getLoginFacade == null) {
			getLoginFacade = (LoginFacadeLocal) getSmartContext("LoginFacade");
		}

		return getLoginFacade;
	}
}
