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
import com.taller.asb.dto.user.CreateUserFormDto;
import com.taller.asb.dto.user.UpdateUserFormDto;
import com.taller.asb.dto.user.UpdateChangePasswordFormDto;
import com.taller.asb.dto.user.UserDto;
import com.taller.asb.error.UriErrorMessages;
import com.taller.asb.interfaces.SequenceValidation;
import com.taller.asb.manager.UserManager;
import com.taller.asb.response.ResponsePage;
import com.taller.asb.response.ResponseService;

@RestController
@Validated
public class UserController {
	
	private static final String USERS = "Users";
	private static final String USER = "User";

	@Autowired
	private UserManager userManager;
	
	@GetMapping("/users")
	public ResponseService getUserList(
		@RequestParam(value="q", defaultValue = "") String query,
		@RequestParam(value="page", defaultValue = "0") @Min(message = UriErrorMessages.MIN_PAGE_ERROR_MESSAGE , value = 0) Integer page,
		@RequestParam(value="size", defaultValue = "0") @Min(message = UriErrorMessages.MIN_SIZE_ERROR_MESSAGE, value = 0) Integer size
	) {
		
		ResponseService responseService = new ResponseService();
		responseService.setType(USERS);
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
		@PathVariable("idUser") @Positive(message = UriErrorMessages.POSITIVE_ID_ERROR_MESSAGE) Long idUser
	) {
		ResponseService responseService = new ResponseService();
		responseService.setType(USER);
		responseService.setResponseCode(ResponseDefinition.RESPONSECODE_INTERNAL_SERVER_ERROR);
		responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_INTERNAL_SERVER_ERROR_S);
		
		try {
			
			UserDto userDto = userManager.getUser(idUser);
			if (userDto == null) {
				responseService.setResponseCode(ResponseDefinition.RESPONSECODE_NO_CONTENT);
				responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_NO_CONTENT_S);
			}
			else {
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
		responseService.setType(USER);
		responseService.setResponseCode(ResponseDefinition.RESPONSECODE_INTERNAL_SERVER_ERROR);
		responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_INTERNAL_SERVER_ERROR_S);

		try {
			UserDto userDto = userManager.saveUser(createUserFormDto);
			if (userDto == null) {
				responseService.setResponseCode(ResponseDefinition.RESPONSECODE_SERVICE_UNAVAILABLE);
				responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_SERVICE_UNAVAILABLE_S);
			}
			else {
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
		@PathVariable("idUser") @Positive(message = UriErrorMessages.POSITIVE_ID_ERROR_MESSAGE) Long idUser
	) {
		ResponseService responseService = new ResponseService();
		responseService.setType(USER);
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
		@Valid @RequestBody UpdateChangePasswordFormDto updateChangePasswordFormDto,
		@PathVariable("idUser") @Positive(message = UriErrorMessages.POSITIVE_ID_ERROR_MESSAGE) Long idUser
	) {
		ResponseService responseService = new ResponseService();
		responseService.setType(USER);
		responseService.setResponseCode(ResponseDefinition.RESPONSECODE_INTERNAL_SERVER_ERROR);
		responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_INTERNAL_SERVER_ERROR_S);

		try {
			UserDto userDto = userManager.updateChangePassword(idUser, updateChangePasswordFormDto);
			if (userDto == null) {
				responseService.setResponseCode(ResponseDefinition.RESPONSECODE_NO_CONTENT);
				responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_NO_CONTENT_S);
			}
			else {
				responseService.setData(userDto);
				responseService.setResponseCode(ResponseDefinition.RESPONSECODE_OK);
				responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_OK_S);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return responseService;
	}
}
