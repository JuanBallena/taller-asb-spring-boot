package com.taller.asb.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.taller.asb.model.Student;

public interface StudentRepository extends CustomRepository<Student, Long> {
	
	public Page<Student> findByNameContainingOrLastNameContaining(String name, String lastName, Pageable page); 
	public List<Student> findByNameContainingOrLastNameContaining(String name, String lastName);
	
	public Student findByIdStudent(Long idStudent);
	public Student findByDocument(String document);
}
