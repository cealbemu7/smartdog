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
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import co.com.smart.dog.infraestructure.dto.MensajeSQLDTO;
import co.com.smart.dog.infraestructure.dto.PropiedadDTO;
import co.com.smart.dog.infraestructure.dto.UsuarioDTO;
import co.com.smart.dog.persistence.entity.util.JDBCResourceManager;

public class PropiedadCaller extends JDBCResourceManager implements Serializable {
	
	private static final long serialVersionUID = 1L;

	Connection conn = null;
	CallableStatement call = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	
	public PropiedadCaller(String jdniData) {
		super(jdniData);
	}
	/**
	 * consultar Propiedad
	 * @param propiedad
	 * @return 
	 * @throws SQLException
	 * @throws NamingException
	 * @throws IOException
	 */

	public List<PropiedadDTO> consultarPropiedad(PropiedadDTO propiedad) throws SQLException, NamingException, IOException{
		List<PropiedadDTO> propiedadreturn  = new ArrayList<>();
		try {
			conn = getConnection();
			call = conn.prepareCall(getString("PropiedadCaller.fn_consultarpropiedad"));
			call.registerOutParameter(1,Types.OTHER);
			
            if (propiedad.getScpropiedad() != null) {          
            	call.setInt(2, propiedad.getScpropiedad().intValue());
	        } else {	        	
	            call.setNull(2, java.sql.Types.NULL);
	        }
            
            if (propiedad.getCopropiedad() != null) {            	
            	call.setString(3, propiedad.getCopropiedad());
	        } else {	        	
	            call.setNull(3, java.sql.Types.NULL);
	        }
            
            if (propiedad.getDspropiedad() != null) {            	
            	call.setString(4, propiedad.getDspropiedad());
	        } else {	        	
	            call.setNull(4, java.sql.Types.NULL);
	        }
            
			call.execute();
			rs = (ResultSet) call.getObject(1);
			while(rs.next()) {
				
				PropiedadDTO propiedadDTO = new PropiedadDTO();	
				UsuarioDTO usuario = new UsuarioDTO();
				
				propiedadDTO.setScpropiedad(rs.getBigDecimal("sm_scpropiedad"));
				propiedadDTO.setCopropiedad(rs.getString("sm_copropiedad"));
				propiedadDTO.setDspropiedad(rs.getString("sm_dspropiedad"));				
				usuario.setCousuario(rs.getString("sm_cousuario"));
				propiedadDTO.setUsuario(usuario);
				propiedadDTO.setDsdireccion(rs.getString("sm_dsdireccion"));
				
				propiedadreturn.add(propiedadDTO);
			}
			
		} finally {
			closeResources(conn, call);
		}
		return propiedadreturn;
	}

	/**
	 * grabar Propiedad
	 * @param propiedad
	 * @return
	 * @throws SQLException
	 * @throws NamingException
	 * @throws IOException
	 */
	 public PropiedadDTO grabarPropiedad(PropiedadDTO propiedad) throws SQLException, NamingException, IOException{
			try { 
				conn = getConnection();
				call = conn.prepareCall(getString("PropiedadCaller.fn_grabar_propiedad"));
				
				if (propiedad.getScpropiedad() != null) {
					call.setInt(1, propiedad.getScpropiedad().intValue());
	            } else {
	                call.setNull(1, java.sql.Types.NULL);
	            }
				
				call.setString(2,propiedad.getCopropiedad());
				call.setString(3,propiedad.getDspropiedad());
				call.setString(4,propiedad.getDsdireccion());
				call.setString(5,propiedad.getUsuario().getCousuario());
				call.setInt(6, propiedad.getCiudad().getScciudad().intValue());
				call.setInt(7, propiedad.getCiudad().getDepartamento().getScdepartamento().intValue());

				
		
				call.registerOutParameter(8, java.sql.Types.INTEGER);
				call.registerOutParameter(9, java.sql.Types.VARCHAR);
				
				call.executeUpdate();
				
				propiedad.setScpropiedad(new BigDecimal(call.getInt(8))); 
				MensajeSQLDTO msj = getResponseSQL(call.getString(9));
				propiedad.setCodigo(msj.getCodigo());
				propiedad.setDescripcion(msj.getDescripcion());
			}finally {
				closeResources(conn, call);
			}
			return propiedad;
	}
	 
	 
	 
	 /**
	  * Actulizar Propiedad
	  * @param propiedad
	  * @return
	  * @throws SQLException
	  * @throws NamingException
	  * @throws IOException
	  */

	public PropiedadDTO actulizarPropiedad(PropiedadDTO propiedad)throws SQLException, NamingException, IOException{
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

	/**
	 * eliminar Propiedad
	 * @param propiedad
	 * @return
	 * @throws SQLException
	 * @throws NamingException
	 * @throws IOException
	 */
	
	public PropiedadDTO eliminarPropiedad(PropiedadDTO propiedad) throws SQLException, NamingException, IOException{
		PropiedadDTO returnObject = new PropiedadDTO();
		try { 
			conn = getConnection();
			call = conn.prepareCall(getString("PropiedadCaller.fn_eliminar_propiedad"));
			
			if (propiedad.getScpropiedad() != null) {
				call.setInt(1, propiedad.getScpropiedad().intValue());
            } else {
                call.setNull(1, java.sql.Types.INTEGER);
            }
			call.registerOutParameter(2, java.sql.Types.INTEGER);
			call.registerOutParameter(3, java.sql.Types.VARCHAR);
			
			call.executeUpdate();
			
			propiedad.setScpropiedad(new BigDecimal(call.getInt(2))); 
			MensajeSQLDTO msj = getResponseSQL(call.getString(3));
			propiedad.setCodigo(msj.getCodigo());
			propiedad.setDescripcion(msj.getDescripcion());
            
        } catch (SQLException e) {
            e.printStackTrace(System.err);
            throw  e;
        }catch (Throwable thr) {
            thr.printStackTrace(System.err);
            throw  thr;
        
        } finally {
            closeResources(conn, call );
        }
        return returnObject;
			
	}

	
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
