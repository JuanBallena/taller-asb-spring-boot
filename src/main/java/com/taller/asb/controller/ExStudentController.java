package com.taller.asb.controller;

import java.util.Map;

import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taller.asb.definition.ResponseDefinition;
import com.taller.asb.manager.ExStudentManager;
import com.taller.asb.response.ResponsePage;
import com.taller.asb.response.ResponseService;
import com.taller.asb.response.ResponseUtil;

@RestController
@Validated
public class ExStudentController {
	
	private static String NODE_EX_STUDENT_LIST = "ex_student_list";
	private static String NODE_EX_STUDENT = "ex_student";
	
	@Autowired
	private ExStudentManager exStudentManager;
	
	@GetMapping("/ex-student")
	public Map<String,Object> getExStudentList(
		@RequestParam(value = "search", defaultValue = "") String search,
		@RequestParam(value = "page", defaultValue = "0") Integer page,
		@RequestParam(value = "size", defaultValue = "0") Integer size
	) {
		
		ResponseService responseService = new ResponseService();
		responseService.setResponseCode(ResponseDefinition.RESPONSECODE_INTERNAL_SERVER_ERROR);
		responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_INTERNAL_SERVER_ERROR_S);
		
		try {
			ResponsePage responsePage = exStudentManager.getExStudentList(search, page, size);
			
			responseService.setData(responsePage.getData());
			responseService.setPages(responsePage.getTotalPages());
			responseService.setResponseCode(ResponseDefinition.RESPONSECODE_OK);
			responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_OK_S);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseUtil.buildResponse(NODE_EX_STUDENT_LIST, responseService);
	}
	
	@GetMapping("/ex-student/{idExStudent}")
	public Map<String, Object> getExStudentList(@PathVariable("idExStudent") @Positive Long idExStudent) {
		
		ResponseService responseService = new ResponseService();
		responseService.setResponseCode(ResponseDefinition.RESPONSECODE_INTERNAL_SERVER_ERROR);
		responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_INTERNAL_SERVER_ERROR_S);
		
		try {
			responseService.setData(exStudentManager.getExStudent(idExStudent));
			responseService.setResponseCode(ResponseDefinition.RESPONSECODE_OK);
			responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_OK_S);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseUtil.buildResponse(NODE_EX_STUDENT, responseService);
	}
}
