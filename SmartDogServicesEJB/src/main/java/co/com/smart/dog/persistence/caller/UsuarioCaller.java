package co.com.smart.dog.persistence.caller;

import java.io.IOException;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.ArrayList;

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
	public List<UsuarioDTO> consultarUsuario(UsuarioDTO usuario)throws SQLException, NamingException, IOException{
		List<UsuarioDTO> usuarios = new ArrayList<>();
		try {
			conn = getConnection();
			call = conn.prepareCall(getString("UsuarioCaller.fn_consultarusuario"));
			call.registerOutParameter(1, Types.OTHER);
			call.setString(2, usuario.getDsemail());
			
			if(usuario.getDsemail() != null){
				call.setString(3,usuario.getDsemail());
			}else{
				call.setNull(3, java.sql.Types.NULL);
			}
			
			call.execute();
			rs = (ResultSet) call.getObject(1);
         
	         while(rs.next()){
	        	 UsuarioDTO UsuarioDTO = new UsuarioDTO();       	
	        	        	 
	        	 UsuarioDTO.setScusuario(rs.getBigDecimal("sm_scusuario"));
	        	 UsuarioDTO.setDsusuario(rs.getString("sm_dsusuario"));
	        	 UsuarioDTO.setDscontrasena(rs.getString("sm_dscontrasena"));
	        	 UsuarioDTO.setDsemail(rs.getString("sm_dsemail"));	        	     	 
	        	 usuarios.add(UsuarioDTO);
        	 
                }
        }finally{
			closeResources(conn, call);
        }
		
		return usuarios;
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
