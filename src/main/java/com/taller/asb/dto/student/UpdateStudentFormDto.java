package com.taller.asb.dto.student;

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
import com.taller.asb.manager.StudentManager;
import com.taller.asb.manager.UserManager;
import com.taller.asb.model.EntityUtil;
import com.taller.asb.model.Student;
import com.taller.asb.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateStudentFormDto {
	
	@NotNull(message = ErrorMessage.NOT_NULL_ID_STUDENT_ERROR_MESSAGE)
	@EntityExistsValidator(
		manager = StudentManager.class,
		groups = FirstValidation.class,
		message = ErrorMessage.EXISTS_ENTITY_ID_STUDENT_ERROR_MESSAGE
	)
	private Integer idStudent;

	@NotNull(message = ErrorMessage.NOT_NULL_ID_USER_ERROR_MESSAGE)
	private Integer idUser;
	
	@NotNull(message = ErrorMessage.NOT_NULL_HAS_DOCUMENT_COPY_ERROR_MESSAGE)
	private Boolean hasDocumentCopy;	
	
	@NotNull(message = ErrorMessage.NOT_NULL_SUSPENDED_ERROR_MESSAGE)
	private Boolean suspended;
	
	@JsonProperty(required = true)
	@NotNull(message = ErrorMessage.NOT_NULL_USERNAME_ERROR_MESSAGE)
	@NotEmpty(message = ErrorMessage.NOT_EMPTY_USERNAME_ERROR_MESSAGE)
	@NotBlank(message = ErrorMessage.NOT_BLANK_USERNAME_ERROR_MESSAGE)
	@Size(max = 20, message = ErrorMessage.SIZE_USERNAME_ERROR_MESSAGE)
	private String username;
	
	@JsonProperty(required = true)
	@NotNull(message = ErrorMessage.NOT_NULL_NAME_ERROR_MESSAGE)
	@NotEmpty(message = ErrorMessage.NOT_EMPTY_NAME_ERROR_MESSAGE)
	@NotBlank(message = ErrorMessage.NOT_BLANK_NAME_ERROR_MESSAGE)
	private String name;
	
	@JsonProperty(required = true)
	@NotNull(message = ErrorMessage.NOT_NULL_LAST_NAME_ERROR_MESSAGE)
	@NotEmpty(message = ErrorMessage.NOT_EMPTY_LAST_NAME_ERROR_MESSAGE)
	@NotBlank(message = ErrorMessage.NOT_BLANK_LAST_NAME_ERROR_MESSAGE)
	private String lastName;
	
	@JsonProperty(required = true)
	@NotNull(message = ErrorMessage.NOT_NULL_ID_DOCUMENT_TYPE_ERROR_MESSAGE)
	private Integer idDocumentType;
	
	@JsonProperty(required = true)
	@NotNull(message = ErrorMessage.NOT_NULL_DOCUMENT_ERROR_MESSAGE)
	@NotEmpty(message = ErrorMessage.NOT_EMPTY_DOCUMENT_ERROR_MESSAGE)
	@NotBlank(message = ErrorMessage.NOT_BLANK_DOCUMENT_ERROR_MESSAGE)
	@Size(max = 20, message = ErrorMessage.SIZE_DOCUMENT_ERROR_MESSAGE)
	private String document;
	
	@Size(max = 100, message = ErrorMessage.SIZE_ADDRESS_ERROR_MESSAGE)
	private String address;
	
	@Size(max = 30, message = ErrorMessage.SIZE_PHONE_ERROR_MESSAGE)
	private String phone;
	
	@NotNull(message = ErrorMessage.NOT_NULL_ID_STATUS_ERROR_MESSAGE)
	private Integer idStatus;
	
	@UniqueValueOnUpdate(
		field = User.FIELD_USERNAME, 
		manager = UserManager.class,
		message = ErrorMessage.UNIQUE_VALUE_USERNAME_ERROR_MESSAGE)
	private EntityUtil getIdAndUsername() {
		return EntityUtil.builder().id(idUser).value(username).build();
	}
	
	@UniqueValueOnUpdate(
		field = Student.FIELD_DOCUMENT, 
		manager = UserManager.class,
		message = ErrorMessage.UNIQUE_VALUE_DOCUMENT_ERROR_MESSAGE)
	private EntityUtil getIdAndDocument() {
		return EntityUtil.builder().id(idUser).value(document).build();
	}
	
	@SuppressWarnings("unchecked")
	@JsonProperty("student")
	@JsonIgnoreProperties(ignoreUnknown = true)
	private void getJsonProperties(Map<String, Object> student) {
		
		Map<String, Object> data = (Map<String, Object>) student.get("data");
		
		idStudent        = (Integer) data.get("idStudent");
		idUser           = (Integer) data.get("idUser");
		username         = (String)  data.get("username");
		name             = (String)  data.get("name");
		lastName         = (String)  data.get("lastName");
		idDocumentType   = (Integer) data.get("idDocumentType");
		document         = (String)  data.get("document");
		address          = (String)  data.get("address");
		phone            = (String)  data.get("phone");
		hasDocumentCopy  = (Boolean) data.get("hasDocumentCopy");
		suspended        = (Boolean) data.get("suspended");
		idStatus         = (Integer) data.get("idStatus");
	}
}
