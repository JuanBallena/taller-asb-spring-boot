package com.taller.asb.dto.user;

import java.util.Map;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.taller.asb.annotations.UniqueValueOnCreate;
import com.taller.asb.error.UserErrorMessage;
import com.taller.asb.manager.UserManager;
import com.taller.asb.model.User;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateUserFormDto {
	
	@NotNull(message = UserErrorMessage.NOT_NULL_USERNAME)
	@NotEmpty(message = UserErrorMessage.NOT_EMPTY_USERNAME)
	@NotBlank(message = UserErrorMessage.NOT_BLANK_USERNAME)
	@Size(min = User.MIN_USERNAME, max = User.MAX_USERNAME, message = UserErrorMessage.SIZE_USERNAME)
	@UniqueValueOnCreate(
		field = User.FIELD_USERNAME,
		manager = UserManager.class, 
		message = UserErrorMessage.EXISTING_USERNAME)
	private String username;
	
	@NotNull(message = UserErrorMessage.NOT_NULL_PASSWORD)
	@NotEmpty(message = UserErrorMessage.NOT_EMPTY_PASSWORD)
	@NotBlank(message = UserErrorMessage.NOT_BLANK_PASSWORD)
	@Size(min = User.MIN_PASSWORD, message = UserErrorMessage.SIZE_PASSWORD)
	private String password;
	
	@NotNull(message = UserErrorMessage.NOT_NULL_ID_ROLE)
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
