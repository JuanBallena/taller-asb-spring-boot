package com.taller.asb.definition;

public class ResponseDefinition {

	public static final Integer RESPONSECODE_OK = 200;
	public static final String RESPONSECODE_OK_S = "Exito";
	
	public static final Integer RESPONSECODE_CREATED = 201;
	public static final String RESPONSECODE_CREATED_S = "Registrado con éxito";
	
	public static final Integer RESPONSECODE_NO_CONTENT = 204;
	public static final String RESPONSECODE_NO_CONTENT_S = "Sin contenido";
	
	public static final Integer RESPONSECODE_BAD_REQUEST = 400;
	public static final String RESPONSECODE_BAD_REQUEST_S = "Solicitud incorrecta";
	
	public static final Integer RESPONSECODE_UNAUTHORIZED = 401;
	public static final String RESPONSECODE_UNAUTHORIZED_S = "No autorizado";
	
	public static final Integer RESPONSECODE_FORBIDDEN = 403;
	public static final String RESPONSECODE_FORBIDDEN_S = "Prohibido";
	
	public static final Integer RESPONSECODE_NOT_FOUND = 404;
	public static final String RESPONSECODE_NOT_FOUND_S = "Sin contenido";
	
	public static final Integer RESPONSECODE_UNPROCESSABLE_ENTITY = 422;
	public static final String RESPONSECODE_UNPROCESSABLE_ENTITY_S = "Entidad no procesable, error semántico";
	
	public static final Integer RESPONSECODE_INTERNAL_SERVER_ERROR = 500;
	public static final String RESPONSECODE_INTERNAL_SERVER_ERROR_S = "Error de servidor";
	
	public static final Integer RESPONSECODE_SERVICE_UNAVAILABLE = 503;
	public static final String RESPONSECODE_SERVICE_UNAVAILABLE_S = "Servicio no disponible";
	
//	public static final Integer RESPONSECODE_ERROR_GENERAL = 9999;
//	public static final String RESPONSECODE_ERROR_GENERAL_S = "Error general";
}
