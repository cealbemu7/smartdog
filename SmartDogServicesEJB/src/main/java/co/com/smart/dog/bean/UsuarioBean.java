package co.com.smart.dog.bean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.ejb.Stateless;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import co.com.smart.dog.infraestructure.dto.SmartExcepcionSerializada;
import co.com.smart.dog.infraestructure.dto.UsuarioDTO;
import co.com.smart.dog.persistence.UsuarioFacadeLocal;
import co.com.smart.dog.session.DelegateContextEJB;
import co.com.smart.dog.utility.SmartConstant;
import co.com.smart.dog.utility.SmartEmail;
import co.com.smart.dog.utility.SmartUtilities;

/**
 * Session Bean implementation class UsuarioBean
 */
@Stateless(name = "UsuarioBean", mappedName = "ejb/UsuarioBean")
public class UsuarioBean extends AbstractBean implements UsuarioBeanLocal {
	private UsuarioFacadeLocal facade;

	VelocityEngine velocityEngine;
	Map<String, String> configs;
	Properties props;

	/**
	 * Default constructor
	 */
	public UsuarioBean() {
		facade = DelegateContextEJB.getUsuarioFacade();
		props = new Properties();
		props.setProperty("resource.loader", "class");
		props.setProperty("class.resource.loader.class","org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		configs = new HashMap<>();
	}

	/**
	 * metodo utilizado para consultar usuario
	 */
	@Override
	public List<UsuarioDTO> consultarUsuario(UsuarioDTO json)
			throws SmartExcepcionSerializada {
		List<UsuarioDTO> usuarios = new ArrayList<>();
		try {			
			usuarios = facade.consultarUsuario(json);
		} catch (Throwable ex) {
			SmartExcepcionSerializada smartException = new SmartExcepcionSerializada();
			smartException.setCode(0);
			smartException.setStackTrace(ex.getStackTrace());
			throw smartException;
		}
		return usuarios;

	}

	/**
	 * metodo utilizado para grabar usuario
	 */

	@Override
	public UsuarioDTO grabarUsuario(UsuarioDTO json)
			throws SmartExcepcionSerializada {
		UsuarioDTO response = new UsuarioDTO();
		try {
			String secureToken = generateSecureToken(json);
			json.setSecureToken(secureToken);
			response = facade.grabarUsuario(json);// primero guardo
			
			//luego envio correo
			VelocityContext context = loadParamsVelocity(facade.getParams("EMAIL-SMTP-REQUEST-USERS"));

			SmartEmail mail = new SmartEmail(configs);
			velocityEngine = new VelocityEngine(props);
			velocityEngine.init();
			Template template = velocityEngine.getTemplate("/co/com/smart/dog/templates/templateEmailRequestCreateUser.vm");
			context.put("user", json.getDsusuario());
			context.put("email", json.getDsemail());

			Jws<Claims> claims = getInfoSecureToken(json.getSecureToken());

			Date expire = claims.getBody().getExpiration();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");

			context.put("token", json.getSecureToken());
			context.put("expire", dateFormat.format(expire.getTime()));
			context.put("year", Calendar.getInstance().get(Calendar.YEAR));

			StringWriter html = new StringWriter();

			template.merge(context, html);
			mail.setSubject("Confirmacion de registro");
			mail.addRecipients(json.getDsemail());
			mail.setContent(html.toString(), true);
			mail.send();
			
		} catch (Throwable ex) {
			ex.printStackTrace(System.err);
			SmartExcepcionSerializada smartException = new SmartExcepcionSerializada();
			smartException.setCode(0);
			smartException.setMensaje(ex.getMessage());
			smartException.setStackTrace(ex.getStackTrace());
			throw smartException;
		}
		return response;
	}

	@Override
	public String generateSecureToken(UsuarioDTO json)
			throws SmartExcepcionSerializada {

		long time = System.currentTimeMillis();
		long timeExpiration = Long.parseLong(SmartUtilities
				.getProperty(SmartConstant.EXPIRATION));
		return Jwts
				.builder()
				.signWith(
						SignatureAlgorithm.HS256,
						SmartUtilities.decodeToString(SmartUtilities
								.getProperty(SmartConstant.CONF_KEY), true))
				.setSubject(
						SmartUtilities.decodeToString(SmartUtilities
								.getProperty(SmartConstant.SUBJECT), true))
				.setIssuedAt(new Date(time))
				.setExpiration(new Date(time + timeExpiration))
				.claim("user-name", json.getDsusuario())
				.claim("user-email", json.getDsemail()).compact();
	}

	@Override
	public Jws<Claims> getInfoSecureToken(String secureToken) {
		return Jwts
				.parser()
				.setSigningKey(
						SmartUtilities.decodeToString(SmartUtilities
								.getProperty(SmartConstant.CONF_KEY), true))
				.parseClaimsJws(secureToken);

	}

	/**
	 * Load paramas to context velocity
	 * 
	 * @param params
	 * @return
	 */
	private VelocityContext loadParamsVelocity(Map<String, String> params) {
		Iterator<Entry<String, String>> paramsLoad = params.entrySet()
				.iterator();
		VelocityContext context = new VelocityContext();
		while (paramsLoad.hasNext()) {
			Entry<String, String> config = paramsLoad.next();
			context.put(config.getKey(), config.getValue());
			configs.put(config.getKey(), config.getValue());
		}
		return context;
	}
	
	@Override
	public UsuarioDTO solicitarRegistroUsuario(UsuarioDTO json) 
		throws SmartExcepcionSerializada {
			UsuarioDTO response = new UsuarioDTO();
			try {
				response = facade.grabarUsuario(json);
				/*TODO: 
				 *   1) genero token JWT apartir del correo, la aplicacion y la fecha con hora
				 *   2) almaceno solicitud en usuarios (Base de datos, funcion que crea la solicitud de registro)
				 *   3) crear plantilla de notificacion velocity y mapeo de campos, que contenido debe tener el correo
				 *   4) respondemos la informacion de registro exitoso
				 */
			} catch (Throwable ex) {
				ex.printStackTrace(System.err);
				SmartExcepcionSerializada smartException = new SmartExcepcionSerializada();
				smartException.setCode(0);
				smartException.setMensaje(ex.getMessage());
				smartException.setStackTrace(ex.getStackTrace());
				throw smartException;
			}
			return response;
	}

}
