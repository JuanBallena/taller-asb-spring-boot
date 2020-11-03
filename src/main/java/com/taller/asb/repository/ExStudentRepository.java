package com.taller.asb.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.taller.asb.model.ExStudent;
import com.taller.asb.model.Parameter;

public interface ExStudentRepository extends CustomRepository<ExStudent, Long> {

	public Page<ExStudent> findByNameContainingOrLastNameContaining(String name, String lastName, Pageable page); 
	public List<ExStudent> findByNameContainingOrLastNameContaining(String name, String lastName);
	
	public ExStudent findByIdExStudent(Long idExStudent);
	public ExStudent findByDocument(String document);
	public List<ExStudent> findByUserStatus(Parameter status);
}
