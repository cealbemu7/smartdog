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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import co.com.smart.dog.infraestructure.dto.AsesorDTO;
import co.com.smart.dog.infraestructure.dto.CitaDTO;
import co.com.smart.dog.infraestructure.dto.ClienteDTO;
import co.com.smart.dog.infraestructure.dto.DatosMaestroDTO;
import co.com.smart.dog.infraestructure.dto.EmpresaDTO;
import co.com.smart.dog.infraestructure.dto.MensajeSQLDTO;
import co.com.smart.dog.infraestructure.dto.PropiedadDTO;
import co.com.smart.dog.persistence.entity.util.JDBCResourceManager;

public class CitaCaller extends JDBCResourceManager implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Connection conn = null;
	CallableStatement call = null;
	ResultSet rs = null;
	PreparedStatement pst = null;

	public CitaCaller(String jdniData) {
		super(jdniData);
	}

	/**
	 * Metodo utilizado para eliminar citas
	 * 
	 * @param cita
	 * @return
	 * @throws SQLException
	 * @throws NamingException
	 * @throws IOException
	 */
	public CitaDTO EliminarCita(CitaDTO cita) throws SQLException, NamingException, IOException {
		CitaDTO returnCita = new CitaDTO();
		try {
			conn = getConnection();
			call = conn.prepareCall(getString("CitaCaller.fn_eliminar_citas"));

			if (cita.getSccita() != null) {
				call.setInt(1, cita.getSccita().intValue());
			} else {
				call.setNull(1, java.sql.Types.NULL);
			}

			if (cita.getUsuario() != null) {
				call.setString(2, cita.getUsuario().getDsusuario());
			} else {
				call.setNull(2, java.sql.Types.NULL);
			}

			call.registerOutParameter(3, java.sql.Types.INTEGER);
			call.registerOutParameter(4, java.sql.Types.VARCHAR);
			call.executeUpdate();
			cita.setSccita(new BigDecimal(call.getInt(3)));
			MensajeSQLDTO msj = getResponseSQL(call.getString(4));
			cita.setCodigo(msj.getCodigo());
			cita.setDescripcion(msj.getDescripcion());

		} catch (SQLException e) {
			e.printStackTrace(System.err);
			throw e;
		} catch (Throwable thr) {
			thr.printStackTrace(System.err);
			throw thr;

		} finally {
			closeResources(conn, call);
		}
		return returnCita;
	}

	/**
	 * Metodo utilizado para listar citas
	 * 
	 * @param cita
	 * @return
	 * @throws SQLException
	 * @throws NamingException
	 * @throws IOException
	 */
	public List<CitaDTO> ConsultarCita(CitaDTO cita) throws SQLException, NamingException, IOException {
		List<CitaDTO> ListarCita = new ArrayList<>();
		try {
			conn = getConnection();
			call = conn.prepareCall(getString("CitaCaller.fn_consultarcitas"));
			call.registerOutParameter(1, Types.OTHER);

			if (cita.getAsesor() != null) {
				call.setInt(2, cita.getAsesor().getScasesor().intValue());
			} else {
				call.setNull(2, java.sql.Types.NULL);
			}

			if (cita.getPropiedad() != null) {
				call.setInt(3, cita.getPropiedad().getScpropiedad().intValue());
			} else {
				call.setNull(3,java.sql.Types.NULL);
			}

			if (cita.getCliente() != null) {
				call.setInt(4, cita.getCliente().getSccliente().intValue());
			} else {
				call.setNull(4, java.sql.Types.NULL);
			}

			if (cita.getEstado() != null) {
				call.setInt(5, cita.getEstado().getScdatmaestro().intValue());
			} else {
				call.setNull(5, java.sql.Types.NULL);
			}

			if (cita.getFhhorainicio() != null) {
				call.setString(6, cita.getFhhorainicio());
			} else {
				call.setNull(6, java.sql.Types.NULL);
			}

			call.execute();
			rs = (ResultSet) call.getObject(1);
			while (rs.next()) {
				CitaDTO citas = new CitaDTO();
				PropiedadDTO propiedad = new PropiedadDTO();
				AsesorDTO asesor = new AsesorDTO();
				ClienteDTO cliente = new ClienteDTO();
				EmpresaDTO empresa = new EmpresaDTO();
				DatosMaestroDTO tipoidentificacion = new DatosMaestroDTO();
				DatosMaestroDTO tipoidentificacionasesor = new DatosMaestroDTO();
				DatosMaestroDTO tipoinmueble = new DatosMaestroDTO();
				DatosMaestroDTO estado = new DatosMaestroDTO();

				propiedad.setScpropiedad(rs.getBigDecimal("sm_scpropiedad"));
				propiedad.setCopropiedad(rs.getString("sm_copropiedad"));
				propiedad.setDspropiedad(rs.getString("sm_dspropiedad"));

				citas.setPropiedad(propiedad);

				cliente.setSccliente(rs.getBigDecimal("sm_sccliente"));

				tipoidentificacion.setScdatmaestro(rs.getBigDecimal("sm_sctipoidentificacion"));

				cliente.setTipoidentificacion(tipoidentificacion);
				cliente.setDsidentificacion(rs.getString("sm_dsidentificacion"));
				cliente.setNombrecompleto(rs.getString("nombrecliente"));
				cliente.setTipoidentificacion(tipoidentificacion);

				citas.setCliente(cliente);

				empresa.setScempresa(rs.getBigDecimal("sm_scempresa"));
				empresa.setDsrazonsocial(rs.getString("sm_dsrazonsocial"));
				citas.setEmpresa(empresa);
				/*
				tipoinmueble.setScdatmaestro(rs.getBigDecimal("sctipoinmueble"));
				tipoinmueble.setDsdatmaestro(rs.getString("dstipoinmueble"));
				citas.setTipoinmueble(tipoinmueble);*/

				citas.setFhhorainicio(rs.getString("sm_fhhorainicio"));
				citas.setFhhorafin(rs.getString("sm_fhhorafin"));
				asesor.setScasesor(rs.getBigDecimal("sm_scasesor"));
				asesor.setCoidentificacion(rs.getString("sm_dsidentificacion"));
				asesor.setNombrecompleto(rs.getString("nombreasesor"));
				tipoidentificacionasesor.setScdatmaestro(rs.getBigDecimal("sm_sctipoidentificacion"));
				asesor.setTipoidentificacion(tipoidentificacionasesor);
				estado.setScdatmaestro(rs.getBigDecimal("sm_scestado"));
				estado.setDsdatmaestro(rs.getString("dsestado"));
				estado.setDsvalor(rs.getString("valorestado"));

				citas.setAsesor(asesor);
				citas.setEstado(estado);

				ListarCita.add(citas);
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
		return ListarCita;
	}

	/**
	 * Metodo utilizado para grabar cita
	 * 
	 * @param cita
	 * @return
	 * @throws SQLException
	 * @throws NamingException
	 * @throws ParseException
	 */
	public CitaDTO GrabarCita(CitaDTO cita) throws SQLException, NamingException, ParseException {
		try {
			conn = getConnection();
			call = conn.prepareCall(getString("CitaCaller.fn_grabar_citas"));

			if (cita.getSccita() != null) {
				call.setInt(1, cita.getSccita().intValue());
			} else {
				call.setNull(1, java.sql.Types.NULL);
			}

			if (cita.getPropiedad() != null) {
				call.setInt(2, cita.getPropiedad().getScpropiedad().intValue());
			} else {
				call.setNull(2, java.sql.Types.NULL);
			}

			if (cita.getCliente() != null) {
				call.setInt(3, cita.getCliente().getSccliente().intValue());
			} else {
				call.setNull(3, java.sql.Types.NULL);
			}
		
			call.setString(4,cita.getFhhorainicio()); 
			
			call.setString(5, cita.getUsuario().getCousuario());
			
			call.setString(6, cita.getFhhorafin());

			if (cita.getAsesor() != null && cita.getAsesor().getScasesor() != null) {
				call.setInt(7, cita.getAsesor().getScasesor().intValue());
			} else {
				call.setNull(7, java.sql.Types.NULL);
			}

			if (cita.getEstado() != null && cita.getEstado().getScdatmaestro() != null) {
				call.setInt(8, cita.getEstado().getScdatmaestro().intValue());
			} else {
				call.setNull(8, java.sql.Types.NULL);
			}

			if (cita.getEmpresa() != null) {
				call.setInt(9, cita.getEmpresa().getScempresa().intValue());
			} else {
				call.setNull(9, java.sql.Types.NULL);
			}

			call.registerOutParameter(10, java.sql.Types.INTEGER);
			call.registerOutParameter(11, java.sql.Types.VARCHAR);

			call.executeUpdate();

			cita.setSccita(new BigDecimal(call.getInt(10)));
			MensajeSQLDTO msj = getResponseSQL(call.getString(11));
			cita.setCodigo(msj.getCodigo());
		} catch (SQLException e) {
			e.printStackTrace(System.err);
			throw e;
		} catch (Throwable thr) {
			thr.printStackTrace(System.err);
			throw thr;
		} finally {
			closeResources(conn, call);
		}
		return cita;
	}

	@Override
	public MensajeSQLDTO getResponseSQL(String response) {
		MensajeSQLDTO sqldto = new MensajeSQLDTO();

		if (response != null) {
			String[] msj = response.split(":");
			sqldto.setCodigo(msj[0]);
			sqldto.setDescripcion(msj[1]);
		}

		return sqldto;
	}
}
