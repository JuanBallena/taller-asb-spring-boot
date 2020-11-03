package com.taller.asb.converter;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.taller.asb.dto.parameter.ParameterDto;
import com.taller.asb.model.Parameter;

@Component
public class ParameterConverter {

	public ParameterDto toParameterDto(Parameter parameter) {
		if (parameter == null) return null;
		
		return ParameterDto.builder()
				.id(parameter.getIdParameter())
				.description(parameter.getDescription())
				.build();
	}
	
	public List<ParameterDto> toParameterDtoList(List<Parameter> parameterList) {
		
		List<ParameterDto> parameterDtoList = new LinkedList<ParameterDto>();
		
		for (Parameter parameter : parameterList) {
			parameterDtoList.add(toParameterDto(parameter));
		}
		
		return parameterDtoList;
	}
}
