package com.taller.asb.dto.user;

import java.util.Map;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.taller.asb.annotations.EntityExistsValidator;
import com.taller.asb.annotations.UniqueValueOnUpdate;
import com.taller.asb.error.ErrorMessage;
import com.taller.asb.interfaces.FirstValidation;
import com.taller.asb.interfaces.SecondValidation;
import com.taller.asb.manager.UserManager;
import com.taller.asb.model.EntityUtil;
import com.taller.asb.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserFormDto {
		
	@NotNull(groups = FirstValidation.class, message = ErrorMessage.NOT_NULL_ID_USER_ERROR_MESSAGE)
	@EntityExistsValidator(
		manager = UserManager.class, 
		groups = SecondValidation.class,
		message = ErrorMessage.EXISTS_ENTITY_ID_USER_ERROR_MESSAGE
	)
	private Integer idUser;
	
	@NotNull(message = ErrorMessage.NOT_NULL_USERNAME_ERROR_MESSAGE)
	@NotEmpty(message = ErrorMessage.NOT_EMPTY_USERNAME_ERROR_MESSAGE)
	@NotBlank(message = ErrorMessage.NOT_BLANK_USERNAME_ERROR_MESSAGE)
	@Size(max = 20, message = ErrorMessage.SIZE_USERNAME_ERROR_MESSAGE)
	private String username;
	
	@NotNull(message = ErrorMessage.NOT_NULL_PASSWORD_ERROR_MESSAGE)
	@NotEmpty(message = ErrorMessage.NOT_EMPTY_PASSWORD_ERROR_MESSAGE)
	@NotBlank(message = ErrorMessage.NOT_BLANK_PASSWORD_ERROR_MESSAGE)
	@Size(min = 6, message = ErrorMessage.SIZE_PASSWORD_ERROR_MESSAGE)
	private String password;
	
	@NotNull(message = ErrorMessage.NOT_NULL_ID_ROLE_ERROR_MESSAGE)
	private Integer idRole;
	
	@NotNull(message = ErrorMessage.NOT_NULL_ID_STATUS_ERROR_MESSAGE)
	private Integer idStatus;
	
	@UniqueValueOnUpdate(
		field = User.FIELD_USERNAME, 
		manager = UserManager.class,
		message = ErrorMessage.UNIQUE_VALUE_USERNAME_ERROR_MESSAGE)
	private EntityUtil getIdAndUsername() {
		return EntityUtil.builder().id(idUser).value(username).build();
	}
	
	@SuppressWarnings("unchecked")
	@JsonProperty("user")
	@JsonIgnoreProperties(ignoreUnknown = true)
	private void getJsonProperties(Map<String, Object> user) {
		
		Map<String, Object> data = (Map<String, Object>) user.get("data");
		
		idUser           = (Integer) data.get("idUser");
		username         = (String)  data.get("username");
		password         = (String)  data.get("password");
		idRole         	 = (Integer) data.get("idRole");
		idStatus         = (Integer) data.get("idStatus");
	}
}
