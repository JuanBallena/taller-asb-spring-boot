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
import com.taller.asb.error.StudentErrorMessage;
import com.taller.asb.interfaces.FirstValidation;
import com.taller.asb.interfaces.SecondValidation;
import com.taller.asb.manager.StudentManager;
import com.taller.asb.model.EntityUtil;
import com.taller.asb.model.Student;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateStudentFormDto {
	
	@NotNull(message = StudentErrorMessage.NOT_NULL_ID_STUDENT, groups = FirstValidation.class)
	@EntityExistsValidator(
		manager = StudentManager.class,
		groups = SecondValidation.class,
		message = StudentErrorMessage.STUDENT_NOT_EXISTS)
	private Integer idStudent;
	
	@NotNull(message = StudentErrorMessage.NOT_NULL_NAME)
	@NotEmpty(message = StudentErrorMessage.NOT_EMPTY_NAME)
	@NotBlank(message = StudentErrorMessage.NOT_BLANK_NAME)
	private String name;
	
	@NotNull(message = StudentErrorMessage.NOT_NULL_LAST_NAME)
	@NotEmpty(message = StudentErrorMessage.NOT_EMPTY_LAST_NAME)
	@NotBlank(message = StudentErrorMessage.NOT_BLANK_LAST_NAME)
	private String lastName;
	
	@NotNull(message = StudentErrorMessage.NOT_NULL_ID_DOCUMENT_TYPE)
	private Integer idDocumentType;
	
	@JsonProperty(required = true)
	@NotNull(message = StudentErrorMessage.NOT_NULL_DOCUMENT)
	@NotEmpty(message = StudentErrorMessage.NOT_EMPTY_DOCUMENT)
	@NotBlank(message = StudentErrorMessage.NOT_BLANK_DOCUMENT)
	@Size(max = Student.MAX_DOCUMENT, message = StudentErrorMessage.SIZE_DOCUMENT)
	private String document;
	
	@Size(max = Student.MAX_ADDRESS, message = StudentErrorMessage.SIZE_ADDRESS)
	private String address;
	
	@Size(max = Student.MAX_PHONE, message = StudentErrorMessage.SIZE_PHONE)
	private String phone;
	
	@NotNull(message = StudentErrorMessage.NOT_NULL_HAS_DOCUMENT_COPY)
	private Boolean hasDocumentCopy;	
	
	@NotNull(message = StudentErrorMessage.NOT_NULL_SUSPENDED)
	private Boolean suspended;
	
	@UniqueValueOnUpdate(
		field = Student.FIELD_DOCUMENT, 
		manager = StudentManager.class,
		message = StudentErrorMessage.EXISTING_DOCUMENT)
	private EntityUtil getIdAndDocument() {
		return EntityUtil.builder().id(idStudent).value(document).build();
	}
	
	@SuppressWarnings("unchecked")
	@JsonProperty("student")
	@JsonIgnoreProperties(ignoreUnknown = true)
	private void getJsonProperties(Map<String, Object> student) {
		
		Map<String, Object> data = (Map<String, Object>) student.get("data");
		
		idStudent        = (Integer) data.get("idStudent");
		name             = (String)  data.get("name");
		lastName         = (String)  data.get("lastName");
		idDocumentType   = (Integer) data.get("idDocumentType");
		document         = (String)  data.get("document");
		address          = (String)  data.get("address");
		phone            = (String)  data.get("phone");
		hasDocumentCopy  = (Boolean) data.get("hasDocumentCopy");
		suspended        = (Boolean) data.get("suspended");
	}
}
