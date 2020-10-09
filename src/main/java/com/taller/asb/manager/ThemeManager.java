package com.taller.asb.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taller.asb.converter.ThemeConverter;
import com.taller.asb.dto.theme.ThemeDto;
import com.taller.asb.model.Theme;
import com.taller.asb.repository.ThemeRepository;
import com.taller.asb.response.ResponsePage;

@Service
public class ThemeManager {
	
	@Autowired
	private ThemeRepository themeRepository;
	
	@Autowired
	private ThemeConverter themeConverter;

	public ResponsePage getThemeList(String title, int size, int page) {
		
		ResponsePage responsePage = new ResponsePage();
		
		List<Theme> themeList = themeRepository.findAll();
		List<ThemeDto> themeDtoList = themeConverter.toThemeDtoList(themeList);
		
		responsePage.setData(themeDtoList);
		responsePage.setTotalPages(themeDtoList == null ? 0 : 1);
		return responsePage;
	}
	
	public ThemeDto getTheme(Long idTheme) {
		return themeConverter.toThemeDto(themeRepository.findByIdTheme(idTheme));
	}
}
