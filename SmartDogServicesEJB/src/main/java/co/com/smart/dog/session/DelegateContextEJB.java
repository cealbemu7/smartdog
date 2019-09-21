package co.com.smart.dog.session;

import co.com.smart.dog.bean.CiudadBeanLocal;
import co.com.smart.dog.bean.DatosMaestroBeanLocal;
import co.com.smart.dog.bean.DepartamentoBeanLocal;
import co.com.smart.dog.bean.MaestroBeanLocal;
import co.com.smart.dog.bean.PaisBeanLocal;
import co.com.smart.dog.bean.UsuarioBeanLocal;
import co.com.smart.dog.persistence.CiudadFacadeLocal;
import co.com.smart.dog.persistence.DatosMaestroFacadeLocal;
import co.com.smart.dog.persistence.DepartamentoFacadeLocal;
import co.com.smart.dog.persistence.MaestroFacadeLocal;
import co.com.smart.dog.persistence.PaisFacadeLocal;
import co.com.smart.dog.persistence.UsuarioFacadeLocal;
import co.com.smart.dog.session.base.SmartContextLookUp;



/**
 * Define la comunicacion entre BEAN y FACADE 
 * usando el patron singleton de forma que delegue la transaccion 
 * sincrona y unica 
 * @author SmartJugle S.A.S
 *
 */
public class DelegateContextEJB  extends SmartContextLookUp{

	/**
	 * Bean
	 */
	
	private static PaisBeanLocal getPaisBean;
	private static DepartamentoBeanLocal getDepartamentoBean;
	private static CiudadBeanLocal getCiudadBean;
	private static MaestroBeanLocal getMaestroBean;
	private static DatosMaestroBeanLocal getDatosMaestroBean;
	private static UsuarioBeanLocal getUsuarioBean;
	
	 /**
	  * Instancia unica del bean getPaisBean
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
	 * @return
	 */	
	public static synchronized MaestroBeanLocal getMaestroBean() {
		if (getMaestroBean == null){
			getMaestroBean = (MaestroBeanLocal) getSmartContext("MaestroBean");
		}
		return getMaestroBean;
	}

	/**
	 * Instancia unica de la bean getDatosMaestroBean
	 * @return
	 */	
	public static synchronized DatosMaestroBeanLocal getDatosMaestroBean() {
		if (getDatosMaestroBean == null){
			getDatosMaestroBean = (DatosMaestroBeanLocal) getSmartContext("DatosMaestroBean");
		}
		return getDatosMaestroBean;
	}
	
	 /**
	  * Instancia unica del bean getUsuarioBean
	  * @return
	  */
	 public static synchronized UsuarioBeanLocal getUsuarioBean() {

	  if (getUsuarioBean == null) {
		  getUsuarioBean = (UsuarioBeanLocal) getSmartContext("UsuarioBean");
	  }

	  return getUsuarioBean;
	 }
	
	/**
	 * Facade
	 */
	
	private static PaisFacadeLocal getPaisFacade;
	private static DepartamentoFacadeLocal getDepartamentoFacade;
	private static CiudadFacadeLocal getCiudadFacade;
	private static MaestroFacadeLocal getMaestroFacade;
	private static DatosMaestroFacadeLocal getDatosMaestroFacade;
	private static UsuarioFacadeLocal getUsuarioFacade;
	
	 /**
	  * Instancia unica de la facade getPaisFacade
	  * @return
	  */
	 public static synchronized PaisFacadeLocal getPaisFacade() {

		if (getPaisFacade == null) {
			getPaisFacade = (PaisFacadeLocal) getSmartContext("PaisFacade");
		}
		
		return getPaisFacade;
	 }
	 
	/**
	 * Instancia unica de la fachada getDepartamentoFacade
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
	 * @return
	 */
	public static synchronized UsuarioFacadeLocal getUsuarioFacade() {

		if (getUsuarioFacade == null) {
			getUsuarioFacade = (UsuarioFacadeLocal) getSmartContext("UsuarioFacade");
		}

		return getUsuarioFacade;
	}


}