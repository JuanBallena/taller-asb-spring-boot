package com.taller.asb.dto.theme;

import java.util.Map;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.taller.asb.annotations.EntityExistsValidator;
import com.taller.asb.error.ThemeErrorMessage;
import com.taller.asb.interfaces.FirstValidation;
import com.taller.asb.manager.ThemeManager;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateThemeFormDto {

	@NotNull(message = ThemeErrorMessage.NOT_NULL_ID_THEME)
	@EntityExistsValidator(
			manager = ThemeManager.class, 
			groups = FirstValidation.class, 
			message = ThemeErrorMessage.THEME_NOT_EXISTS)
	private Integer idTheme;
	
	@NotNull(message = ThemeErrorMessage.NOT_NULL_TITLE)
	@NotEmpty(message = ThemeErrorMessage.NOT_EMPTY_TITLE)
	@NotBlank(message = ThemeErrorMessage.NOT_BLANK_TITLE)
	private String title;
	
	@NotNull(message = ThemeErrorMessage.NOT_NULL_AUTHOR)
	@NotEmpty(message = ThemeErrorMessage.NOT_EMPTY_AUTHOR)
	@NotBlank(message = ThemeErrorMessage.NOT_BLANK_AUTHOR)
	private String author;
	
	private String urlLocationYoutube;
	
	@NotNull(message = ThemeErrorMessage.NOT_NULL_ID_ALLOWED_GROUP)
	private Integer idAllowedGroup;
	
	@SuppressWarnings("unchecked")
	@JsonProperty("theme")
	@JsonIgnoreProperties(ignoreUnknown = true)
	private void getJsonProperties(Map<String, Object> theme) {
		
		Map<String, Object> data = (Map<String, Object>) theme.get("data");
		
		idTheme            = (Integer) data.get("idTheme");
		title              = (String)  data.get("title");
		author             = (String)  data.get("author");
		urlLocationYoutube = (String)  data.get("urlLocationYoutube");
		idAllowedGroup     = (Integer) data.get("idAllowedGroup");
	}
}
