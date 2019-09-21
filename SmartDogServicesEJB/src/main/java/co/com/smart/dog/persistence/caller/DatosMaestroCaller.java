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

import co.com.smart.dog.infraestructure.dto.DatosMaestroDTO;
import co.com.smart.dog.infraestructure.dto.MaestroDTO;
import co.com.smart.dog.infraestructure.dto.MensajeSQLDTO;
import co.com.smart.dog.persistence.entity.util.JDBCResourceManager;

public class DatosMaestroCaller extends JDBCResourceManager implements Serializable{

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
	public DatosMaestroCaller(String jdniData) {
		super(jdniData);
	}
	public List<DatosMaestroDTO> consultarDatosMestros(DatosMaestroDTO filtro) throws SQLException, NamingException, IOException {

        List<DatosMaestroDTO> datosMaestros = new ArrayList<>();
        
        try {
            conn = getConnection();
            call = conn.prepareCall(getString("DatosMaestroCaller.fn_consultarDatosMestros"));
			call.registerOutParameter(1, Types.OTHER);     
            call.setString(2, filtro.getMaestro().getComaestro());
            call.execute();
			rs = (ResultSet) call.getObject(1);
            
                while(rs.next()){
                	DatosMaestroDTO datosmaestro = new DatosMaestroDTO();
                	MaestroDTO maestro = new MaestroDTO();
                    datosmaestro.setScdatmaestro(rs.getBigDecimal("sp_scdatmaestro"));
                    datosmaestro.setCodatmaestro(rs.getString("sp_codatmaestro"));
                    datosmaestro.setDsdatmaestro(rs.getString("sp_dsdatmaestro"));
                    datosmaestro.setDsvalor(rs.getString("sp_dsvalor"));
                    maestro.setScmaestro(rs.getBigDecimal("sp_scmaestro"));
                    datosmaestro.setMaestro(maestro);
                    datosMaestros.add(datosmaestro);
                }

        } finally {
            closeResources(conn, call);
        }

        return datosMaestros;

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
