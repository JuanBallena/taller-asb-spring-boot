package com.taller.asb.controller;

import java.util.Map;

import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taller.asb.definition.ResponseDefinition;
import com.taller.asb.manager.ThemeManager;
import com.taller.asb.response.ResponsePage;
import com.taller.asb.response.ResponseService;
import com.taller.asb.response.ResponseUtil;

@RestController
public class ThemeController {
	
	private static String NODE_THEME_LIST = "theme_list";
	private static String NODE_THEME = "theme";
	
	@Autowired
	private ThemeManager themeManager;

	@GetMapping("/themes")
	public Map<String, Object> getThemeList(
		@RequestParam(value="title", defaultValue="") String title,
		@RequestParam(value="size", defaultValue="0") int size,
		@RequestParam(value="page", defaultValue="0") int page
	) {
		ResponseService responseService = new ResponseService();
		responseService.setResponseCode(ResponseDefinition.RESPONSECODE_ERROR_GENERAL);
		responseService.setData(ResponseDefinition.RESPONSECODE_ERROR_GENERAL_S);
		
		try {
			ResponsePage responsePage = themeManager.getThemeList(title, size, page);
			responseService.setData(responsePage.getData());
			responseService.setPages(responsePage.getTotalPages());
			responseService.setResponseCode(ResponseDefinition.RESPONSECODE_OK);
			responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_OK_S);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseUtil.buildResponse(NODE_THEME_LIST, responseService);
	}
	
	@GetMapping("/themes/{idTheme}")
	public Map<String, Object> getUser(
		@PathVariable("idTheme") @Positive Long idTheme
	) {
		ResponseService responseService = new ResponseService();
		responseService.setResponseCode(ResponseDefinition.RESPONSECODE_ERROR_GENERAL);
		responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_ERROR_GENERAL_S);
		
		try {
			responseService.setData(themeManager.getTheme(idTheme));
			responseService.setResponseCode(ResponseDefinition.RESPONSECODE_OK);
			responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_OK_S);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseUtil.buildResponse(NODE_THEME, responseService);
	}
}
