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

	public List<PropiedadDTO> consultarEmpresa(PropiedadDTO propiedad) throws SQLException, NamingException, IOException{
		List<PropiedadDTO> propiedadreturn  = new ArrayList<>();
		try {
			conn = getConnection();
			call = conn.prepareCall(getString("PropiedadCaller.fn_consultarpropiedad"));
			call.registerOutParameter(1,Types.OTHER);
			call.setString(2,propiedad.getCopropiedad());
			call.execute();
			rs = (ResultSet) call.getObject(1);
			while(rs.next()) {
				PropiedadDTO propiedadDTO = new PropiedadDTO();				
				propiedadDTO.setCopropiedad(rs.getString("sm_copropiedad"));
				propiedadDTO.setDspropiedad(rs.getString("sm_dsprpopiedad"));
				propiedadDTO.setFhingreso(rs.getDate("sm_fhingreso"));
				propiedadDTO.setFhmodificacion(rs.getDate("sm_fhmodificacion"));
				propiedadDTO.setFhretiro(rs.getDate("sm_fhretiro"));
				propiedadDTO.setCousuario(rs.getString("sm_cousuario"));
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
	                call.setNull(1, java.sql.Types.INTEGER);
	            }
				
				call.setString(2,propiedad.getCopropiedad());
				call.setString(3,propiedad.getDspropiedad());
				call.setString(4,propiedad.getCousuario());
				call.setString(5,propiedad.getDsdireccion());
		
				call.registerOutParameter(6, java.sql.Types.INTEGER);
				call.registerOutParameter(7, java.sql.Types.VARCHAR);
				
				call.executeUpdate();
				
				propiedad.setScpropiedad(new BigDecimal(call.getInt(6))); 
				MensajeSQLDTO msj = getResponseSQL(call.getString(7));
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
