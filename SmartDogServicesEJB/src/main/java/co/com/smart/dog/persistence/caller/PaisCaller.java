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
import co.com.smart.dog.infraestructure.dto.PaisDTO;
import co.com.smart.dog.persistence.entity.util.JDBCResourceManager;

public class PaisCaller extends JDBCResourceManager implements Serializable{

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
	public PaisCaller(String jdniData) {
		super(jdniData);
	}
	
	public List<PaisDTO>consultarPais(PaisDTO filtros)throws SQLException, NamingException, IOException{
		List<PaisDTO>pais = new ArrayList<PaisDTO>();
		try {
			conn = getConnection();
			call = conn.prepareCall(getString("PaisCaller.fn_consultarPais"));
			call.registerOutParameter(1,Types.OTHER);
			call.setString(2,filtros.getCopais());
			call.execute();
			rs = (ResultSet) call.getObject(1);
			while (rs.next()) {
				PaisDTO paisDTO = new PaisDTO();
				paisDTO.setScpais(rs.getBigDecimal("sp_scpais"));
				paisDTO.setCopais(rs.getString("sp_copais"));
				paisDTO.setDspais(rs.getString("sp_dspais"));
				paisDTO.setCousuario(rs.getString("sp_cousuario"));
				paisDTO.setFhingreso(rs.getDate("sp_fhingreso"));
				paisDTO.setFhmodificacion(rs.getDate("sp_fhmodificacion"));
				paisDTO.setFhretiro(rs.getDate("sp_fhretiro"));
				pais.add(paisDTO);
				
				
			}
			
		} finally {
			closeResources(conn, call);
		}
		return pais;
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
