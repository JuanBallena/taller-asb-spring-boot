package com.taller.asb.converter;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.taller.asb.dto.ex_student.CreateExStudentFormDto;
import com.taller.asb.dto.ex_student.ExStudentDto;
import com.taller.asb.dto.ex_student.UpdateExStudentFormDto;
import com.taller.asb.dto.parameter.ParameterDto;
import com.taller.asb.model.ExStudent;
import com.taller.asb.model.Parameter;
import com.taller.asb.model.User;

@Component
public class ExStudentConverter {

	public ExStudentDto toExStudentDto(ExStudent exStudent) {
		if (exStudent == null) return null;
		
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
				.hasDocumentCopy(exStudent.getHasDocumentCopy())
				.status(ParameterDto.builder()
						.id(exStudent.getUser().getStatus().getIdParameter())
						.description(exStudent.getUser().getStatus().getDescription())
						.build())
				.build();
	}
	
	public ExStudent toExStudentModel(CreateExStudentFormDto createExStudentFormDto, User user) {
		return ExStudent.builder()
				.user(user)
				.name(createExStudentFormDto.getName())
				.lastName(createExStudentFormDto.getLastName())
				.documentType(Parameter.builder()
						.idParameter(Long.valueOf(createExStudentFormDto.getIdDocumentType()))
						.build())
				.document(createExStudentFormDto.getDocument())
				.address(createExStudentFormDto.getAddress())
				.phone(createExStudentFormDto.getPhone())
				.promotion(createExStudentFormDto.getPromotion())
				.build();
	}
	
	public ExStudent replaceValuesInExStudentModel(ExStudent exStudent, UpdateExStudentFormDto updateExStudentFormDto) {
		
		exStudent.setName(updateExStudentFormDto.getName());
		exStudent.setLastName(updateExStudentFormDto.getLastName());
		exStudent.setDocumentType(Parameter.builder()
				.idParameter(Long.valueOf(updateExStudentFormDto.getIdDocumentType()))
				.build());
		exStudent.setDocument(updateExStudentFormDto.getDocument());
		exStudent.setAddress(updateExStudentFormDto.getAddress());
		exStudent.setPhone(updateExStudentFormDto.getPhone());
		exStudent.setHasDocumentCopy(updateExStudentFormDto.getHasDocumentCopy());
		exStudent.setPromotion(updateExStudentFormDto.getPromotion());
		
		return exStudent;
	}
	
	public Page<ExStudentDto> toExStudentDtoPage(Page<ExStudent> exStudentPage) {
		
		Page<ExStudentDto> exStudentDtoPage = exStudentPage.map((Function<ExStudent, ExStudentDto>) exStudent -> {
			return toExStudentDto(exStudent);
		});
		
		return exStudentDtoPage;
	}
	
	public List<ExStudentDto> toExStudentDtoList(List<ExStudent> exStudentList) {
		
		List<ExStudentDto> exStudentDtoList = new LinkedList<ExStudentDto>();
		for (ExStudent exStudent : exStudentList) {
			exStudentDtoList.add(toExStudentDto(exStudent));
		}
		
		return exStudentDtoList;
	}
}
