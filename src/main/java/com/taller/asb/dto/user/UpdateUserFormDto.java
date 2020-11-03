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
import com.taller.asb.error.UserErrorMessage;
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
		
	@NotNull(groups = FirstValidation.class, message = UserErrorMessage.NOT_NULL_ID_USER)
	@EntityExistsValidator(manager = UserManager.class, groups = SecondValidation.class, 
		message = UserErrorMessage.USER_NOT_EXISTS)
	private Integer idUser;
	
	@NotNull(message = UserErrorMessage.NOT_NULL_USERNAME)
	@NotEmpty(message = UserErrorMessage.NOT_EMPTY_USERNAME)
	@NotBlank(message = UserErrorMessage.NOT_BLANK_USERNAME)
	@Size(min = User.MIN_USERNAME, max = User.MAX_USERNAME, message = UserErrorMessage.SIZE_USERNAME)
	private String username;
	
//	@NotNull(message = UserErrorMessage.NOT_NULL_PASSWORD)
//	@NotEmpty(message = UserErrorMessage.NOT_EMPTY_PASSWORD)
//	@NotBlank(message = UserErrorMessage.NOT_BLANK_PASSWORD)
//	@Size(min = User.MIN_PASSWORD, message = UserErrorMessage.SIZE_PASSWORD)
//	private String password;
	
	@NotNull(message = UserErrorMessage.NOT_NULL_ID_ROLE)
	private Integer idRole;
	
	@NotNull(message = UserErrorMessage.NOT_NULL_ID_STATUS)
	private Integer idStatus;
	
	@UniqueValueOnUpdate(
		field = User.FIELD_USERNAME, 
		manager = UserManager.class,
		message = UserErrorMessage.EXISTING_USERNAME)
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
//		password         = (String)  data.get("password");
		idRole         	 = (Integer) data.get("idRole");
		idStatus         = (Integer) data.get("idStatus");
	}
}
