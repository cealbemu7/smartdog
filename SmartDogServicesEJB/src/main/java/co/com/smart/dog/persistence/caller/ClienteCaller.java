package co.com.smart.dog.persistence.caller;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.naming.NamingException;

import co.com.smart.dog.infraestructure.dto.ClienteDTO;
import co.com.smart.dog.infraestructure.dto.DatosMaestroDTO;
import co.com.smart.dog.infraestructure.dto.MensajeSQLDTO;
import co.com.smart.dog.infraestructure.dto.UsuarioDTO;
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
		SimpleDateFormat outFormat = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
		try { 
			conn = getConnection();
			call = conn.prepareCall(getString("ClienteCaller.fn_grabar_cliente"));
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
			
			if(cliente.getFhnacimiento() != null){
				Date fechanacimiento = inputFormat.parse(cliente.getFhnacimiento());
				call.setString(11, outFormat.format(fechanacimiento));
			}else{
				call.setNull(13, java.sql.Types.VARCHAR);
			}

			call.setString(12, cliente.getUsuario().getDsusuario());
			
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
	
	/**
	 * Metodo utilizado para consultar clientes
	 * 
	 * @param cliente
	 * @return
	 * @throws SQLException
	 * @throws NamingException
	 * @throws IOException
	 */
	public ClienteDTO ConsultarCliente(ClienteDTO cliente) throws SQLException, NamingException, IOException {
		ClienteDTO returncliente = new ClienteDTO();
		try {	
			conn = getConnection();
			call = conn.prepareCall(getString("ClienteCaller.fn_consultar_cliente"));
			call.registerOutParameter(1, Types.OTHER);

			if(cliente.getUsuario() != null){
				call.setInt(2,cliente.getUsuario().getScusuario().intValue());
			}else{
				call.setNull(2, java.sql.Types.NULL);
			}
			
			if(cliente.getSccliente() != null){
				call.setInt(3,cliente.getSccliente().intValue());
			}else{
				call.setNull(3, java.sql.Types.NULL);
			}
			
			if(cliente.getTipoidentificacion() != null){
				call.setInt(4,cliente.getTipoidentificacion().getScdatmaestro().intValue());
			}else{
				call.setNull(4, java.sql.Types.NULL);
			}
			if(cliente.getDsidentificacion() != null){
				call.setString(5,cliente.getDsidentificacion());
			}else{
				call.setNull(5, java.sql.Types.NULL);
			}
			call.execute();
			rs = (ResultSet) call.getObject(1);
			while (rs.next()) {
				DatosMaestroDTO tipoidentificacion = new DatosMaestroDTO();
				DatosMaestroDTO sexo = new DatosMaestroDTO();
				UsuarioDTO usuario = new UsuarioDTO();
				
				returncliente.setSccliente(rs.getBigDecimal("sm_sccliente"));
				tipoidentificacion.setScdatmaestro(rs.getBigDecimal("sm_sctipoidentificacion"));
				returncliente.setTipoidentificacion(tipoidentificacion);
				returncliente.setDsidentificacion(rs.getString("sm_dsidentificacion"));
				returncliente.setNombrecompleto(rs.getString("nombrecompleto"));
				returncliente.setDspnombre(rs.getString("sm_dspnombre"));
				returncliente.setDssnombre(rs.getString("sm_dssnombre"));
				returncliente.setDspapellido(rs.getString("sm_dspapellido"));
				returncliente.setDssapellido(rs.getString("sm_dssapellido")); 
				returncliente.setDstelefono(rs.getString("sm_dstelefono"));
				returncliente.setDscelular(rs.getString("sm_dscelular"));
				returncliente.setDsemail(rs.getString("sm_dsemail"));
				returncliente.setDsdireccion(rs.getString("sm_dsdireccion"));
				returncliente.setFhnacimiento(rs.getString("sm_fhnacimiento"));
				
				usuario.setScusuario(rs.getBigDecimal("sm_scusuario"));
				usuario.setCousuario(rs.getString("sm_cousuario"));
				returncliente.setUsuario(usuario);
				
				sexo.setDsdatmaestro(rs.getString("sm_dssexo"));
				sexo.setScdatmaestro(rs.getBigDecimal("sm_scsexo"));
				returncliente.setSexo(sexo);
				 
			}
		} catch (SQLException e) {
			e.printStackTrace(System.err);
			throw e;
		} catch (Throwable thr) {
			thr.printStackTrace(System.err);
			throw thr;
		} finally {
			closeResources(conn, call);
		}
		return returncliente;
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
