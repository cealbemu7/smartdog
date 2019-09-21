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

import co.com.smart.dog.infraestructure.dto.CiudadDTO;
import co.com.smart.dog.infraestructure.dto.DepartamentoDTO;
import co.com.smart.dog.infraestructure.dto.MensajeSQLDTO;
import co.com.smart.dog.persistence.entity.util.JDBCResourceManager;

public class CiudadCaller extends JDBCResourceManager implements Serializable{

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
	public CiudadCaller(String jdniData) {
		super(jdniData);
	}
	public List<CiudadDTO> consultarCiudad (CiudadDTO filtro)throws SQLException, NamingException, IOException{

		List<CiudadDTO> ciudad = new ArrayList<>();
		try {
			conn = getConnection();
			call = conn.prepareCall(getString("CiudadCaller.fn_consultarCiudad"));
			call.registerOutParameter(1, Types.OTHER);
			call.setInt(2, filtro.getDepartamento().getPais().getScpais().intValue());
			
			if(filtro.getCociudad() != null){
				call.setString(3,filtro.getCociudad());
			}else{
				call.setNull(3, java.sql.Types.NULL);
			}
			
			call.execute();
			rs = (ResultSet) call.getObject(1);
         
	         while(rs.next()){
	        	 CiudadDTO ciudadDTO = new CiudadDTO();
	        	 DepartamentoDTO departamentoDTO = new DepartamentoDTO();
	        	        	 
	        	 ciudadDTO.setScciudad(rs.getBigDecimal("sp_scciudad"));
	        	 ciudadDTO.setCociudad(rs.getString("sp_cociudad"));
	        	 ciudadDTO.setDsciudad(rs.getString("sp_dsciudad"));
	        	 departamentoDTO.setScdepartamento(rs.getBigDecimal("sp_scdepartamento"));
	        	 ciudadDTO.setDepartamento(departamentoDTO);      	 
	        	 ciudad.add(ciudadDTO);
        	 
                }
        }finally{
			closeResources(conn, call);
        }
		
		return ciudad;
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
