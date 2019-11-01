package co.com.smart.dog.persistence.caller;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import co.com.smart.dog.infraestructure.dto.EmpresaDTO;
import co.com.smart.dog.infraestructure.dto.MensajeSQLDTO;
import co.com.smart.dog.infraestructure.dto.PropiedadDTO;
import co.com.smart.dog.persistence.entity.util.JDBCResourceManager;

public class PropiedadCaller extends JDBCResourceManager implements Serializable {
	
	private static final long serialVersionUID = 1L;

	Connection conn = null;
	CallableStatement call = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	
	public PropiedadCaller(String jdniData) {
		super(jdniData);
	}

	public List<PropiedadDTO> consultarEmpresa(PropiedadDTO propiedad) {
		// TODO Auto-generated method stub
		return null;
	}


	public PropiedadDTO grabarPropiedad(PropiedadDTO propiedad) {
		// TODO Auto-generated method stub
		return null;
	}

	public PropiedadDTO actulizarPropiedad(PropiedadDTO propiedad) {
		// TODO Auto-generated method stub
		return null;
	}

	public PropiedadDTO eliminarPropiedad(PropiedadDTO propiedad) {
		// TODO Auto-generated method stub
		return null;
	}

	
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
