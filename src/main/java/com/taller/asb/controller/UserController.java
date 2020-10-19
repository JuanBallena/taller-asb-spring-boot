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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taller.asb.definition.ResponseDefinition;
import com.taller.asb.dto.user.CreateUserFormDto;
import com.taller.asb.dto.user.UpdateUserFormDto;
import com.taller.asb.dto.user.UserDto;
import com.taller.asb.manager.UserManager;
import com.taller.asb.response.ResponsePage;
import com.taller.asb.response.ResponseService;
import com.taller.asb.response.ResponseUtil;

@RestController
@Validated
public class UserController {
	
	//private static final String NODE_USER_LIST = "user_list";
	private static final String NODE_USER = "user";

	@Autowired
	private UserManager userManager;
	
	@GetMapping("/users")
	public ResponseService getUserList(
		@RequestParam(value="search", defaultValue = "") String search,
		@RequestParam(value="page", defaultValue = "0") @Min(0) Integer page,
		@RequestParam(value="size", defaultValue = "0") @Min(0) Integer size
	) {
		
		ResponseService responseService = new ResponseService();
		responseService.setResponseCode(ResponseDefinition.RESPONSECODE_ERROR_GENERAL);
		responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_ERROR_GENERAL_S);
		
		try {
			ResponsePage responsePage = userManager.getUserList(search, page, size);
			
//			if (responsePage.getData().size() == 0) {
//				responseService.setData(responsePage.getData());
//				responseService.setResponseCode(ResponseDefinition.RESPONSECODE_NO_CONTENT);
//				responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_NO_CONTENT_S);
//			}
//			else {
				responseService.setData(responsePage.getData());
				responseService.setPages(responsePage.getTotalPages());
				responseService.setResponseCode(ResponseDefinition.RESPONSECODE_OK);
				responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_OK_S);
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return responseService;
		//return ResponseUtil.buildResponse(NODE_USER_LIST, responseService);
	}
	
	@GetMapping("/users/{idUser}")
	public Map<String, Object> getUser(
		@PathVariable("idUser") @Positive(message = "Id de usuario debe ser mayor a 0") Long idUser
	) {
		ResponseService responseService = new ResponseService();
		responseService.setResponseCode(ResponseDefinition.RESPONSECODE_ERROR_GENERAL);
		responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_ERROR_GENERAL_S);
		
		try {
			
			UserDto userDto = userManager.getUser(idUser);	
			
			if (userDto != null) {
				responseService.setData(userDto);
				responseService.setResponseCode(ResponseDefinition.RESPONSECODE_OK);
				responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_OK_S);
			} else {
				responseService.setResponseCode(ResponseDefinition.RESPONSECODE_NO_CONTENT);
				responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_NO_CONTENT_S);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseUtil.buildResponse(NODE_USER, responseService);
	}
	
	@PostMapping("/users")
	public Map<String, Object> saveUser(
		@Valid @RequestBody CreateUserFormDto createUserFormDto
	) {

		ResponseService responseService = new ResponseService();
		responseService.setResponseCode(ResponseDefinition.RESPONSECODE_ERROR_GENERAL);
		responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_ERROR_GENERAL_S);

		try {
			responseService.setData(userManager.saveUser(createUserFormDto));
			responseService.setResponseCode(ResponseDefinition.RESPONSECODE_CREATED);
			responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_CREATED_S);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseUtil.buildResponse(NODE_USER, responseService);
	}
	
	@PutMapping("/users/{idUser}")
	public Map<String, Object> updateUser(
		@Valid @RequestBody UpdateUserFormDto updateUserFormDto,
		@PathVariable("idUser") @Positive(message = "Id de usuario debe ser mayor a 0") Long idUser
	) {
		
		ResponseService responseService = new ResponseService();
		
		try {
			UserDto userDto = userManager.getUser(idUser);
			
			if (userDto != null) {
				responseService.setData(userManager.updateUser(updateUserFormDto));
				responseService.setResponseCode(ResponseDefinition.RESPONSECODE_CREATED);
				responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_CREATED_S);
			} else {
				responseService.setResponseCode(ResponseDefinition.RESPONSECODE_NO_CONTENT);
				responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_NO_CONTENT_S);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseUtil.buildResponse(NODE_USER, responseService);
	}
}
