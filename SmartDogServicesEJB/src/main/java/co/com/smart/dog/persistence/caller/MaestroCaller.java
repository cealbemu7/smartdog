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

import co.com.smart.dog.infraestructure.dto.MaestroDTO;
import co.com.smart.dog.infraestructure.dto.MensajeSQLDTO;
import co.com.smart.dog.persistence.entity.util.JDBCResourceManager;

public class MaestroCaller extends JDBCResourceManager implements Serializable{

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
	public MaestroCaller(String jdniData) {
		super(jdniData);
	}
	public List<MaestroDTO> listarMaestros(MaestroDTO filtro) throws SQLException, NamingException, IOException {

		List<MaestroDTO> listmaestro = new ArrayList<>();
        
        try {
        	System.err.println("Llego por la caller");
            conn = getConnection();
            call = conn.prepareCall(getString("MaestroCaller.fn_listarMaestros"));
			call.registerOutParameter(1, Types.OTHER);      
            call.setString(2, filtro.getComaestro());
            call.execute();
			rs = (ResultSet) call.getObject(1);
                while(rs.next()){
                	MaestroDTO maestro = new MaestroDTO();
                	maestro.setScmaestro(rs.getBigDecimal("sm_scmaestro"));
                	maestro.setComaestro(rs.getString("sm_comaestro"));
                	maestro.setDsmaestro(rs.getString("sm_dsmaestro"));
                	maestro.setCousuario(rs.getString("sm_cousuario"));
                	listmaestro.add(maestro);
                }
        } finally {
            closeResources(conn, call, rs);
        }
        return listmaestro;
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
