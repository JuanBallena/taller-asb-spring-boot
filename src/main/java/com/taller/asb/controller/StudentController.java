package com.taller.asb.controller;

import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taller.asb.definition.ResponseDefinition;
import com.taller.asb.dto.student.CreateStudentDto;
import com.taller.asb.manager.StudentManager;
import com.taller.asb.response.ResponsePage;
import com.taller.asb.response.ResponseService;
import com.taller.asb.response.ResponseUtil;

@RestController
@Validated
public class StudentController {
	
	private static final String NODE_STUDENT_LIST = "student_list";
	private static final String NODE_STUDENT = "student";
	
	@Autowired
	private StudentManager studentManager; 
	
	@GetMapping("/students")
	public Map<String,Object> getStudentList(
		@RequestParam(value = "search", defaultValue = "") String search,
		@RequestParam(value = "page", defaultValue = "0") 
		@Min(value = 0, message = "Página debe ser igual o mayor a 0.") Integer page,
		@RequestParam(value = "size", defaultValue = "0") 
		@Min(value = 0, message = "Límite debe ser igual o mayor a 0.") Integer size
	) {
		
		ResponseService responseService = new ResponseService();
		responseService.setResponseCode(ResponseDefinition.RESPONSECODE_INTERNAL_SERVER_ERROR);
		responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_INTERNAL_SERVER_ERROR_S);

		try {
			ResponsePage responsePage = studentManager.getStudentList(search, page, size);
			
			responseService.setData(responsePage.getData());
			responseService.setPages(responsePage.getTotalPages());
			responseService.setResponseCode(ResponseDefinition.RESPONSECODE_OK);
			responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_OK_S);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseUtil.buildResponse(NODE_STUDENT_LIST, responseService);
	}
	
	@GetMapping("/students/{idStudent}")
	public Map<String,Object> getStudent(@PathVariable("idStudent") @Positive Long idStudent) {
		
		ResponseService responseService = new ResponseService();
		responseService.setResponseCode(ResponseDefinition.RESPONSECODE_INTERNAL_SERVER_ERROR);
		responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_INTERNAL_SERVER_ERROR_S);

		try {
			
			responseService.setData(studentManager.getStudent(idStudent));
			responseService.setResponseCode(ResponseDefinition.RESPONSECODE_OK);
			responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_OK_S);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseUtil.buildResponse(NODE_STUDENT, responseService);
	}
	
	@PostMapping("/students")
	public Map<String,Object> saveStudent(@Valid @RequestBody CreateStudentDto createStudentDto) {

		ResponseService responseService = new ResponseService();
		responseService.setResponseCode(ResponseDefinition.RESPONSECODE_INTERNAL_SERVER_ERROR);
		responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_INTERNAL_SERVER_ERROR_S);

		try {
			responseService.setData(studentManager.saveStudent(createStudentDto));
			responseService.setResponseCode(ResponseDefinition.RESPONSECODE_OK);
			responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_OK_S);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseUtil.buildResponse(NODE_STUDENT, responseService);
	}
}
