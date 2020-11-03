package com.taller.asb.dto.ex_student;

import java.util.Map;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.taller.asb.annotations.EntityExistsValidator;
import com.taller.asb.annotations.UniqueValueOnUpdate;
import com.taller.asb.error.ExStudentErrorMessage;
import com.taller.asb.interfaces.FirstValidation;
import com.taller.asb.interfaces.SecondValidation;
import com.taller.asb.manager.ExStudentManager;
import com.taller.asb.model.EntityUtil;
import com.taller.asb.model.ExStudent;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateExStudentFormDto {

	@NotNull(message = ExStudentErrorMessage.NOT_NULL_ID_EX_STUDENT, groups = FirstValidation.class)
	@EntityExistsValidator(
		manager = ExStudentManager.class,
		groups = SecondValidation.class,
		message = ExStudentErrorMessage.EX_STUDENT_NOT_EXISTS)
	private Integer idExStudent;
	
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
	
	@JsonProperty(required = true)
	@NotNull(message = ExStudentErrorMessage.NOT_NULL_DOCUMENT)
	@NotEmpty(message = ExStudentErrorMessage.NOT_EMPTY_DOCUMENT)
	@NotBlank(message = ExStudentErrorMessage.NOT_BLANK_DOCUMENT)
	@Size(max = ExStudent.MAX_DOCUMENT, message = ExStudentErrorMessage.SIZE_DOCUMENT)
	private String document;
	
	@Size(max = ExStudent.MAX_ADDRESS, message = ExStudentErrorMessage.SIZE_ADDRESS)
	private String address;
	
	@Size(max = ExStudent.MAX_PHONE, message = ExStudentErrorMessage.SIZE_PHONE)
	private String phone;
	
	@NotNull(message = ExStudentErrorMessage.NOT_NULL_HAS_DOCUMENT_COPY)
	private Boolean hasDocumentCopy;	
	
	@Size(min = ExStudent.LENTGH_PROMOTION, max = ExStudent.LENTGH_PROMOTION, message = ExStudentErrorMessage.SIZE_PROMOTION)
	private String promotion;
	
	@UniqueValueOnUpdate(
		field = ExStudent.FIELD_DOCUMENT, 
		manager = ExStudentManager.class,
		message = ExStudentErrorMessage.EXISTING_DOCUMENT)
	private EntityUtil getIdAndDocument() {
		return EntityUtil.builder().id(idExStudent).value(document).build();
	}
	
	@SuppressWarnings("unchecked")
	@JsonProperty("exStudent")
	@JsonIgnoreProperties(ignoreUnknown = true)
	private void getJsonProperties(Map<String, Object> exStudent) {
		
		Map<String, Object> data = (Map<String, Object>) exStudent.get("data");
		
		idExStudent      = (Integer) data.get("idExStudent");
		name             = (String)  data.get("name");
		lastName         = (String)  data.get("lastName");
		idDocumentType   = (Integer) data.get("idDocumentType");
		document         = (String)  data.get("document");
		address          = (String)  data.get("address");
		phone            = (String)  data.get("phone");
		hasDocumentCopy  = (Boolean) data.get("hasDocumentCopy");
		promotion        = (String)  data.get("promotion");
	}
}
