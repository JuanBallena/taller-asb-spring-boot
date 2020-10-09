package com.taller.asb.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.taller.asb.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	
	public Page<Student> findByUserNameContainingOrUserLastNameContaining(String name, String lastName, Pageable page); 
	public List<Student> findByUserNameContainingOrUserLastNameContaining(String name, String lastName);
	
	public Student findByIdStudent(Long idStudent);
}
