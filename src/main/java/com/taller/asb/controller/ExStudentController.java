package com.taller.asb.controller;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taller.asb.definition.ResponseDefinition;
import com.taller.asb.definition.TypeDefinition;
import com.taller.asb.dto.ex_student.CreateExStudentFormDto;
import com.taller.asb.dto.ex_student.ExStudentDto;
import com.taller.asb.dto.ex_student.UpdateExStudentFormDto;
import com.taller.asb.dto.ex_student.UpdatePhotoExStudentFormDto;
import com.taller.asb.error.UrlErrorMessage;
import com.taller.asb.manager.ExStudentManager;
import com.taller.asb.response.ResponsePage;
import com.taller.asb.response.ResponseService;

@RestController
@Validated
public class ExStudentController {

	
	@Autowired
	private ExStudentManager exStudentManager;
	
	@GetMapping("/ex_students")
	public ResponseService getExStudentList(
		@RequestParam(value = "q", defaultValue = "") String query,
		@RequestParam(value = "page", defaultValue = "0") Integer page,
		@RequestParam(value = "size", defaultValue = "0") Integer size
	) {
		ResponseService responseService = new ResponseService();
		responseService.setResponseCode(ResponseDefinition.RESPONSECODE_INTERNAL_SERVER_ERROR);
		responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_INTERNAL_SERVER_ERROR_S);
		
		try {
			ResponsePage responsePage = exStudentManager.getExStudentList(query, page, size);
			
			if (responsePage.getData().size() == 0) {
				responseService.setData(responsePage.getData());
				responseService.setResponseCode(ResponseDefinition.RESPONSECODE_NO_CONTENT);
				responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_NO_CONTENT_S);
			}
			else {
				responseService.setType(TypeDefinition.EX_STUDENTS);
				responseService.setData(responsePage.getData());
				responseService.setPages(responsePage.getTotalPages());
				responseService.setResponseCode(ResponseDefinition.RESPONSECODE_OK);
				responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_OK_S);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return responseService;
	}
	
	@GetMapping("/ex_students/{idExStudent}")
	public ResponseService getExStudentList(
		@PathVariable("idExStudent") @Positive(message = UrlErrorMessage.POSITIVE_ID) Long idExStudent
	) {	
		ResponseService responseService = new ResponseService();
		responseService.setResponseCode(ResponseDefinition.RESPONSECODE_INTERNAL_SERVER_ERROR);
		responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_INTERNAL_SERVER_ERROR_S);
		
		try {
			ExStudentDto exStudentDto = exStudentManager.getExStudent(idExStudent);
			
			if (exStudentDto == null) {
				responseService.setResponseCode(ResponseDefinition.RESPONSECODE_NO_CONTENT);
				responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_NO_CONTENT_S);
			}
			else {
				responseService.setType(TypeDefinition.EX_STUDENT);
				responseService.setData(exStudentDto);
				responseService.setResponseCode(ResponseDefinition.RESPONSECODE_OK);
				responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_OK_S);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return responseService;
	}
	
	@PostMapping("/ex_students")
	public ResponseService saveExStudent(
		@Valid @RequestBody CreateExStudentFormDto createExStudentFormDto
	) {
		ResponseService responseService = new ResponseService();
		responseService.setResponseCode(ResponseDefinition.RESPONSECODE_INTERNAL_SERVER_ERROR);
		responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_INTERNAL_SERVER_ERROR_S);

		try {
			ExStudentDto exStudentDto = exStudentManager.saveExStudent(createExStudentFormDto);
			
			if (exStudentDto == null) {
				responseService.setResponseCode(ResponseDefinition.RESPONSECODE_SERVICE_UNAVAILABLE);
				responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_SERVICE_UNAVAILABLE_S);
			}
			else {
				responseService.setType(TypeDefinition.EX_STUDENT);
				responseService.setData(exStudentDto);
				responseService.setResponseCode(ResponseDefinition.RESPONSECODE_CREATED);
				responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_CREATED_S);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return responseService;
	}
	
	@PutMapping("/ex_students/{idExStudent}")
	public ResponseService updateExStudent(
		@Valid @RequestBody UpdateExStudentFormDto updateExStudentFormDto,
		@PathVariable("idExStudent") @Positive(message = UrlErrorMessage.POSITIVE_ID) Long idExStudent
	) {
		ResponseService responseService = new ResponseService();
		responseService.setResponseCode(ResponseDefinition.RESPONSECODE_INTERNAL_SERVER_ERROR);
		responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_BAD_REQUEST_S);
		
		try {
			if (idExStudent != Long.valueOf(updateExStudentFormDto.getIdExStudent())) {
				responseService.setResponseCode(ResponseDefinition.RESPONSECODE_UNPROCESSABLE_ENTITY);
				responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_UNPROCESSABLE_ENTITY_S);
			}
			
			ExStudentDto exStudentDto = exStudentManager.updateExStudent(idExStudent, updateExStudentFormDto);
			
			if (exStudentDto == null) {
				responseService.setResponseCode(ResponseDefinition.RESPONSECODE_NO_CONTENT);
				responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_NO_CONTENT_S);
			}
			else {
				responseService.setType(TypeDefinition.EX_STUDENT);
				responseService.setData(exStudentDto);
				responseService.setResponseCode(ResponseDefinition.RESPONSECODE_CREATED);
				responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_CREATED_S);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return responseService;
	}
	
	@PutMapping("/ex_students/actions/photo/{idExStudent}")
	public ResponseService updatePhoto(
		@Valid @RequestBody UpdatePhotoExStudentFormDto updatePhotoExStudentFormDto,
		@PathVariable("idExStudent") @Positive(message = UrlErrorMessage.POSITIVE_ID) Long idExStudent
	) {
		ResponseService responseService =  new ResponseService();
		responseService.setResponseCode(ResponseDefinition.RESPONSECODE_INTERNAL_SERVER_ERROR);
		responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_INTERNAL_SERVER_ERROR_S);
		
		try {
			ExStudentDto exStudentDto = exStudentManager.updatePhoto(idExStudent, updatePhotoExStudentFormDto);
			
			if (exStudentDto == null) {
				responseService.setResponseCode(ResponseDefinition.RESPONSECODE_NO_CONTENT);
				responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_NO_CONTENT_S);
			}
			else {
				responseService.setType(TypeDefinition.EX_STUDENT);
				responseService.setData(exStudentDto);
				responseService.setResponseCode(ResponseDefinition.RESPONSECODE_NO_CONTENT);
				responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_NO_CONTENT_S);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return responseService;
	}
}
