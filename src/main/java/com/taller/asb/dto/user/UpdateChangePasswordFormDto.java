package com.taller.asb.dto.user;

import java.util.Map;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.taller.asb.error.ErrorMessage;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateChangePasswordFormDto {

	@NotNull(message = ErrorMessage.NOT_NULL_CHANGE_PASSWORD_ERROR_MESSAGE)
	private Boolean changePassword;
	
	@SuppressWarnings("unchecked")
	@JsonProperty("user")
	@JsonIgnoreProperties(ignoreUnknown = true)
	private void getJsonProperties(Map<String, Object> user) {
		
		Map<String, Object> data = (Map<String, Object>) user.get("data");
		
		changePassword = (Boolean) data.get("changePassword");
	}
}
