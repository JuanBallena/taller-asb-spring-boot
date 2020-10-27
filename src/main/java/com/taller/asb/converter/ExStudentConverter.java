package com.taller.asb.converter;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.taller.asb.dto.ParameterDto;
import com.taller.asb.dto.ex_student.ExStudentDto;
import com.taller.asb.model.ExStudent;

@Component
public class ExStudentConverter {

	public ExStudentDto toExStudentDto(ExStudent exStudent) {
		if (exStudent == null) {
			return null;
		}
		
		return ExStudentDto.builder()
				.id(exStudent.getIdExStudent())
				.name(exStudent.getName())
				.lastName(exStudent.getLastName())
				.urlLocationPhoto(exStudent.getUrlLocationPhoto())
				.documentType(ParameterDto.builder()
						.id(exStudent.getDocumentType().getIdParameter())
						.description(exStudent.getDocumentType().getDescription()).build())
				.document(exStudent.getDocument())
				.address(exStudent.getAddress())
				.phone(exStudent.getPhone())
				.promotion(exStudent.getPromotion())
				.status(ParameterDto.builder()
						.id(exStudent.getUser().getStatus().getIdParameter())
						.description(exStudent.getUser().getStatus().getDescription()).build())
				.build();
	}
	
	public Page<ExStudentDto> toExStudentDtoPage(Page<ExStudent> exStudentPage) {
		if (exStudentPage == null) {
			return null;
		}
		
		Page<ExStudentDto> exStudentDtoPage = exStudentPage.map((Function<ExStudent, ExStudentDto>) exStudent -> {
			return toExStudentDto(exStudent);
		});
		
		return exStudentDtoPage;
	}
	
	public List<ExStudentDto> toExStudentDtoList(List<ExStudent> exStudentList) {
		if (exStudentList == null) {
			return null;
		}
		
		List<ExStudentDto> exStudentDtoList = new LinkedList<ExStudentDto>();
		for (ExStudent exStudent : exStudentList) {
			exStudentDtoList.add(toExStudentDto(exStudent));
		}
		
		return exStudentDtoList;
	}
}
