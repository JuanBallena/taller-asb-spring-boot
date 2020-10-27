package com.taller.asb.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.taller.asb.converter.ThemeConverter;
import com.taller.asb.definition.EntityDefinition;
import com.taller.asb.dto.theme.CreateThemeFormDto;
import com.taller.asb.dto.theme.ThemeDto;
import com.taller.asb.dto.theme.UpdateThemeFormDto;
import com.taller.asb.interfaces.Existable;
import com.taller.asb.model.Theme;
import com.taller.asb.repository.ThemeRepository;
import com.taller.asb.response.ResponsePage;

@Service
public class ThemeManager implements Existable {
	
	@Autowired
	private ThemeRepository themeRepository;
	
	@Autowired
	private ThemeConverter themeConverter;

	public ResponsePage getThemeList(String query, int size, int page) {
		
		ResponsePage responsePage = new ResponsePage();
		
		if (!query.isEmpty()) {
			
			String title = query;
			
			if (size > 0) {
				Page<Theme> themePage = themeRepository.findByTitleContaining(title, PageRequest.of(page, size));
				Page<ThemeDto> themeDtoPage = themeConverter.toThemeDtoPage(themePage);
				responsePage.setData(themeDtoPage.getContent());
				responsePage.setTotalPages(themeDtoPage.getTotalPages());
				
				return responsePage;
			}
			else {
				List<Theme> themeList = themeRepository.findByTitleContaining(title);
				List<ThemeDto> themeDtoList = themeConverter.toThemeDtoList(themeList);
				responsePage.setData(themeDtoList);
				responsePage.setTotalPages(themeDtoList.size() == 0 ? 0 : 1);
				
				return responsePage;
			}
		}
		
		if (size > 0) {
			Page<Theme> themePage = themeRepository.findAll(PageRequest.of(page, size));
			Page<ThemeDto> themeDtoPage = themeConverter.toThemeDtoPage(themePage);
			responsePage.setData(themeDtoPage.getContent());
			responsePage.setTotalPages(themeDtoPage.getTotalPages());
			
			return responsePage;
		}
		
		List<Theme> themeList = themeRepository.findAll();
		List<ThemeDto> themeDtoList = themeConverter.toThemeDtoList(themeList);
		responsePage.setData(themeDtoList);
		responsePage.setTotalPages(themeDtoList.size() == 0 ? 0 : 1);
		
		return responsePage;
	}
	
	public ThemeDto getTheme(Long idTheme) {
		
		Theme theme = themeRepository.findByIdTheme(idTheme);
		if (theme == null) return null;
		
		return themeConverter.toThemeDto(themeRepository.findByIdTheme(idTheme));
	}
	
	public ThemeDto saveTheme(CreateThemeFormDto createThemeFormDto) {
		
		Theme theme = themeRepository.save(themeConverter.toThemeModel(createThemeFormDto));
		if (theme == null) return null;
		themeRepository.refresh(theme);
		
		return themeConverter.toThemeDto(theme);
	}
	
	public ThemeDto updateTheme(Long idTheme, UpdateThemeFormDto updateThemeFormDto) {
		
		Theme theme = themeRepository.findByIdTheme(idTheme);
		if (theme == null) return null;
		theme = themeConverter.replaceValuesInThemeModel(theme, updateThemeFormDto);
		themeRepository.save(theme);
		themeRepository.refresh(theme);
		
		return themeConverter.toThemeDto(theme);
	}

	@Override
	public boolean entityExistsInDatabase(Long id) {
		
		Theme theme = themeRepository.findByIdTheme(id);
		
		return theme == null 
			? EntityDefinition.ENTITY_NOT_EXISTS 
			: EntityDefinition.ENTITY_EXISTS;
	}
}
