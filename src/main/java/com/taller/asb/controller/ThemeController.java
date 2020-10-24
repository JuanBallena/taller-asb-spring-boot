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
import com.taller.asb.dto.theme.CreateThemeFormDto;
import com.taller.asb.dto.theme.ThemeDto;
import com.taller.asb.dto.theme.UpdateThemeFormDto;
import com.taller.asb.error.UriErrorMessages;
import com.taller.asb.interfaces.SequenceValidation;
import com.taller.asb.manager.ThemeManager;
import com.taller.asb.response.ResponsePage;
import com.taller.asb.response.ResponseService;

@RestController
@Validated
public class ThemeController {
	
	private static final String THEMES = "themes";
	private static final String THEME = "theme";
	
	@Autowired
	private ThemeManager themeManager;

	@GetMapping("/themes")
	public ResponseService getThemeList(
		@RequestParam(value="q", defaultValue="") String query,
		@RequestParam(value="size", defaultValue="0") @Min(message = UriErrorMessages.MIN_SIZE_ERROR_MESSAGE, value = 0) int size,
		@RequestParam(value="page", defaultValue="0") @Min(message = UriErrorMessages.MIN_PAGE_ERROR_MESSAGE, value = 0) int page
	) {
		ResponseService responseService = new ResponseService();
		responseService.setType(THEMES);
		responseService.setResponseCode(ResponseDefinition.RESPONSECODE_INTERNAL_SERVER_ERROR);
		responseService.setData(ResponseDefinition.RESPONSECODE_INTERNAL_SERVER_ERROR_S);
		
		try {
			ResponsePage responsePage = themeManager.getThemeList(query, size, page);
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
	
	@GetMapping("/themes/{idTheme}")
	public ResponseService getUser(
		@PathVariable("idTheme") @Positive(message = UriErrorMessages.POSITIVE_ID_ERROR_MESSAGE) Long idTheme
	) {
		ResponseService responseService = new ResponseService();
		responseService.setType(THEME);
		responseService.setResponseCode(ResponseDefinition.RESPONSECODE_INTERNAL_SERVER_ERROR);
		responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_INTERNAL_SERVER_ERROR_S);
		
		try {
			ThemeDto themeDto = themeManager.getTheme(idTheme);
			if (themeDto == null) {
				responseService.setResponseCode(ResponseDefinition.RESPONSECODE_NO_CONTENT);
				responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_NO_CONTENT_S);
			}
			else {
				responseService.setData(themeDto);
				responseService.setResponseCode(ResponseDefinition.RESPONSECODE_OK);
				responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_OK_S);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return responseService;
	}
	
	@PostMapping("/themes")
	public ResponseService saveTheme(
		@Valid @RequestBody CreateThemeFormDto createThemeFormDto
	) {

		ResponseService responseService = new ResponseService();
		responseService.setType(THEME);
		responseService.setResponseCode(ResponseDefinition.RESPONSECODE_INTERNAL_SERVER_ERROR);
		responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_INTERNAL_SERVER_ERROR_S);

		try {
			ThemeDto themeDto = themeManager.saveTheme(createThemeFormDto);
			if (themeDto == null) {
				responseService.setResponseCode(ResponseDefinition.RESPONSECODE_SERVICE_UNAVAILABLE);
				responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_SERVICE_UNAVAILABLE_S);
			}
			else {
				responseService.setData(themeDto);
				responseService.setResponseCode(ResponseDefinition.RESPONSECODE_CREATED);
				responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_CREATED_S);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return responseService;
	}
	
	@PutMapping("/themes/{idTheme}")
	public ResponseService updateTheme(
		@Validated(SequenceValidation.class) @RequestBody UpdateThemeFormDto updateThemeFormDto,
		@PathVariable("idTheme") @Positive(message = UriErrorMessages.POSITIVE_ID_ERROR_MESSAGE) Long idTheme
	) {
		
		ResponseService responseService = new ResponseService();
		responseService.setType(THEME);
		responseService.setResponseCode(ResponseDefinition.RESPONSECODE_INTERNAL_SERVER_ERROR);
		responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_INTERNAL_SERVER_ERROR_S);
		
		try {
			if (idTheme != Long.valueOf(updateThemeFormDto.getIdTheme())) {
				responseService.setResponseCode(ResponseDefinition.RESPONSECODE_UNPROCESSABLE_ENTITY);
				responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_UNPROCESSABLE_ENTITY_S);
				
				return responseService;
			}
			
			ThemeDto themeDto = themeManager.updateTheme(idTheme, updateThemeFormDto);
			if (themeDto == null) {
				responseService.setResponseCode(ResponseDefinition.RESPONSECODE_NO_CONTENT);
				responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_NO_CONTENT_S);
			} 
			else {
				responseService.setData(themeDto);
				responseService.setResponseCode(ResponseDefinition.RESPONSECODE_CREATED);
				responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_CREATED_S);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseService;
	}
}
