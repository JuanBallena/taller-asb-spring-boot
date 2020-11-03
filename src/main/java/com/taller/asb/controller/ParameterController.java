package com.taller.asb.controller;

import java.util.List;

import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taller.asb.definition.ResponseDefinition;
import com.taller.asb.definition.TypeDefinition;
import com.taller.asb.dto.parameter.ParameterDto;
import com.taller.asb.error.UrlErrorMessage;
import com.taller.asb.manager.ParameterManager;
import com.taller.asb.response.ResponseService;

@RestController
@Validated
public class ParameterController {

	@Autowired
	private ParameterManager parameterManager;
	
	@GetMapping("/parameters")
	public ResponseService getParameterList(
		@RequestParam(value="idParameterType") @Positive(message = UrlErrorMessage.POSITIVE_ID) Integer idParameterType
	) {
		ResponseService responseService = new ResponseService();
		responseService.setResponseCode(ResponseDefinition.RESPONSECODE_INTERNAL_SERVER_ERROR);
		responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_INTERNAL_SERVER_ERROR_S);
		
		try {
			List<ParameterDto> parameterDtoList = parameterManager.getParameterList(Long.valueOf(idParameterType));
			
			if (parameterDtoList.size() == 0) {
				responseService.setData(parameterDtoList);
				responseService.setResponseCode(ResponseDefinition.RESPONSECODE_NO_CONTENT);
				responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_NO_CONTENT_S);
			}
			else {
				responseService.setType(TypeDefinition.PARAMETERS);
				responseService.setData(parameterDtoList);
				responseService.setPages(parameterDtoList.size() == 0 ? 0 : 1);
				responseService.setResponseCode(ResponseDefinition.RESPONSECODE_OK);
				responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_OK_S);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return responseService;
	}
}
