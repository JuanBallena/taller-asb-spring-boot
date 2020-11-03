package com.taller.asb.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taller.asb.converter.ParameterConverter;
import com.taller.asb.dto.parameter.ParameterDto;
import com.taller.asb.model.Parameter;
import com.taller.asb.model.ParameterType;
import com.taller.asb.repository.ParameterRepository;

@Service
public class ParameterManager {

	@Autowired
	private ParameterRepository parameterRepository;
	
	@Autowired
	private ParameterConverter parameterConverter;
	
	public List<ParameterDto> getParameterList(Long idParameterType) {
		
		ParameterType parameterType = ParameterType.builder()
				.idParameterType(idParameterType)
				.build();
		
		List<Parameter> parameterList = parameterRepository.findByParameterType(parameterType);
		List<ParameterDto> parameterDtoList = parameterConverter.toParameterDtoList(parameterList);
		return parameterDtoList;
	}
}
