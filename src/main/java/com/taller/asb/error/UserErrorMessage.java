package com.taller.asb.error;

import com.taller.asb.model.User;

public class UserErrorMessage {

	public static final String NOT_NULL_ID_USER = "Id de usuario no debe ser nulo";
	
	public static final String NOT_NULL_USERNAME = "Nombre de usuario no debe ser nulo";
	public static final String NOT_EMPTY_USERNAME = "Nombre de usuario no debe estar vacío";
	public static final String NOT_BLANK_USERNAME = "Nombre de usuario no debe estar vacío";
	public static final String EXISTING_USERNAME = "Nombre de usuario existente";
	public static final String SIZE_USERNAME = "Nombre de usuario debe estar entre "+ User.MIN_USERNAME +" y "+ User.MAX_USERNAME +" caracteres";
	
	public static final String NOT_NULL_PASSWORD = "Contraseña no debe ser nulo";
	public static final String NOT_EMPTY_PASSWORD = "Contraseña no debe estar vacío";
	public static final String NOT_BLANK_PASSWORD = "Contraseña no debe estar vacío";
	public static final String SIZE_PASSWORD = "Contraseña no menor a "+ User.MIN_PASSWORD +" caracteres";
	
	public static final String NOT_NULL_ID_ROLE = "Rol no debe ser nulo";
	
	public static final String NOT_NULL_CHANGE_PASSWORD = "Cambiar contraseña no debe ser nulo";
	
	public static final String NOT_NULL_ID_STATUS = "Estado no debe ser nulo";
	
	public static final String USER_NOT_EXISTS = "Usuario no existe";
}
