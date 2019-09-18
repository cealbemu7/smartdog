package co.com.smart.dog.session;

import co.com.smart.dog.bean.CiudadBeanLocal;
import co.com.smart.dog.bean.DepartamentoBeanLocal;
import co.com.smart.dog.bean.PaisBeanLocal;
import co.com.smart.dog.persistence.CiudadFacadeLocal;
import co.com.smart.dog.persistence.DepartamentoFacadeLocal;
import co.com.smart.dog.persistence.PaisFacadeLocal;
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
	 * Instancia unica de la fachada getDepartamentoBean
	 * @return
	 */
	public static synchronized DepartamentoBeanLocal getDepartamentoBean() {

		if (getDepartamentoBean == null) {
			getDepartamentoBean = (DepartamentoBeanLocal) getSmartContext("DepartamentoBean");
		}

		return getDepartamentoBean;
	}

	/**
	 * Instancia unica de la fachada getCiudadBean
	 * @return
	 */
	public static synchronized CiudadBeanLocal getCiudadBean() {

		if (getCiudadBean == null) {
			getCiudadBean = (CiudadBeanLocal) getSmartContext("CiudadBean");
		}

		return getCiudadBean;
	}
	
	/**
	 * Facade
	 */
	
	private static PaisFacadeLocal getPaisFacade;
	private static DepartamentoFacadeLocal getDepartamentoFacade;
	private static CiudadFacadeLocal getCiudadFacade;
	
	
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


}