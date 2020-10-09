package com.taller.asb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taller.asb.model.Theme;

public interface ThemeRepository extends JpaRepository<Theme, Long> {

	public Theme findByIdTheme(Long idTheme);
}
