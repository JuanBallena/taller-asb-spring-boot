package com.taller.asb.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.taller.asb.model.Theme;

public interface ThemeRepository extends CustomRepository<Theme, Long> {

	public Theme findByIdTheme(Long idTheme);
	public Page<Theme> findByTitleContaining(String title, Pageable page);
	public List<Theme> findByTitleContaining(String title);
}
