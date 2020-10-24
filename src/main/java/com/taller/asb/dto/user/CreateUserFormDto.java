package com.taller.asb.dto.user;

import java.util.Map;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.taller.asb.annotations.UniqueValueValidator;
import com.taller.asb.error.UserErrorMessages;
import com.taller.asb.manager.UserManager;
import com.taller.asb.model.User;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateUserFormDto {
	
	@JsonProperty(required = true)
	@NotNull(message = UserErrorMessages.NOT_NULL_USERNAME_ERROR_MESSAGE)
	@NotEmpty(message = UserErrorMessages.NOT_EMPTY_USERNAME_ERROR_MESSAGE)
	@NotBlank(message = UserErrorMessages.NOT_BLANK_USERNAME_ERROR_MESSAGE)
	@UniqueValueValidator(
		field = User.FIELD_USERNAME, 
		manager = UserManager.class, 
		message = UserErrorMessages.UNIQUE_VALUE_USERNAME_ERROR_MESSAGE
	)
	@Size(max = 20, message = UserErrorMessages.SIZE_USERNAME_ERROR_MESSAGE)
	private String username;
	
	@JsonProperty(required = true)
	@NotNull(message = UserErrorMessages.NOT_NULL_PASSWORD_ERROR_MESSAGE)
	@NotEmpty(message = UserErrorMessages.NOT_EMPTY_PASSWORD_ERROR_MESSAGE)
	@NotBlank(message = UserErrorMessages.NOT_BLANK_PASSWORD_ERROR_MESSAGE)
	@Size(min = 6, message = UserErrorMessages.SIZE_PASSWORD_ERROR_MESSAGE)
	private String password;
	
	@JsonProperty(required = true)
	@NotNull(message = UserErrorMessages.NOT_NULL_ID_ROLE_ERROR_MESSAGE)
	private Integer idRole;
	
	@JsonProperty(required = true)
	@NotNull(message = UserErrorMessages.NOT_NULL_NAME_ERROR_MESSAGE)
	@NotEmpty(message = UserErrorMessages.NOT_EMPTY_NAME_ERROR_MESSAGE)
	@NotBlank(message = UserErrorMessages.NOT_BLANK_NAME_ERROR_MESSAGE)
	private String name;
	
	@JsonProperty(required = true)
	@NotNull(message = UserErrorMessages.NOT_NULL_LAST_NAME_ERROR_MESSAGE)
	@NotEmpty(message = UserErrorMessages.NOT_EMPTY_LAST_NAME_ERROR_MESSAGE)
	@NotBlank(message = UserErrorMessages.NOT_BLANK_LAST_NAME_ERROR_MESSAGE)
	private String lastName;
	
	@JsonProperty(required = true)
	@NotNull(message = UserErrorMessages.NOT_NULL_ID_DOCUMENT_TYPE_ERROR_MESSAGE)
	private Integer idDocumentType;
	
	@JsonProperty(required = true)
	@NotNull(message = UserErrorMessages.NOT_NULL_DOCUMENT_ERROR_MESSAGE)
	@NotEmpty(message = UserErrorMessages.NOT_EMPTY_DOCUMENT_ERROR_MESSAGE)
	@NotBlank(message = UserErrorMessages.NOT_BLANK_DOCUMENT_ERROR_MESSAGE)
	@UniqueValueValidator(
		field = User.FIELD_DOCUMENT, 
		manager = UserManager.class, 
		message = UserErrorMessages.UNIQUE_VALUE_DOCUMENT_ERROR_MESSAGE
	)
	@Size(max = 20, message = UserErrorMessages.SIZE_DOCUMENT_ERROR_MESSAGE)
	private String document;
	
	@JsonProperty(required = true)
	@Size(max = 100, message = UserErrorMessages.SIZE_ADDRESS_ERROR_MESSAGE)
	private String address;
	
	@JsonProperty(required = true)
	@Size(max = 30, message = UserErrorMessages.SIZE_PHONE_ERROR_MESSAGE)
	private String phone;
	
	@SuppressWarnings("unchecked")
	@JsonProperty("user")
	@JsonIgnoreProperties(ignoreUnknown = true)
	private void getJsonProperties(Map<String, Object> user) {
		
		Map<String, Object> data = (Map<String, Object>) user.get("data");
		
		username       = (String)  data.get("username");
		password       = (String)  data.get("password");
		idRole         = (Integer) data.get("idRole");
		name           = (String)  data.get("name");
		lastName       = (String)  data.get("lastName");
		idDocumentType = (Integer) data.get("idDocumentType");
		document       = (String)  data.get("document");
		address        = (String)  data.get("address");
		phone          = (String)  data.get("phone");
	}
	
}
