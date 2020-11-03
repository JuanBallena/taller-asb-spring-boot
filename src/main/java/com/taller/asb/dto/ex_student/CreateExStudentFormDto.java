package com.taller.asb.dto.ex_student;

import java.util.Map;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.taller.asb.annotations.UniqueValueOnCreate;
import com.taller.asb.error.ExStudentErrorMessage;
import com.taller.asb.error.UserErrorMessage;
import com.taller.asb.manager.ExStudentManager;
import com.taller.asb.manager.UserManager;
import com.taller.asb.model.ExStudent;
import com.taller.asb.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateExStudentFormDto {

	@NotNull(message = UserErrorMessage.NOT_NULL_USERNAME)
	@NotEmpty(message = UserErrorMessage.NOT_EMPTY_USERNAME)
	@NotBlank(message = UserErrorMessage.NOT_BLANK_USERNAME)
	@Size(max = User.MAX_USERNAME, message = UserErrorMessage.SIZE_USERNAME)
	@UniqueValueOnCreate(
		field = User.FIELD_USERNAME, 
		manager = UserManager.class, 
		message = UserErrorMessage.EXISTING_USERNAME)
	private String username;
	
	@NotNull(message = UserErrorMessage.NOT_NULL_PASSWORD)
	@NotEmpty(message = UserErrorMessage.NOT_EMPTY_PASSWORD)
	@NotBlank(message = UserErrorMessage.NOT_BLANK_PASSWORD)
	@Size(min = 6, message = UserErrorMessage.SIZE_PASSWORD)
	private String password;
	
	@NotNull(message = ExStudentErrorMessage.NOT_NULL_NAME)
	@NotEmpty(message = ExStudentErrorMessage.NOT_EMPTY_NAME)
	@NotBlank(message = ExStudentErrorMessage.NOT_BLANK_NAME)
	private String name;
	
	@NotNull(message = ExStudentErrorMessage.NOT_NULL_LAST_NAME)
	@NotEmpty(message = ExStudentErrorMessage.NOT_EMPTY_LAST_NAME)
	@NotBlank(message = ExStudentErrorMessage.NOT_BLANK_LAST_NAME)
	private String lastName;
	
	@NotNull(message = ExStudentErrorMessage.NOT_NULL_ID_DOCUMENT_TYPE)
	private Integer idDocumentType;
	
	@NotNull(message = ExStudentErrorMessage.NOT_NULL_DOCUMENT)
	@NotEmpty(message = ExStudentErrorMessage.NOT_EMPTY_DOCUMENT)
	@NotBlank(message = ExStudentErrorMessage.NOT_BLANK_DOCUMENT)
	@Size(max = ExStudent.MAX_DOCUMENT, message = ExStudentErrorMessage.SIZE_DOCUMENT)
	@UniqueValueOnCreate(
		field = ExStudent.FIELD_DOCUMENT,
		manager = ExStudentManager.class, 
		message = ExStudentErrorMessage.EXISTING_DOCUMENT)
	private String document;
	
	@Size(max = ExStudent.MAX_ADDRESS, message = ExStudentErrorMessage.SIZE_ADDRESS)
	private String address;
	
	@Size(max = ExStudent.MAX_PHONE, message = ExStudentErrorMessage.SIZE_PHONE)
	private String phone;
	
	@Size(min = ExStudent.LENTGH_PROMOTION, max = ExStudent.LENTGH_PROMOTION, 
			message = ExStudentErrorMessage.SIZE_PROMOTION)
	private String promotion;
	
	@SuppressWarnings("unchecked")
	@JsonProperty("exStudent")
	@JsonIgnoreProperties(ignoreUnknown = true)
	private void getJsonProperties(Map<String, Object> exStudent) {
		
		Map<String, Object> data = (Map<String, Object>) exStudent.get("data");
		
		username       = (String)  data.get("username");
		password       = (String)  data.get("password");
		name           = (String)  data.get("name");
		lastName       = (String)  data.get("lastName");
		idDocumentType = (Integer) data.get("idDocumentType");
		document       = (String)  data.get("document");
		address        = (String)  data.get("address");
		phone          = (String)  data.get("phone");
		promotion      = (String)  data.get("promotion");
	}
}
