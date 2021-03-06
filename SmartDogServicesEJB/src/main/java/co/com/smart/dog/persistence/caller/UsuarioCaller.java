package co.com.smart.dog.persistence.caller;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.naming.NamingException;

import co.com.smart.dog.infraestructure.dto.MensajeSQLDTO;
import co.com.smart.dog.infraestructure.dto.UsuarioDTO;
import co.com.smart.dog.persistence.entity.util.JDBCResourceManager;

public class UsuarioCaller extends JDBCResourceManager implements Serializable{

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
	public UsuarioCaller(String jdniData) {
		super(jdniData);
	}
	/**
	 * Metodo utilizado para consultar usuario
	 * @param usuario
	 * @return usuarios
	 * @throws SQLException
	 * @throws NamingException
	 * @throws IOException
	 */
	public UsuarioDTO consultarUsuario(UsuarioDTO usuario)throws SQLException, NamingException, IOException{
		 UsuarioDTO usuarioDTO = new UsuarioDTO(); 
		try {
			conn = getConnection();
			call = conn.prepareCall(getString("UsuarioCaller.fn_consultarusuario"));
			call.registerOutParameter(1, Types.OTHER);
			call.setString(2, usuario.getDsemail());
			call.setString(3, usuario.getDscontrasena());	
			
			if(usuario.getDsemail() != null){
				call.setString(2,usuario.getDsemail());
			}else{
				call.setNull(2, java.sql.Types.NULL);
			}
			if(usuario.getDscontrasena() != null){
				call.setString(3,usuario.getDscontrasena());
			}else{
				call.setNull(3, java.sql.Types.NULL);
			}
			
			call.execute();
			rs = (ResultSet) call.getObject(1);
         
	         while(rs.next()){	        	        	 
	        	 usuarioDTO.setScusuario(rs.getBigDecimal("sm_scusuario"));
	        	 usuarioDTO.setDsusuario(rs.getString("sm_dsusuario"));
	        	 usuarioDTO.setDscontrasena(rs.getString("sm_dscontrasena"));
	        	 usuarioDTO.setDsemail(rs.getString("sm_dsemail"));
	        	 usuarioDTO.setSecureToken(rs.getString("dstokenrequest"));
              }
        }finally{
			closeResources(conn, call);
        }
		
		return usuarioDTO;
	}
	/**
	 * Metodo utilizado para grabar usuario
	 * @param usuario
	 * @return
	 * @throws SQLException
	 * @throws NamingException
	 * @throws IOException
	 */
	
	public UsuarioDTO grabarUsuario(UsuarioDTO usuario) throws SQLException, NamingException, IOException{
		try { 
			conn = getConnection();
			call = conn.prepareCall(getString("UsuarioCaller.fn_grabar_usuario"));
			
			if (usuario.getScusuario() != null) {
				call.setInt(1, usuario.getScusuario().intValue());
            } else {
                call.setNull(1, java.sql.Types.INTEGER);
            }			
			call.setString(2, usuario.getDsusuario());
			
			if(usuario.getDscontrasena() != null){
				call.setString(3,usuario.getDscontrasena());
			}else{
				call.setNull(3, java.sql.Types.NULL);
			}
			
			call.setString(4, usuario.getDsemail());
			call.setString(5, usuario.getSecureToken());

			call.registerOutParameter(6, java.sql.Types.INTEGER);
			call.registerOutParameter(7, java.sql.Types.VARCHAR);
			
			call.executeUpdate();			
			usuario.setScusuario(new BigDecimal(call.getInt(6))); 
			MensajeSQLDTO msj = getResponseSQL(call.getString(7));
			usuario.setCodigo(msj.getCodigo());
			usuario.setDescripcion(msj.getDescripcion());			
		}finally {
			closeResources(conn, call);
		}
		return usuario;
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
