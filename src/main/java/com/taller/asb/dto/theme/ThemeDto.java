package com.taller.asb.dto.theme;

import com.taller.asb.dto.ParameterDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class ThemeDto {

	private Long id;
	private String title;
	private String author;
	private String urlThemeYoutube;
	private ParameterDto allowedGroup;
	private Object links;
}
