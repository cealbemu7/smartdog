package co.com.smart.dog.persistence.caller;

import java.io.IOException;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

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
	public List<UsuarioDTO> consultarUsuario(UsuarioDTO usuario)throws SQLException, NamingException, IOException{
		List<UsuarioDTO> usuarios = new ArrayList<>();
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
	        	 UsuarioDTO usuarioDTO = new UsuarioDTO();       	
	        	        	 
	        	 usuarioDTO.setScusuario(rs.getBigDecimal("sm_scusuario"));
	        	 usuarioDTO.setDsusuario(rs.getString("sm_dsusuario"));
	        	 usuarioDTO.setDscontrasena(rs.getString("sm_dscontrasena"));
	        	 usuarioDTO.setDsemail(rs.getString("sm_dsemail"));	        	     	 
	        	 usuarios.add(usuarioDTO);
        	 
                }
        }finally{
			closeResources(conn, call);
        }
		
		return usuarios;
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
                call.setBigDecimal(1, usuario.getScusuario());
            } else {
                call.setNull(1, java.sql.Types.NULL);
            }
				call.setBigDecimal(2, usuario.getScusuario());
				call.setString(3, usuario.getDsusuario());
				call.setString(4, usuario.getDscontrasena());
				call.setString(5, usuario.getDsemail());
						
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
