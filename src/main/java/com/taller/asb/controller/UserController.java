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
import com.taller.asb.dto.user.CreateUserFormDto;
import com.taller.asb.dto.user.UpdateChangePasswordUserFormDto;
import com.taller.asb.dto.user.UpdateUserFormDto;
import com.taller.asb.dto.user.UserDto;
import com.taller.asb.error.UrlErrorMessage;
import com.taller.asb.interfaces.SequenceValidation;
import com.taller.asb.manager.UserManager;
import com.taller.asb.response.ResponsePage;
import com.taller.asb.response.ResponseService;

@RestController
@Validated
public class UserController {
	
	@Autowired
	private UserManager userManager;
	
	@GetMapping("/users")
	public ResponseService getUserList(
		@RequestParam(value="q", defaultValue = "") String query,
		@RequestParam(value="page", defaultValue = "0") @Min(message = UrlErrorMessage.MIN_PAGE , value = 0) Integer page,
		@RequestParam(value="size", defaultValue = "0") @Min(message = UrlErrorMessage.MIN_SIZE, value = 0) Integer size
	) {
		ResponseService responseService = new ResponseService();
		responseService.setResponseCode(ResponseDefinition.RESPONSECODE_INTERNAL_SERVER_ERROR);
		responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_INTERNAL_SERVER_ERROR_S);
		
		try {
			ResponsePage responsePage = userManager.getUserList(query, page, size);
			
			if (responsePage.getData().size() == 0) {
				responseService.setData(responsePage.getData());
				responseService.setResponseCode(ResponseDefinition.RESPONSECODE_NO_CONTENT);
				responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_NO_CONTENT_S);
			}
			else {
				responseService.setType(TypeDefinition.USERS);
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
	
	@GetMapping("/users/{idUser}")
	public ResponseService getUser(
		@PathVariable("idUser") @Positive(message = UrlErrorMessage.POSITIVE_ID) Long idUser
	) {
		ResponseService responseService = new ResponseService();
		responseService.setResponseCode(ResponseDefinition.RESPONSECODE_INTERNAL_SERVER_ERROR);
		responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_INTERNAL_SERVER_ERROR_S);
		
		try {
			UserDto userDto = userManager.getUser(idUser);
			
			if (userDto == null) {
				responseService.setResponseCode(ResponseDefinition.RESPONSECODE_NO_CONTENT);
				responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_NO_CONTENT_S);
			}
			else {
				responseService.setType(TypeDefinition.USER);
				responseService.setData(userDto);
				responseService.setResponseCode(ResponseDefinition.RESPONSECODE_OK);
				responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_OK_S);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return responseService;
	}
	
	@PostMapping("/users")
	public ResponseService saveUser(
		@Valid @RequestBody CreateUserFormDto createUserFormDto
	) {
		ResponseService responseService = new ResponseService();
		responseService.setResponseCode(ResponseDefinition.RESPONSECODE_INTERNAL_SERVER_ERROR);
		responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_INTERNAL_SERVER_ERROR_S);

		try {
			UserDto userDto = userManager.saveUser(createUserFormDto);
			
			if (userDto == null) {
				responseService.setResponseCode(ResponseDefinition.RESPONSECODE_SERVICE_UNAVAILABLE);
				responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_SERVICE_UNAVAILABLE_S);
			}
			else {
				responseService.setType(TypeDefinition.USER);
				responseService.setData(userDto);
				responseService.setResponseCode(ResponseDefinition.RESPONSECODE_CREATED);
				responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_CREATED_S);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return responseService;
	}
	
	@PutMapping("/users/{idUser}")
	public ResponseService updateUser(
		@Validated(SequenceValidation.class) @RequestBody UpdateUserFormDto updateUserFormDto,
		@PathVariable("idUser") @Positive(message = UrlErrorMessage.POSITIVE_ID) Long idUser
	) {
		ResponseService responseService = new ResponseService();
		responseService.setResponseCode(ResponseDefinition.RESPONSECODE_INTERNAL_SERVER_ERROR);
		responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_INTERNAL_SERVER_ERROR_S);
		
		try {
			if (idUser != Long.valueOf(updateUserFormDto.getIdUser())) {
				responseService.setResponseCode(ResponseDefinition.RESPONSECODE_UNPROCESSABLE_ENTITY);
				responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_UNPROCESSABLE_ENTITY_S);
				return responseService;
			}
			
			UserDto userDto = userManager.updateUser(idUser, updateUserFormDto);
			
			if (userDto == null) {
				responseService.setResponseCode(ResponseDefinition.RESPONSECODE_NO_CONTENT);
				responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_NO_CONTENT_S);
			} 
			else {
				responseService.setType(TypeDefinition.USER);
				responseService.setData(userDto);
				responseService.setResponseCode(ResponseDefinition.RESPONSECODE_CREATED);
				responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_CREATED_S);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return responseService;
	}
	
	@PutMapping("/users/actions/change_password/{idUser}")
	public ResponseService updateChangePassword(
		@Valid @RequestBody UpdateChangePasswordUserFormDto updateChangePasswordUserFormDto,
		@PathVariable("idUser") @Positive(message = UrlErrorMessage.POSITIVE_ID) Long idUser
	) {
		ResponseService responseService = new ResponseService();
		responseService.setResponseCode(ResponseDefinition.RESPONSECODE_INTERNAL_SERVER_ERROR);
		responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_INTERNAL_SERVER_ERROR_S);

		try {
			UserDto userDto = userManager.updateChangePassword(idUser, updateChangePasswordUserFormDto);
			
			if (userDto == null) {
				responseService.setResponseCode(ResponseDefinition.RESPONSECODE_NO_CONTENT);
				responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_NO_CONTENT_S);
			}
			else {
				responseService.setType(TypeDefinition.USER);
				responseService.setData(userDto);
				responseService.setResponseCode(ResponseDefinition.RESPONSECODE_CREATED);
				responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_CREATED_S);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return responseService;
	}
}
