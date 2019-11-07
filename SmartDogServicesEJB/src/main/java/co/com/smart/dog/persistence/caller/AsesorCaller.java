package co.com.smart.dog.persistence.caller;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import co.com.smart.dog.infraestructure.dto.AsesorDTO;
import co.com.smart.dog.infraestructure.dto.MensajeSQLDTO;
import co.com.smart.dog.persistence.entity.util.JDBCResourceManager;

public class AsesorCaller extends JDBCResourceManager implements Serializable{

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
	public AsesorCaller(String jdniData) {
		super(jdniData);
	}

/**
 * grabar asesor
 * @param asesor
 * @return asesor
 * @throws SQLException
 * @throws NamingException
 * @throws IOException
 */
	public AsesorDTO grabarAsesor(AsesorDTO asesor) throws SQLException, NamingException, IOException{
		try { 

			conn = getConnection();
			call = conn.prepareCall(getString("AsesorCaller.fn_grabar_empresa"));
			
			if (asesor.getScasesor() != null) {
				call.setInt(1, asesor.getScasesor().intValue());
            } else {
                call.setNull(1, java.sql.Types.INTEGER);
            }
			
			
			call.setBigDecimal(2, asesor.getSctipoidentificacion());
			call.setString(3, asesor.getDsidentificacion());
			call.setString(4, asesor.getDsemail());
			call.setString(5, asesor.getDstelefono());
			call.setString(6, asesor.getCousuario());
			call.setInt(7, asesor.getCiudad().getScciudad().intValue());
			call.setInt(8, asesor.getCiudad().getDepartamento().getScdepartamento().intValue());		
			call.setString(9, asesor.getDsdireccion());
			call.setString(10,asesor.getDspnombre());
			call.setString(11,asesor.getDssnombre());
			call.setString(12,asesor.getDspapellido());
			call.setString(13,asesor.getDssapellido());
			call.setString(14,asesor.getDscelular());
			call.setString(15,asesor.getFhnacimineto());
			call.setBigDecimal(16, asesor.getScsexo());
	
			call.registerOutParameter(17, java.sql.Types.INTEGER);
			call.registerOutParameter(18, java.sql.Types.VARCHAR);
			
			call.executeUpdate();
			
			
			asesor.setScasesor(new BigDecimal(call.getInt(17))); 
			MensajeSQLDTO msj = getResponseSQL(call.getString(18));
			asesor.setCodigo(msj.getCodigo());
			asesor.setDescripcion(msj.getDescripcion());
		}finally {
			closeResources(conn, call);
			
		}
		return asesor;
	}
	
		
		@Override
		public MensajeSQLDTO getResponseSQL(String response) {
			MensajeSQLDTO sqldto = new MensajeSQLDTO();
			
			if(response != null){
				String[] msj = response.split(":");
				sqldto.setCodigo(msj[0]);
				sqldto.setDescripcion(msj[1]);
			}
			
			return sqldto;
		}
}
