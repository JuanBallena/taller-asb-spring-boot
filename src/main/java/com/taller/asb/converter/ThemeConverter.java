package com.taller.asb.converter;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.taller.asb.dto.ParameterDto;
import com.taller.asb.dto.theme.ThemeDto;
import com.taller.asb.model.Theme;

@Component
public class ThemeConverter {
	
	public ThemeDto toThemeDto(Theme theme) {
		if (theme == null) {
			return null;
		}
		
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

	public List<ThemeDto> toThemeDtoList(List<Theme> themeList) {
		if (themeList == null) {
			return null;
		}
		
		List<ThemeDto> themeDtoList = new LinkedList<>();
		for (Theme theme : themeList) {
			themeDtoList.add(toThemeDto(theme));
		}
		return themeDtoList;
	}
}
