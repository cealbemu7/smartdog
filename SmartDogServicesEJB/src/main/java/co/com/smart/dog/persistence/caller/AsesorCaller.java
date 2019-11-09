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

import co.com.smart.dog.infraestructure.dto.AsesorDTO;
import co.com.smart.dog.infraestructure.dto.CiudadDTO;
import co.com.smart.dog.infraestructure.dto.DatosMaestroDTO;
import co.com.smart.dog.infraestructure.dto.DepartamentoDTO;
import co.com.smart.dog.infraestructure.dto.EmpresaDTO;
import co.com.smart.dog.infraestructure.dto.MaestroDTO;
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
			call = conn.prepareCall(getString("AsesorCaller.fn_grabar_asesor"));
			
			if (asesor.getScasesor() != null) {
				call.setInt(1, asesor.getScasesor().intValue());
            } else {
                call.setNull(1, java.sql.Types.INTEGER);
            }
			
			
			call.setObject(2, asesor.getTipoidentificacion());
			call.setString(3, asesor.getCoidentificacion());
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
			call.setObject(16, asesor.getSexo());
	
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

		public List<AsesorDTO> consultarAsesor(AsesorDTO asesorlist) throws SQLException, NamingException, IOException{
			List<AsesorDTO> list = new ArrayList<>();
			try {
				conn = getConnection();
				call = conn.prepareCall(getString("AsesorCaller.fn_consultarasesor"));
				call.registerOutParameter(1,Types.OTHER);
				
	            if (asesorlist.getScasesor() != null) {
	            	call.setBigDecimal(2, asesorlist.getScasesor());
		        } else {
		            call.setNull(2, java.sql.Types.NULL);
		        }
	            if (asesorlist.getTipoidentificacion() != null) {
	            	call.setObject(3,asesorlist.getTipoidentificacion());
	            }	else {
		            call.setNull(3, java.sql.Types.NULL);
	            }
	            if (asesorlist.getCoidentificacion()!= null) {
	            	
				call.setString(4,asesorlist.getCoidentificacion());
	            }else {
		            call.setNull(4, java.sql.Types.NULL);
		        }
	            if (asesorlist.getDspnombre()!= null) {
				call.setString(5,asesorlist.getDspnombre());
	            }else {
		            call.setNull(5, java.sql.Types.NULL);
		        }
	            
	            if(asesorlist.getDspapellido()!= null) {
				call.setString(6,asesorlist.getDspapellido());
	            }else {
		            call.setNull(6, java.sql.Types.NULL);
	            }
	            rs = call.executeQuery();
	           if (rs!=null){	            		
				while(rs.next()) {
					AsesorDTO AsesorDTO = new AsesorDTO();
					DepartamentoDTO departamentoDTO = new DepartamentoDTO();
					CiudadDTO ciudadDTO = new CiudadDTO();
					DatosMaestroDTO maestroDTO = new DatosMaestroDTO();
					EmpresaDTO empresaDTO = new EmpresaDTO();
					
					maestroDTO.setScdatmaestro(rs.getBigDecimal("sm_sctipoidentificacion"));
					AsesorDTO.setCoidentificacion(rs.getString("sm_dsidentificacion"));
					AsesorDTO.setDspnombre(rs.getString("sm_dspnombre"));
					AsesorDTO.setDssnombre(rs.getString("sm_dssnombre"));
					AsesorDTO.setDspapellido(rs.getString("sm_dspapeldo"));
					AsesorDTO.setDssapellido(rs.getString("sm_dssapellido"));
					ciudadDTO.setScciudad(rs.getBigDecimal("sm_scciudad"));
					AsesorDTO.setFhnacimineto(rs.getString("sm_fhnacimiento"));
					ciudadDTO.setDepartamento(departamentoDTO);
					maestroDTO.setScdatmaestro(rs.getBigDecimal("sm_scsexo"));
					
					empresaDTO.setScempresa(rs.getBigDecimal("sm_scempresa"));
					
					AsesorDTO.setDscelular(rs.getString("sm_dscelular"));
					AsesorDTO.setDsdireccion(rs.getString("sm_dsdireccion"));
					AsesorDTO.setDstelefono(rs.getString("sm_dstelefono"));
					AsesorDTO.setDsemail(rs.getString("sm_dsemail"));
					AsesorDTO.setCiudad(ciudadDTO);
					AsesorDTO.setSexo(maestroDTO);
					AsesorDTO.setTipoidentificacion(maestroDTO);
					AsesorDTO.setEmpresa(empresaDTO);
					list.add(AsesorDTO);
										
				}
	           }
			} finally {
				closeResources(conn, call);
			}
			return list;
		}

		public AsesorDTO eliminarAsesor(AsesorDTO asesor) throws SQLException, NamingException, IOException{
			AsesorDTO returnObject = new AsesorDTO();
		 	try {
	             conn = getConnection();
	             call = conn.prepareCall("AsesorCaller.fn_eliminar_asesor");
	             if (asesor.getScasesor() != null && asesor.getScasesor()!=null) {
	            	 call.setBigDecimal(1, asesor.getScasesor());
	             } else {
	                 call.setNull(1, java.sql.Types.INTEGER);
	             }   
	           
	 			call.registerOutParameter(2, java.sql.Types.INTEGER);
	 			call.registerOutParameter(3, java.sql.Types.VARCHAR);
	            call.executeUpdate();
	            asesor.setScasesor(new BigDecimal(call.getInt(2))); 
				MensajeSQLDTO msj = getResponseSQL(call.getString(3));
				asesor.setCodigo(msj.getCodigo());
				asesor.setDescripcion(msj.getDescripcion());
	             
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
}
