package com.taller.asb.converter;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.taller.asb.dto.ParameterDto;
import com.taller.asb.dto.theme.CreateThemeFormDto;
import com.taller.asb.dto.theme.ThemeDto;
import com.taller.asb.dto.theme.UpdateThemeFormDto;
import com.taller.asb.model.Parameter;
import com.taller.asb.model.Theme;

@Component
public class ThemeConverter {
	
	public ThemeDto toThemeDto(Theme theme) {
		if (theme == null) return null;
		
		return ThemeDto.builder()
				.id(theme.getIdTheme())
				.title(theme.getTitle())
				.author(theme.getAuthor())
				.urlThemeYoutube(theme.getUrlThemeYoutube())
				.allowedGroup(ParameterDto.builder()
						.id(theme.getAllowedGroup().getIdParameter())
						.description(theme.getAllowedGroup().getDescription())
						.build())
				.build();
	}
	
	public Theme toThemeModel(CreateThemeFormDto createThemeFormDto) {
		
		return Theme.builder()
				.title(createThemeFormDto.getTitle())
				.author(createThemeFormDto.getAuthor())
				.urlThemeYoutube(createThemeFormDto.getUrlLocationYoutube())
				.allowedGroup(Parameter.builder()
						.idParameter(Long.valueOf(createThemeFormDto.getIdAllowedGroup()))
						.build())
				.build();
	}
	
	public Theme replaceValuesInThemeModel(Theme theme, UpdateThemeFormDto updateThemeFormDto) {
		
		theme.setTitle(updateThemeFormDto.getTitle());
		theme.setAuthor(updateThemeFormDto.getAuthor());
		theme.setUrlThemeYoutube(updateThemeFormDto.getUrlLocationYoutube());
		theme.setAllowedGroup(Parameter.builder()
				.idParameter(Long.valueOf(updateThemeFormDto.getIdAllowedGroup()))
				.build());
		return theme;
	}
	
	public Page<ThemeDto> toThemeDtoPage(Page<Theme> themePage) {
		
		Page<ThemeDto> themeDtoPage = themePage.map((Function<Theme, ThemeDto>) theme -> {
			return toThemeDto(theme);
		});
				
		return themeDtoPage; 
	}

	public List<ThemeDto> toThemeDtoList(List<Theme> themeList) {
		
		List<ThemeDto> themeDtoList = new LinkedList<>();
		for (Theme theme : themeList) {
			themeDtoList.add(toThemeDto(theme));
		}
		
		return themeDtoList;
	}
}
