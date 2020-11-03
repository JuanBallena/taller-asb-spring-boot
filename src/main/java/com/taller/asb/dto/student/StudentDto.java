package com.taller.asb.dto.student;

import com.taller.asb.dto.parameter.ParameterDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class StudentDto {
	
	private Long id;
	private String name;
	private String lastName;
	private String urlLocationPhoto;
	private ParameterDto documentType;
	private String document;
	private String address;
	private String phone;
	private Boolean hasDocumentCopy;
	private Boolean suspended;
	private ParameterDto status;
	private Object links;
}
