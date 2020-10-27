package com.taller.asb.dto.student;

import java.util.Map;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.taller.asb.annotations.UniqueValueOnCreate;
import com.taller.asb.error.ErrorMessage;
import com.taller.asb.manager.StudentManager;
import com.taller.asb.manager.UserManager;
import com.taller.asb.model.Student;
import com.taller.asb.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateStudentFormDto {

	@NotNull(message = ErrorMessage.NOT_NULL_USERNAME_ERROR_MESSAGE)
	@NotEmpty(message = ErrorMessage.NOT_EMPTY_USERNAME_ERROR_MESSAGE)
	@NotBlank(message = ErrorMessage.NOT_BLANK_USERNAME_ERROR_MESSAGE)
	@Size(max = 20, message = ErrorMessage.SIZE_USERNAME_ERROR_MESSAGE)
	@UniqueValueOnCreate(
		field = User.FIELD_USERNAME, 
		manager = UserManager.class, 
		message = ErrorMessage.UNIQUE_VALUE_USERNAME_ERROR_MESSAGE)
	private String username;
	
	@NotNull(message = ErrorMessage.NOT_NULL_PASSWORD_ERROR_MESSAGE)
	@NotEmpty(message = ErrorMessage.NOT_EMPTY_PASSWORD_ERROR_MESSAGE)
	@NotBlank(message = ErrorMessage.NOT_BLANK_PASSWORD_ERROR_MESSAGE)
	@Size(min = 6, message = ErrorMessage.SIZE_PASSWORD_ERROR_MESSAGE)
	private String password;
	
	@NotNull(message = ErrorMessage.NOT_NULL_NAME_ERROR_MESSAGE)
	@NotEmpty(message = ErrorMessage.NOT_EMPTY_NAME_ERROR_MESSAGE)
	@NotBlank(message = ErrorMessage.NOT_BLANK_NAME_ERROR_MESSAGE)
	private String name;
	
	@NotNull(message = ErrorMessage.NOT_NULL_LAST_NAME_ERROR_MESSAGE)
	@NotEmpty(message = ErrorMessage.NOT_EMPTY_LAST_NAME_ERROR_MESSAGE)
	@NotBlank(message = ErrorMessage.NOT_BLANK_LAST_NAME_ERROR_MESSAGE)
	private String lastName;
	
	@NotNull(message = ErrorMessage.NOT_NULL_ID_DOCUMENT_TYPE_ERROR_MESSAGE)
	private Integer idDocumentType;
	
	@NotNull(message = ErrorMessage.NOT_NULL_DOCUMENT_ERROR_MESSAGE)
	@NotEmpty(message = ErrorMessage.NOT_EMPTY_DOCUMENT_ERROR_MESSAGE)
	@NotBlank(message = ErrorMessage.NOT_BLANK_DOCUMENT_ERROR_MESSAGE)
	@Size(max = 20, message = ErrorMessage.SIZE_DOCUMENT_ERROR_MESSAGE)
	@UniqueValueOnCreate(
		field = Student.FIELD_DOCUMENT,
		manager = StudentManager.class, 
		message = ErrorMessage.UNIQUE_VALUE_DOCUMENT_ERROR_MESSAGE)
	private String document;
	
	@Size(max = 100, message = ErrorMessage.SIZE_ADDRESS_ERROR_MESSAGE)
	private String address;
	
	@Size(max = 30, message = ErrorMessage.SIZE_PHONE_ERROR_MESSAGE)
	private String phone;
	
	@SuppressWarnings("unchecked")
	@JsonProperty("student")
	@JsonIgnoreProperties(ignoreUnknown = true)
	private void getJsonProperties(Map<String, Object> student) {
		
		Map<String, Object> data = (Map<String, Object>) student.get("data");
		
		username       = (String)  data.get("username");
		password       = (String)  data.get("password");
		name           = (String)  data.get("name");
		lastName       = (String)  data.get("lastName");
		idDocumentType = (Integer) data.get("idDocumentType");
		document       = (String)  data.get("document");
		address        = (String)  data.get("address");
		phone          = (String)  data.get("phone");
	}
}
