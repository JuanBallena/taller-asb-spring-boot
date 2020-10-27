package com.taller.asb.dto.user;

import java.util.Map;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.taller.asb.annotations.UniqueValueOnCreate;
import com.taller.asb.error.ErrorMessage;
import com.taller.asb.manager.UserManager;
import com.taller.asb.model.User;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateUserFormDto {
	
	@NotNull(message = ErrorMessage.NOT_NULL_USERNAME_ERROR_MESSAGE)
	@NotEmpty(message = ErrorMessage.NOT_EMPTY_USERNAME_ERROR_MESSAGE)
	@NotBlank(message = ErrorMessage.NOT_BLANK_USERNAME_ERROR_MESSAGE)
	@UniqueValueOnCreate(
		field = User.FIELD_USERNAME,
		manager = UserManager.class, 
		message = ErrorMessage.UNIQUE_VALUE_USERNAME_ERROR_MESSAGE)
	@Size(max = 20, message = ErrorMessage.SIZE_USERNAME_ERROR_MESSAGE)
	private String username;
	
	@NotNull(message = ErrorMessage.NOT_NULL_PASSWORD_ERROR_MESSAGE)
	@NotEmpty(message = ErrorMessage.NOT_EMPTY_PASSWORD_ERROR_MESSAGE)
	@NotBlank(message = ErrorMessage.NOT_BLANK_PASSWORD_ERROR_MESSAGE)
	@Size(min = 6, message = ErrorMessage.SIZE_PASSWORD_ERROR_MESSAGE)
	private String password;
	
	@NotNull(message = ErrorMessage.NOT_NULL_ID_ROLE_ERROR_MESSAGE)
	private Integer idRole;
	
	@SuppressWarnings("unchecked")
	@JsonProperty("user")
	@JsonIgnoreProperties(ignoreUnknown = true)
	private void getJsonProperties(Map<String, Object> user) {
		
		Map<String, Object> data = (Map<String, Object>) user.get("data");
		
		username       = (String)  data.get("username");
		password       = (String)  data.get("password");
		idRole         = (Integer) data.get("idRole");
	}
}
