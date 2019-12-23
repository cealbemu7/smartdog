package co.com.smart.dog.persistence.caller;

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

import co.com.smart.dog.infraestructure.dto.CiudadDTO;
import co.com.smart.dog.infraestructure.dto.DatosMaestroDTO;
import co.com.smart.dog.infraestructure.dto.DepartamentoDTO;
import co.com.smart.dog.infraestructure.dto.EmpresaDTO;
import co.com.smart.dog.infraestructure.dto.EntidadDTO;
import co.com.smart.dog.infraestructure.dto.LoginDTO;
import co.com.smart.dog.infraestructure.dto.MensajeSQLDTO;
import co.com.smart.dog.infraestructure.dto.MenuDTO;
import co.com.smart.dog.infraestructure.dto.SmartExcepcionSerializada;
import co.com.smart.dog.infraestructure.dto.TercerosDTO;
import co.com.smart.dog.persistence.entity.util.JDBCResourceManager;

public class LoginCaller extends JDBCResourceManager implements Serializable {

	public LoginCaller(String jdniData) {
		super(jdniData);

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Connection conn = null;
	CallableStatement call = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	
	/**
	 * Método Útilizado para validar la existencia del usuario
	 * @param login
	 * @return
	 * @throws NamingException 
	 * @throws SQLException 
	 * @throws SmartExcepcionSerializada
	 */
	public boolean getUserValidate(LoginDTO login) throws SQLException, NamingException{
        boolean response = Boolean.FALSE;
        try{
			conn = getConnection();
			call = conn.prepareCall("{call getUserValidate(?, ?, ?)}");
			call.setString(1, login.getApplication());
			if(!"".equals(login.getUser()) && login.getUser() != null)
			    call.setString(2, login.getUser());
			else
				call.setNull(2, Types.VARCHAR);	
			call.setString(3, login.getPassword());
			
			rs = call.executeQuery();
            
            if (rs!=null){
		         while(rs.next()){
		        	 response = Boolean.TRUE;
		        	 break;
                }
            }
        }finally{
            closeResources(conn,call,rs);
        }
        return response;
	}


	/**
	 * Método Útilizado para Consultalos datos maestros apartir del maerstro
	 * filtrado
	 * 
	 * @param filtros
	 * @return
	 * @throws NamingException 
	 */
	public List<MenuDTO> getResource(LoginDTO filtros) throws SQLException, NamingException {
		List<MenuDTO> returnResources = new ArrayList<>();
		try {
			conn = getConnection();
			call = conn.prepareCall("{call consultarResources(?,?,?)}");

			call.setBigDecimal(1, new BigDecimal(filtros.getEntity().getEntity()));
			call.setString(2, filtros.getUser());
			call.setString(3, filtros.getApplication());
			rs = call.executeQuery();

			if (rs != null) {
				while (rs.next()) {
					if (rs.getInt("ALLOWED") == 0) { // recurso permitido
						MenuDTO resource = new MenuDTO();
						resource.setApplication(rs.getString("application"));
						resource.setIdemenu(rs.getLong("idemenu"));
						resource.setIdemenufather(rs.getLong("idemenufather"));
						resource.setContextPath(rs.getString("contextpath"));
						resource.setDescription(rs.getString("description"));
						resource.setUri(rs.getString("uri"));
						returnResources.add(resource);
					}
				}
			}

		} finally {
			closeResources(conn, call, rs);
		}

		return returnResources;

	}

	/**
	 * Método Útilizado para consultar las entidades del usuario en session
	 * @param getEntitiesUser
	 * @return
	 * @throws NamingException 
	 * @throws SQLException 
	 * @throws SmartExcepcionSerializada
	 */
	public List<EntidadDTO> getEntitiesUser(LoginDTO filtros) throws SQLException, NamingException {
		List<EntidadDTO> returnResources = new ArrayList<>();
		try {
			conn = getConnection();
			call = conn.prepareCall("{call consultarEntities(?)}");

			call.setString(1, filtros.getUser());
			rs = call.executeQuery();

			if (rs != null) {
				while (rs.next()) {
					EntidadDTO resource = new EntidadDTO();
						resource.setEntity(rs.getLong("entity"));
						resource.setDsentity(rs.getString("dsentity"));
						resource.setDsnit(rs.getString("dsnit"));
						resource.setVerify(rs.getString("verify"));
						resource.setNmMatriculaMercantil(rs.getString("sm_nmmatriculamercantil"));
						returnResources.add(resource);
				}
			}

		} finally {
			closeResources(conn, call, rs);
		}

		return returnResources;

	}
	
	/**
	 * Método Útilizado para consultar la empresa por entidad
	 * @param entidad
	 * @return
	 * @throws NamingException 
	 * @throws SQLException 
	 * @throws SmartExcepcionSerializada
	 */
	public EmpresaDTO getEmpresaEntidad(EntidadDTO entidad) throws SQLException, NamingException{
		EmpresaDTO returnEmpresa = new EmpresaDTO();
        
        try{
			conn = getConnection();
			call = conn.prepareCall("{call consultarEmpresas(?, ?, ?)}");
			call.setString(1, entidad.getDsnit());
			call.setInt(
					2,
					entidad.getVerify() != null
							&& entidad.getVerify().length() > 0 ? Integer
							.valueOf(entidad.getVerify()) : 0);
			if(entidad.getNmMatriculaMercantil() != null && !entidad.getNmMatriculaMercantil().isEmpty()){
				call.setString(3,entidad.getNmMatriculaMercantil());
			}else{
				call.setNull(3, Types.VARCHAR);
			}
			
			rs = call.executeQuery();
            
            if (rs!=null){
		         while(rs.next()){
		        	 DepartamentoDTO departamentoDTO = new DepartamentoDTO();
		        	 CiudadDTO ciudadDTO = new CiudadDTO();
		        	 DatosMaestroDTO tipoempresaDTO = new DatosMaestroDTO();
		        	 DatosMaestroDTO tipodocumentorepresentanteDTO = new DatosMaestroDTO();
		        	 TercerosDTO tercerosDTO =new TercerosDTO();
		        	 DatosMaestroDTO tipofacturacionDTO = new DatosMaestroDTO();
		        	 
		        	 tipoempresaDTO.setDsdatmaestro(rs.getString("tipoempresa"));
		        	 
		        	 tipodocumentorepresentanteDTO.setScdatmaestro(rs.getBigDecimal("sm_sctipodocumento"));
		        	 
		        	 tercerosDTO.setSctercero(rs.getBigDecimal("sm_screpresentantelegal"));
		        	 tercerosDTO.setCoidentificacion(rs.getString("sm_coidentificacion"));
		        	 tercerosDTO.setDsrazonsocial(rs.getString("representantelegal").trim());
		        	 tercerosDTO.setTipodocumento(tipodocumentorepresentanteDTO);
		        	 
		        	 departamentoDTO.setScdepartamento(rs.getBigDecimal("sm_scdepartamento"));
		        	 departamentoDTO.setDsdepartamento(rs.getString("sm_dsdepartamento"));
		        	 
		        	 ciudadDTO.setScciudad(rs.getBigDecimal("sm_scciudad"));
		        	 ciudadDTO.setDsciudad(rs.getString("sm_dsciudad"));
		        	 ciudadDTO.setDepartamento(departamentoDTO);
		        	 
		        	 tipofacturacionDTO.setScdatmaestro(rs.getBigDecimal("sm_sctipofacturacion"));
				        	 
		        	 returnEmpresa.setFhmodificacion(rs.getDate("sm_fhmodificacion"));
		        	 returnEmpresa.setFhingreso(rs.getDate("sm_fhingreso"));
		        	 returnEmpresa.setFhretiro(rs.getDate("sm_fhretiro"));
		        	 returnEmpresa.setCousuario(rs.getString("sm_cousuario"));
		        	 returnEmpresa.setScempresa(rs.getBigDecimal("sm_scempresa"));
		        	 returnEmpresa.setNitempresa(rs.getString("sm_coempresa"));
		        	 returnEmpresa.setDsrazonsocial(rs.getString("sm_dsempresa"));
		        	 returnEmpresa.setDsdireccion(rs.getString("sm_dsdireccion"));
		        	 returnEmpresa.setDstelefono(rs.getString("sm_dstelefono"));
		        	 returnEmpresa.setDsemail(rs.getString("sm_dsemail"));
		        	 
		        	 returnEmpresa.setCiudad(ciudadDTO); 
	        
                }
            }
        }finally{
            closeResources(conn,call,rs);
        }
        return returnEmpresa;
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
