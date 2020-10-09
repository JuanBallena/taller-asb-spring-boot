package com.taller.asb.dto.ex_student;

import com.taller.asb.dto.ParameterDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class ExStudentDto {

	private Long id;
	private String name;
	private String lastName;
	private String urlLocationPhoto;
	private ParameterDto documentType;
	private String document;
	private String address;
	private String phone;
	private String promotion;
	private String hasDocumentCopy;
	private ParameterDto status;
	
}
