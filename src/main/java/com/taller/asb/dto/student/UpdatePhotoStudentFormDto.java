package com.taller.asb.dto.student;

//import java.io.File;
import java.util.Map;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePhotoStudentFormDto {

	@NotNull
	private Integer idStudent;
	
	@NotNull
	@NotBlank
	@NotEmpty
	@Size(max = 2)
	private String photo;
	
	@SuppressWarnings("unchecked")
	@JsonProperty("student")
	@JsonIgnoreProperties(ignoreUnknown = true)
	private void getJsonProperties(Map<String, Object> student) {
		
		Map<String, Object> data = (Map<String, Object>) student.get("data");
		
		idStudent = (Integer) data.get("idStudent");
		photo     = (String)  data.get("photo");
	}
}
