package co.com.smart.dog.persistence.caller;

import java.io.IOException;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import co.com.smart.dog.infraestructure.dto.CiudadDTO;
import co.com.smart.dog.infraestructure.dto.DepartamentoDTO;

import co.com.smart.dog.infraestructure.dto.EmpresaDTO;
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
	public EmpresaCaller(String jdniConnection) {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 */
	public EmpresaDTO grabarEmpresa(EmpresaDTO empresa) throws SQLException, NamingException, IOException{
		try { 
			conn = getConnection();
			call = conn.prepareCall(getString("EmpresaCaller.fn_grabar_empresa"));
			if (empresa.getScempresa() != null) {
                call.setBigDecimal(1, empresa.getScempresa());
            } else {
                call.setNull(1, java.sql.Types.NULL);
            }
				call.setBigDecimal(2, empresa.getScempresa());
				call.setString(3, empresa.getNitempresa());
				call.setString(4, empresa.getDsrazonsocial());
				call.setString(5, empresa.getDsemail());
				call.setString(6, empresa.getDstelefono());
				call.setString(7, empresa.getCousuario());
				call.setBigDecimal(8, empresa.getCiudad().getScciudad());
				call.setBigDecimal(9, empresa.getCiudad().getDepartamento().getScdepartamento());
				
			
		}finally {
			closeResources(conn, call);
		}
		return empresa;
	}
	/**
	 * 
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
				empresaDTO.setCousuario(rs.getNString("sm_cousuario"));
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


}
