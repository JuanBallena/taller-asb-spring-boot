package com.taller.asb.controller;

import javax.validation.Valid;
import javax.validation.constraints.Min;
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
import com.taller.asb.dto.student.CreateStudentFormDto;
import com.taller.asb.dto.student.StudentDto;
import com.taller.asb.dto.student.UpdatePhotoStudentFormDto;
import com.taller.asb.dto.student.UpdateStudentFormDto;
import com.taller.asb.error.UrlErrorMessage;
import com.taller.asb.manager.StudentManager;
import com.taller.asb.response.ResponsePage;
import com.taller.asb.response.ResponseService;

@RestController
@Validated
public class StudentController {
		
	@Autowired
	private StudentManager studentManager; 
	
	@GetMapping("/students")
	public ResponseService getStudentList(
		@RequestParam(value = "q", defaultValue = "") String query,
		@RequestParam(value = "page", defaultValue = "0") @Min(value = 0, message = UrlErrorMessage.MIN_PAGE) Integer page,
		@RequestParam(value = "size", defaultValue = "0") @Min(value = 0, message = UrlErrorMessage.MIN_SIZE) Integer size
	) {
		ResponseService responseService = new ResponseService();
		responseService.setResponseCode(ResponseDefinition.RESPONSECODE_INTERNAL_SERVER_ERROR);
		responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_INTERNAL_SERVER_ERROR_S);

		try {
			ResponsePage responsePage = studentManager.getStudentList(query, page, size);
			if (responsePage.getData().size() == 0) {
				responseService.setData(responsePage.getData());
				responseService.setResponseCode(ResponseDefinition.RESPONSECODE_NO_CONTENT);
				responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_NO_CONTENT_S);
			}
			else {
				responseService.setType(TypeDefinition.STUDENTS);
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
	
	@GetMapping("/students/{idStudent}")
	public ResponseService getStudent(
		@PathVariable("idStudent") @Positive(message = UrlErrorMessage.POSITIVE_ID) Long idStudent
	) {
		ResponseService responseService = new ResponseService();
		responseService.setResponseCode(ResponseDefinition.RESPONSECODE_INTERNAL_SERVER_ERROR);
		responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_INTERNAL_SERVER_ERROR_S);

		try {
			StudentDto studentDto = studentManager.getStudent(idStudent);
			if (studentDto == null) {
				responseService.setResponseCode(ResponseDefinition.RESPONSECODE_NO_CONTENT);
				responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_NO_CONTENT_S);
			}
			else {
				responseService.setType(TypeDefinition.STUDENT);
				responseService.setData(studentDto);
				responseService.setResponseCode(ResponseDefinition.RESPONSECODE_OK);
				responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_OK_S);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return responseService;
	}
	
	@PostMapping("/students")
	public ResponseService saveStudent(
		@Valid @RequestBody CreateStudentFormDto createStudentFormDto
	) {
		ResponseService responseService = new ResponseService();
		responseService.setResponseCode(ResponseDefinition.RESPONSECODE_INTERNAL_SERVER_ERROR);
		responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_INTERNAL_SERVER_ERROR_S);

		try {
			StudentDto studentDto = studentManager.saveStudent(createStudentFormDto);
			
			if (studentDto == null) {
				responseService.setResponseCode(ResponseDefinition.RESPONSECODE_SERVICE_UNAVAILABLE);
				responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_SERVICE_UNAVAILABLE_S);
			}
			else {
				responseService.setType(TypeDefinition.STUDENT);
				responseService.setData(studentDto);
				responseService.setResponseCode(ResponseDefinition.RESPONSECODE_CREATED);
				responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_CREATED_S);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return responseService;
	}
	
	@PutMapping("/students/{idStudent}")
	public ResponseService updateStudent(
		@Valid @RequestBody UpdateStudentFormDto updateStudentFormDto,
		@PathVariable("idStudent") @Positive(message = UrlErrorMessage.POSITIVE_ID) Long idStudent
	) {
		ResponseService responseService = new ResponseService();
		responseService.setResponseCode(ResponseDefinition.RESPONSECODE_INTERNAL_SERVER_ERROR);
		responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_BAD_REQUEST_S);
		
		try {
			if (idStudent != Long.valueOf(updateStudentFormDto.getIdStudent())) {
				responseService.setResponseCode(ResponseDefinition.RESPONSECODE_UNPROCESSABLE_ENTITY);
				responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_UNPROCESSABLE_ENTITY_S);
			}
			
			StudentDto studentDto = studentManager.updateStudent(idStudent, updateStudentFormDto);
			
			if (studentDto == null) {
				responseService.setResponseCode(ResponseDefinition.RESPONSECODE_NO_CONTENT);
				responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_NO_CONTENT_S);
			}
			else {
				responseService.setType(TypeDefinition.STUDENT);
				responseService.setData(studentDto);
				responseService.setResponseCode(ResponseDefinition.RESPONSECODE_CREATED);
				responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_CREATED_S);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return responseService;
	}
	
	@PutMapping("/students/actions/photo/{idStudent}")
	public ResponseService updatePhoto(
		@Valid @RequestBody UpdatePhotoStudentFormDto updatePhotoStudentFormDto,
		@PathVariable("idStudent") @Positive(message = UrlErrorMessage.POSITIVE_ID) Long idStudent
	) {
		ResponseService responseService =  new ResponseService();
		responseService.setResponseCode(ResponseDefinition.RESPONSECODE_INTERNAL_SERVER_ERROR);
		responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_INTERNAL_SERVER_ERROR_S);
		
		try {
			StudentDto studentDto = studentManager.updatePhoto(idStudent, updatePhotoStudentFormDto);
			
			if (studentDto == null) {
				responseService.setResponseCode(ResponseDefinition.RESPONSECODE_NO_CONTENT);
				responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_NO_CONTENT_S);
			}
			else {
				responseService.setType(TypeDefinition.STUDENT);
				responseService.setData(studentDto);
				responseService.setResponseCode(ResponseDefinition.RESPONSECODE_NO_CONTENT);
				responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_NO_CONTENT_S);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return responseService;
	}
}
