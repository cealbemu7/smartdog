/**
 * 
 */
package co.com.smart.dog.persistence.caller;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import javax.naming.NamingException;

import co.com.smart.dog.infraestructure.dto.MensajeSQLDTO;
import co.com.smart.dog.persistence.entity.util.JDBCResourceManager;

/**
 * @author user
 *
 */
public class ParametersCaller extends JDBCResourceManager implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Connection conn = null;
	CallableStatement call = null;
	ResultSet rs = null;
	PreparedStatement pst = null;

	/**
	 * @param jdniData
	 */
	public ParametersCaller(String jdniData) {
		super(jdniData);
	}

	public Map<String, String> getParams(String dsparameter)
			throws SQLException, NamingException {
		Map<String, String> result = new HashMap<>();
		try {
			conn = getConnection();
			call = conn.prepareCall(getString("ParametersCaller.fn_get_parameters"));
			call.registerOutParameter(1, Types.OTHER);
			call.setString(2, dsparameter);
			call.execute();
			rs = (ResultSet) call.getObject(1);
			while (rs.next()) {
			  result.put(rs.getString("DSFIELD1"), rs.getString("DSFIELD2"));
			}
		} finally {
			closeResources(conn, call);
		}

		return result;
	}

	@Override
	public MensajeSQLDTO getResponseSQL(String response) {
		return null;
	}

}
