package co.com.smart.dog.services.base;

/**
 * 
 */


import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import co.com.smart.dog.infraestructure.dto.SmartBaseDTO;
import co.com.smart.dog.utility.SmartConstant;

import com.google.gson.Gson;

/**
 * @author Smart Jungle S.A.S
 *
 */
public class SmartResponseBase implements Serializable{
	private static final Logger logger = Logger.getLogger("fileService");
	/**
	 * Enumerador para tipificar las respuestas 
	 * @author user
	 *
	 */
	public enum SmartHttpTypeResponse{
		
		RESP_HTTP_401("401","Unauthorized"),
		RESP_HTTP_402("500","Internal Server Error");
		
		String code;
		String msj;
		private SmartHttpTypeResponse(String code, String msj) {
			this.code=code;
			this.msj = msj;
		}
		public String getCode() {
			return code;
		}
		public String getMsj() {
			return msj;
		}
		
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Response getResponse(Object entity){
		return Response.ok(entity)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
				.header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();
	}
	
	public void responseError(HttpServletResponse res,SmartHttpTypeResponse responseType){
		Gson gson = new Gson();
		SmartBaseDTO dto = new SmartBaseDTO();
		dto.setCodigo(responseType.getCode());
		dto.setDescripcion(responseType.getMsj());
		String json = gson.toJson(dto);
		PrintWriter out;
		try {
			out = res.getWriter();
			res.setContentType(SmartConstant.MEDIA_JSON);
			res.setCharacterEncoding(SmartConstant.CHARACTER_ENCODING);
			out.print(json);
			out.flush();
			res.setStatus(Integer.parseInt(dto.getCodigo()));
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}
	
}
