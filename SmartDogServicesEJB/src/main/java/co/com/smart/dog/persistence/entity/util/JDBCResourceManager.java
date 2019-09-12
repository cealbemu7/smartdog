package co.com.smart.dog.persistence.entity.util;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import co.com.smart.dog.utility.SmartConstant;

/**
 * Clase manejadora de la conexion a la bd sin utilizar JPA
 *
 * @author Andres Escobar
 */
public class JDBCResourceManager implements Serializable{
	
	private static final ResourceBundle PROPERTIES = ResourceBundle.getBundle("co.com.smart.dog.properties.smart-services-caller");

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String jdni_data;

	private static final Logger logger = Logger.getLogger(SmartConstant.LOGGER_NAME);
	
	protected static final String SMART_DB_ERROR_CODE = "45000";

	public JDBCResourceManager() {} // Compliant; no-arg constructor added to ancestor

	public JDBCResourceManager(String jdniData) {
		this.jdni_data = jdniData;
	}

	public enum TipoTransaccion {

		GLOBAL, AUTOCOMMIT
	}

	public Connection getConnection() throws SQLException, NamingException {
		Context ctx = null;
		ctx = new InitialContext();
		DataSource data = (DataSource) ctx.lookup(jdni_data);
		return data.getConnection();
	}

	public void closeResources(Connection connection, CallableStatement ocs) {
		try {
			if (ocs != null) {
				ocs.close();
			}
		} catch (Exception e) {
			logger.error("Error critico cerrando recursos", e);
		} finally {
			closeResources(connection);
		}
	}

	public void closeResources(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			logger.error("Error critico cerrando recursos", e);
		}
	}

	public void closeResources(Connection connection, PreparedStatement ps) {
		try {
			closeResources(ps);
		} catch (Exception e) {
			logger.error("Error critico cerrando recursos", e);
		} finally {
			closeResources(connection);
		}
	}

	public void closeResources(Connection connection, PreparedStatement ps,
			ResultSet rs) {
		try {
			closeResources(rs);
			closeResources(ps);

		} catch (Exception e) {
			logger.error("Error critico cerrando recursos", e);
		} finally {
			closeResources(connection);
		}
	}

	public void closeResources(Connection connection, Statement ps, ResultSet rs) {
		try {
			closeResources(rs);
			closeResources(ps);
		} catch (Exception e) {
			logger.error("Error critico cerrando recursos", e);
		} finally {
			closeResources(connection);
		}
	}

	public void closeResources(ResultSet rs) {
		try {
			if (rs != null && !rs.isClosed()) {
					rs.close();
			}
		} catch (Exception e) {
			logger.error("Error critico cerrando recursos", e);
		}
	}

	public void closeResources(Statement ps) {
		try {
			if (ps != null && !ps.isClosed()) {
					ps.close();
			}
		} catch (Exception e) {
			logger.error("Error critico cerrando recursos", e);
		}
	}

	public void closeResources(PreparedStatement ps) {
		try {
			if (ps != null && !ps.isClosed()) {
					ps.close();
			}
		} catch (Exception e) {
			logger.error("Error critico cerrando recursos", e);
		}
	}
	
	/**
	 * Obtiene las propiedades del llamado a los objetos de BD
	 * @param key
	 * @return
	 */
	public static String getString(String key){
		return "{ "+PROPERTIES.getString(key)+" }";
	}

}
