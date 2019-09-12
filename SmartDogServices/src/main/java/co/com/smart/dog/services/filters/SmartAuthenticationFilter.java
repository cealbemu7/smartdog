/**
 * 
 */
package co.com.smart.dog.services.filters;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import co.com.smart.dog.services.base.SmartResponseBase;
import co.com.smart.dog.utility.SmartConstant;
import co.com.smart.dog.utility.SmartUtilities;

/**
 * Filtro de seguridad para la ejecucion de los servicios con una sesion activa
 * y segura
 * 
 * @author SmartJungle S.A.S
 *
 */
public class SmartAuthenticationFilter extends SmartResponseBase implements
		Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String METHOD_LOGIN = "login";
	private static final String SESSION_ACTIVE = "SESSION.ACTIVE";
	private static final Logger logger = Logger.getLogger(SmartConstant.LOGGER_NAME);

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		logger.debug("SmartAuthenticationFilter finalized");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		String uri = req.getRequestURI();
		String[] compositeUri = uri.split("\\/");
		String methodConsumer = null;
		if (compositeUri.length > 4) {
			methodConsumer = compositeUri[4];
			String secureToken = req.getHeader(SmartConstant.HEADER_TOKEN);
			if (!METHOD_LOGIN.equals(methodConsumer) && Boolean.getBoolean(SmartUtilities.getProperty(SESSION_ACTIVE))) {
				if ("".equals(secureToken) || secureToken == null) {
					responseError(res, SmartHttpTypeResponse.RESP_HTTP_401);
					return;
				} else {
					String[] splitString = secureToken.split("\\.");
					if (splitString.length == 3) {
						try{
							Jws<Claims> claims = Jwts.parser()
						             .setSigningKey(SmartUtilities.decodeToString(SmartUtilities.getProperty(SmartConstant.CONF_KEY),true))
						             .parseClaimsJws(secureToken);
							
							Date exp = claims.getBody().getExpiration();
							Date actual = new Date();
							if(SmartUtilities.getProperty(SmartConstant.ACTIVATE_SESSION_EXPIRED).equals(SmartConstant.CONF_S) && exp.before(actual)){
									responseError(res, SmartHttpTypeResponse.RESP_HTTP_401);
									return;
							}
							
						}catch(SignatureException jsex){
							logger.debug(jsex.getMessage(),jsex);
							responseError(res, SmartHttpTypeResponse.RESP_HTTP_401);
							return;
						}
					}else{
						responseError(res, SmartHttpTypeResponse.RESP_HTTP_401);
						return;
					}
				}
			}
		}
		chain.doFilter(request, response);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		logger.debug("SmartAuthenticationFilter initialized");
	}

}
