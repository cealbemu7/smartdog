package co.com.smart.dog.persistence.caller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.naming.NamingException;

import co.com.smart.dog.infraestructure.dto.ClienteDTO;
import co.com.smart.dog.infraestructure.dto.MensajeSQLDTO;
import co.com.smart.dog.persistence.entity.util.JDBCResourceManager;

public class ClienteCaller extends JDBCResourceManager implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Connection conn = null;
	CallableStatement call = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	
	public ClienteCaller(String jdniData) {
		super(jdniData);
	}
	
	/**
	 * Metodo utilizado para grabar cliente o terceros.
	 * @param cliente
	 * @return
	 * @throws SQLException
	 * @throws NamingException	 
	 * @throws ParseException 
	 */
	public ClienteDTO GrabarCliente (ClienteDTO cliente) throws SQLException, NamingException, ParseException{
		SimpleDateFormat outFormat = new SimpleDateFormat("dd-MM-yyyy");
		DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
		try { 
			conn = getConnection();
			call = conn.prepareCall(getString("ClienteCaller.fn_grabar_cliente"));
			System.out.println(cliente);
			if (cliente.getSccliente() != null) {
				call.setInt(1, cliente.getSccliente().intValue());
            } else {
                call.setNull(1, java.sql.Types.INTEGER);
            }

			if (cliente.getTipoidentificacion() != null) {
				call.setInt(2, cliente.getTipoidentificacion().getScdatmaestro().intValue());
            } else {
                call.setNull(2, java.sql.Types.INTEGER);
            }
			call.setString(3, cliente.getDsidentificacion());
			call.setString(4, cliente.getDspnombre());
			call.setString(5, cliente.getDssnombre());
			call.setString(6, cliente.getDspapellido());
			call.setString(7, cliente.getDssapellido());
			call.setString(8, cliente.getDstelefono());
			call.setString(9, cliente.getDscelular());
			call.setString(10, cliente.getDsemail());
			Date fechanacimiento = inputFormat.parse(cliente.getFhnacimiento());
			call.setString(11, outFormat.format(fechanacimiento));
			call.setString(12, cliente.getUsuario().getCousuario());
			
			if (cliente.getSexo() != null) {
				call.setInt(13, cliente.getSexo().getScdatmaestro().intValue());
            } else {
                call.setNull(13, java.sql.Types.INTEGER);
            }
			
			if (cliente.getUsuario().getScusuario() != null) {
				call.setInt(14, cliente.getUsuario().getScusuario().intValue());
            } else {
                call.setNull(14, java.sql.Types.INTEGER);
            }
			call.setString(15, cliente.getDsdireccion());
			
			call.registerOutParameter(16, java.sql.Types.INTEGER);
			call.registerOutParameter(17, java.sql.Types.VARCHAR);
			
			call.executeUpdate();
			
			cliente.setSccliente(new BigDecimal(call.getInt(16))); 
			MensajeSQLDTO msj = getResponseSQL(call.getString(17));
			cliente.setCodigo(msj.getCodigo());
			cliente.setDescripcion(msj.getDescripcion());
						
		}finally {
			closeResources(conn, call);
		}
		return cliente;
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
