package co.com.smart.dog.persistence.caller;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import co.com.smart.dog.infraestructure.dto.AsesorDTO;
import co.com.smart.dog.infraestructure.dto.CiudadDTO;
import co.com.smart.dog.infraestructure.dto.DatosMaestroDTO;
import co.com.smart.dog.infraestructure.dto.DepartamentoDTO;
import co.com.smart.dog.infraestructure.dto.EmpresaDTO;
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
			
			
			call.setInt(2, asesor.getTipoidentificacion().getScdatmaestro().intValue());
			call.setString(3, asesor.getCoidentificacion());
			call.setString(4,asesor.getDspnombre());
			call.setString(5,asesor.getDssnombre());
			call.setString(6,asesor.getDspapellido());
			call.setString(7,asesor.getDssapellido());
			call.setString(8, asesor.getDstelefono());
			call.setString(9,asesor.getDscelular());
			call.setString(10, asesor.getDsemail());
			call.setString(11,asesor.getFhnacimiento());
			call.setString(12, asesor.getCousuario());
			call.setString(13, asesor.getDsdireccion());
			call.setInt(14, asesor.getSexo().getScdatmaestro().intValue());
			call.setInt(15, asesor.getCiudad().getScciudad().intValue());
			call.setInt(16, asesor.getCiudad().getDepartamento().getScdepartamento().intValue());
			call.setInt(17,asesor.getEmpresa().getScempresa().intValue());
			
			call.registerOutParameter(18, java.sql.Types.INTEGER);
			call.registerOutParameter(19, java.sql.Types.VARCHAR);
			
			call.executeUpdate();
			
			
			asesor.setScasesor(new BigDecimal(call.getInt(18))); 
			MensajeSQLDTO msj = getResponseSQL(call.getString(19));
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
			List<AsesorDTO> listAsesor = new ArrayList<>();
			try {
				
				conn = getConnection();
				call = conn.prepareCall(getString("AsesorCaller.fn_consultarasesor"));
				call.registerOutParameter(1,Types.OTHER);


				
				
				
				
				
				
	            if (asesorlist.getScasesor() != null) {
	            	System.out.println("entro");
	            	call.setInt(2, asesorlist.getScasesor().intValue());
		        } else {
		        	System.out.println("uno");
		            call.setNull(2, java.sql.Types.NULL);
		        }
	            
	            if (asesorlist.getTipoidentificacion() != null) {
	            	call.setBigDecimal(3,asesorlist.getTipoidentificacion().getScdatmaestro());
	            }	else {
	            	System.out.println("dos");
		            call.setNull(3, java.sql.Types.NULL);
	            }
	            
	            if (asesorlist.getCoidentificacion()!= null) {
	            	call.setString(4,asesorlist.getCoidentificacion());
	            }else {
	            	System.out.println("tres");
		            call.setNull(4, java.sql.Types.NULL);
		        }
	            
	            if (asesorlist.getDspnombre()!= null) {
	            	call.setString(5,asesorlist.getDspnombre());
	            }else {
	            	System.out.println("cuatro");
		            call.setNull(5, java.sql.Types.NULL);
		        }
	            
	            
	            if(asesorlist.getDspapellido()!= null) {
	            	call.setString(6,asesorlist.getDspapellido());
	            }else {
	            	System.out.println("cinco");
		            call.setNull(6, java.sql.Types.NULL);
	            }
	            
				call.execute();
				rs = (ResultSet) call.getObject(1);

	           if (rs!=null){	            		
				while(rs.next()) {
					AsesorDTO asesorDTO = new AsesorDTO();
					DepartamentoDTO departamentoDTO = new DepartamentoDTO();
					CiudadDTO ciudadDTO = new CiudadDTO();
					DatosMaestroDTO maestroDTO = new DatosMaestroDTO();
					EmpresaDTO empresaDTO = new EmpresaDTO();
					
					maestroDTO.setScdatmaestro(rs.getBigDecimal("sm_sctipoidentificacion"));
					asesorDTO.setCoidentificacion(rs.getString("sm_dsidentificacion"));
					asesorDTO.setDspnombre(rs.getString("sm_dspnombre"));
					asesorDTO.setDssnombre(rs.getString("sm_dssnombre"));
					asesorDTO.setDspapellido(rs.getString("sm_dspapellido"));
					asesorDTO.setDssapellido(rs.getString("sm_dssapellido"));
					//ciudadDTO.setScciudad(rs.getBigDecimal("sm_scciudad"));
					asesorDTO.setFhnacimiento(rs.getString("sm_fhnacimiento"));
					//ciudadDTO.setDepartamento(departamentoDTO);
					maestroDTO.setScdatmaestro(rs.getBigDecimal("sm_scsexo"));
					
					empresaDTO.setScempresa(rs.getBigDecimal("sm_scempresa"));
					
					asesorDTO.setDscelular(rs.getString("sm_dscelular"));
					asesorDTO.setDsdireccion(rs.getString("sm_dsdireccion"));
					asesorDTO.setDstelefono(rs.getString("sm_dstelefono"));
					asesorDTO.setDsemail(rs.getString("sm_dsemail"));
					//asesorDTO.setCiudad(ciudadDTO);
					asesorDTO.setSexo(maestroDTO);
					asesorDTO.setTipoidentificacion(maestroDTO);
					asesorDTO.setEmpresa(empresaDTO);
					listAsesor.add(asesorDTO);
										
				}
	           }
			}catch (SQLException e) {
	             e.printStackTrace(System.err);
	             throw  e;
	         }catch (Throwable thr) {
	             thr.printStackTrace(System.err);
	             throw  thr;
	         }  finally {
				closeResources(conn, call);
			}
			return listAsesor;
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
