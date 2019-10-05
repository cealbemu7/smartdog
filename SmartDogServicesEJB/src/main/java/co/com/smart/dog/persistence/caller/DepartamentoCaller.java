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

import co.com.smart.dog.infraestructure.dto.DepartamentoDTO;
import co.com.smart.dog.infraestructure.dto.MensajeSQLDTO;
import co.com.smart.dog.infraestructure.dto.PaisDTO;
import co.com.smart.dog.persistence.entity.util.JDBCResourceManager;

public class DepartamentoCaller extends JDBCResourceManager implements Serializable{

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
	public DepartamentoCaller(String jdniData) {
		super(jdniData);
	}

	public List<DepartamentoDTO> consultarDepartamento(DepartamentoDTO filtro)
			throws SQLException, NamingException, IOException {

		List<DepartamentoDTO> departamento = new ArrayList<>();
		try {
			conn = getConnection();
			call = conn.prepareCall(getString("DepartamentoCaller.fn_consultardepartamento"));
			call.registerOutParameter(1, Types.OTHER);
			call.setInt(2,filtro.getPais().getScpais().intValue());
			call.setString(3, filtro.getCodepartamento());
			call.execute();
			rs = (ResultSet) call.getObject(1);
			while (rs.next()) {
				DepartamentoDTO departamentoDTO = new DepartamentoDTO();
				PaisDTO paisDTO = new PaisDTO();
																	
				departamentoDTO.setScdepartamento(rs.getBigDecimal("sm_scdepartamento"));
				departamentoDTO.setCodepartamento(rs.getString("sm_codepartamento"));
				departamentoDTO.setDsdepartamento(rs.getString("sm_dsdepartamento"));
				paisDTO.setScpais(rs.getBigDecimal("sm_scpais"));
				departamentoDTO.setPais(paisDTO);
				departamentoDTO.setCousuario(rs.getString("sm_cousuario"));
				departamentoDTO.setFhingreso(rs.getDate("sm_fhingreso"));
				departamentoDTO.setFhmodificacion(rs.getDate("sm_fhmodificacion"));
				departamentoDTO.setFhretiro(rs.getDate("sm_fhretiro"));
				departamento.add(departamentoDTO);

			}
		} finally {
			closeResources(conn, call);
		}

		return departamento;
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
