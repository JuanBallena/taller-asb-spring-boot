package com.taller.asb.error;

import com.taller.asb.model.ExStudent;

public class ExStudentErrorMessage {

public static final String NOT_NULL_ID_EX_STUDENT = "Id de ex alumno no debe ser nulo";
	
	public static final String NOT_NULL_NAME = "Nombres no debe ser nulo";
	public static final String NOT_EMPTY_NAME = "Nombres no debe estar vacío";
	public static final String NOT_BLANK_NAME = "Nombres no debe estar vacío";
	
	public static final String NOT_NULL_LAST_NAME = "Apellidos no debe ser nulo";
	public static final String NOT_EMPTY_LAST_NAME = "Apellidos no debe estar vacío";
	public static final String NOT_BLANK_LAST_NAME = "Apellidos no debe estar vacío";
	
	public static final String NOT_NULL_ID_DOCUMENT_TYPE = "Tipo documento no debe ser nulo";
	
	public static final String NOT_NULL_DOCUMENT = "Documento no debe ser nulo";
	public static final String NOT_EMPTY_DOCUMENT = "Documento no debe estar vacío";
	public static final String NOT_BLANK_DOCUMENT = "Documento no debe estar vacío";
	public static final String EXISTING_DOCUMENT = "Documento existente";
	public static final String SIZE_DOCUMENT = "Documento no mayor a "+ ExStudent.MAX_DOCUMENT +" caracteres";
	
	public static final String SIZE_ADDRESS = "Dirección no mayor a "+ ExStudent.MAX_ADDRESS +" caracteres";
	
	public static final String SIZE_PHONE = "Contactos no mayor a "+ ExStudent.MAX_PHONE +" caracteres";
	
	public static final String NOT_NULL_HAS_DOCUMENT_COPY = "Copia de documento no debe ser nulo";
	
	public static final String SIZE_PROMOTION = "Promoción debe tener "+ ExStudent.LENTGH_PROMOTION +" caracteres";
	
	public static final String EX_STUDENT_NOT_EXISTS = "Ex alumno no existe";
}
