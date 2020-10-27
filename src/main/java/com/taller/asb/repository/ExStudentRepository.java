package com.taller.asb.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.taller.asb.model.ExStudent;

public interface ExStudentRepository extends JpaRepository<ExStudent, Long> {

	public Page<ExStudent> findByNameContainingOrLastNameContaining(String name, String lastName, Pageable page); 
	public List<ExStudent> findByNameContainingOrLastNameContaining(String name, String lastName);
	
	public ExStudent findByIdExStudent(Long idExStudent);
}
