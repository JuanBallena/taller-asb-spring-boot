package com.taller.asb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taller.asb.definition.ResponseDefinition;
import com.taller.asb.definition.TypeDefinition;
import com.taller.asb.dto.role.RoleDto;
import com.taller.asb.manager.RoleManager;
import com.taller.asb.response.ResponseService;

@RestController
public class RoleController {

	@Autowired
	private RoleManager roleManager;
	
	@GetMapping("/roles")
	public ResponseService getRoleList() {
		ResponseService responseService = new ResponseService();
		responseService.setResponseCode(ResponseDefinition.RESPONSECODE_INTERNAL_SERVER_ERROR);
		responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_INTERNAL_SERVER_ERROR_S);
		
		try {
			List<RoleDto> roleDtoList = roleManager.getRoleList();
			
			responseService.setType(TypeDefinition.ROLES);
			responseService.setData(roleDtoList);
			responseService.setPages(roleDtoList.size() == 0 ? 0 : 1);
			responseService.setResponseCode(ResponseDefinition.RESPONSECODE_OK);
			responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_OK_S);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return responseService;
	}
}
