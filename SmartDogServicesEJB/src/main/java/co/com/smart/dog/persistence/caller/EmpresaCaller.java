package co.com.smart.dog.persistence.caller;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import co.com.smart.dog.infraestructure.dto.CiudadDTO;
import co.com.smart.dog.infraestructure.dto.DepartamentoDTO;
import co.com.smart.dog.infraestructure.dto.EmpresaDTO;
import co.com.smart.dog.infraestructure.dto.MensajeSQLDTO;
import co.com.smart.dog.persistence.entity.util.JDBCResourceManager;

public class EmpresaCaller extends JDBCResourceManager implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Connection conn = null;
	CallableStatement call = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	
	public EmpresaCaller(String jdniData) {
		super(jdniData);
	}
	/**
	 * grabar Empresa
	 * @param empresa
	 * @return
	 * @throws Throwable
	 */
	public EmpresaDTO grabarEmpresa(EmpresaDTO empresa) throws SQLException, NamingException, IOException{
		try { 
			conn = getConnection();
			call = conn.prepareCall(getString("EmpresaCaller.fn_grabar_empresa"));
			
			if (empresa.getScempresa() != null) {
				call.setInt(1, empresa.getScempresa().intValue());
            } else {
                call.setNull(1, java.sql.Types.INTEGER);
            }
				
			call.setString(2, empresa.getNitempresa());
			call.setString(3, empresa.getDsrazonsocial());
			call.setString(4, empresa.getDsemail());
			call.setString(5, empresa.getDstelefono());
			call.setString(6, empresa.getCousuario());
			call.setInt(7, empresa.getCiudad().getScciudad().intValue());
			call.setInt(8, empresa.getCiudad().getDepartamento().getScdepartamento().intValue());		
			call.setString(9, empresa.getDsdireccion());
			
			call.registerOutParameter(10, java.sql.Types.INTEGER);
			call.registerOutParameter(11, java.sql.Types.VARCHAR);
			
			call.executeUpdate();
			
			empresa.setScempresa(new BigDecimal(call.getInt(10))); 
			MensajeSQLDTO msj = getResponseSQL(call.getString(11));
			empresa.setCodigo(msj.getCodigo());
			empresa.setDescripcion(msj.getDescripcion());
		}finally {
			closeResources(conn, call);
		}
		return empresa;
	}
	
	/**
	 * Eliminar Empresa
	 * @param empresa
	 * @return
	 * @throws Throwable
	 */
	 public EmpresaDTO eliminarEmpresa(EmpresaDTO Empresa)  throws NamingException, SQLException {	
		EmpresaDTO returnObject = new EmpresaDTO();
	 	try {
             conn = getConnection();
             call = conn.prepareCall("EmpresaCaller.fn_eliminar_empresa");
             if (Empresa.getScempresa() != null && Empresa.getScempresa()!=null) {
            	 call.setBigDecimal(1, Empresa.getScempresa());
             } else {
                 call.setNull(1, java.sql.Types.INTEGER);
             }   
           
 			call.registerOutParameter(2, java.sql.Types.INTEGER);
 			call.registerOutParameter(3, java.sql.Types.VARCHAR);
            call.executeUpdate();
			Empresa.setScempresa(new BigDecimal(call.getInt(2))); 
			MensajeSQLDTO msj = getResponseSQL(call.getString(3));
			Empresa.setCodigo(msj.getCodigo());
			Empresa.setDescripcion(msj.getDescripcion());
             
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
		/**
		 * consultar Empresa
		 * @param empresa
		 * @return
		 * @throws Throwable
		 */
	public List<EmpresaDTO> consultarEmpresa (EmpresaDTO filtros) throws SQLException, NamingException, IOException{
		List<EmpresaDTO> empresalist = new ArrayList<>();
		try {
			conn = getConnection();
			call = conn.prepareCall(getString("EmpresaCaller.fn_consultarempresa"));
			call.registerOutParameter(1,Types.OTHER);
			call.setString(2,filtros.getNitempresa());
			call.execute();
			rs = (ResultSet) call.getObject(1);
			while(rs.next()) {
				EmpresaDTO empresaDTO = new EmpresaDTO();
				DepartamentoDTO departamentoDTO = new DepartamentoDTO();
				CiudadDTO ciudadDTO = new CiudadDTO();
				
				empresaDTO.setNitempresa(rs.getString("sm_nitempresa"));
				empresaDTO.setDsrazonsocial(rs.getString("sm_dsrazonsocial"));
				empresaDTO.setCousuario(rs.getString("sm_cousuario"));
				empresaDTO.setFhingreso(rs.getDate("sm_fhingreso"));
				empresaDTO.setFhmodificacion(rs.getDate("sm_fhmodificacion"));
				empresaDTO.setFhretiro(rs.getDate("sm_fhretiro"));
				departamentoDTO.setScdepartamento(rs.getBigDecimal("sm_scdepartamento"));
				ciudadDTO.setScciudad(rs.getBigDecimal("sm_scciudad"));
				ciudadDTO.setDepartamento(departamentoDTO);
				empresaDTO.setDsdireccion(rs.getString("sm_dsdireccion"));
				empresaDTO.setDstelefono(rs.getString("sm_dstelefono"));
				empresaDTO.setDsemail(rs.getString("sm_dsemail"));
				empresaDTO.setCiudad(ciudadDTO);
	
				empresalist.add(empresaDTO);
			}
			
		} finally {
			closeResources(conn, call);
		}
		return empresalist;
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
