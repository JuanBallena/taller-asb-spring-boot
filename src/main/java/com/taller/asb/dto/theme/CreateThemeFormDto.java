package com.taller.asb.dto.theme;

import java.util.Map;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.taller.asb.error.ThemeErrorMessages;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateThemeFormDto {
	
	@NotNull(message = ThemeErrorMessages.NOT_NULL_TITLE_ERROR_MESSAGE)
	@NotEmpty(message = ThemeErrorMessages.NOT_EMPTY_TITLE_ERROR_MESSAGE)
	@NotBlank(message = ThemeErrorMessages.NOT_BLANK_TITLE_ERROR_MESSAGE)
	private String title;
	
	@NotNull(message = ThemeErrorMessages.NOT_NULL_AUTHOR_ERROR_MESSAGE)
	@NotEmpty(message = ThemeErrorMessages.NOT_EMPTY_AUTHOR_ERROR_MESSAGE)
	@NotBlank(message = ThemeErrorMessages.NOT_BLANK_AUTHOR_ERROR_MESSAGE)
	private String author;
	
	@NotBlank(message = ThemeErrorMessages.NOT_BLANK_URL_LOCATION_YOUTUBE_ERROR_MESSAGE)
	private String urlLocationYoutube;
	
	@NotNull(message = ThemeErrorMessages.NOT_NULL_ID_ALLOWED_GROUP_ERROR_MESSAGE)
	private Integer idAllowedGroup;
	
	@SuppressWarnings("unchecked")
	@JsonProperty("theme")
	@JsonIgnoreProperties(ignoreUnknown = true)
	private void getJsonProperties(Map<String, Object> theme) {
		
		Map<String, Object> data = (Map<String, Object>) theme.get("data");
		
		title              = (String)  data.get("title");
		author             = (String)  data.get("author");
		urlLocationYoutube = (String)  data.get("urlLocationYoutube");
		idAllowedGroup     = (Integer) data.get("idAllowedGroup");
	}
}
