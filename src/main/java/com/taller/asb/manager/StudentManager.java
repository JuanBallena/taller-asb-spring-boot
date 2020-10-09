package com.taller.asb.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.taller.asb.converter.StudentConverter;
import com.taller.asb.dto.student.CreateStudentDto;
import com.taller.asb.dto.student.StudentDto;
import com.taller.asb.model.Student;
import com.taller.asb.repository.StudentRepository;
import com.taller.asb.response.ResponsePage;

@Service
public class StudentManager{

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private StudentConverter studentConverter;
	
	public ResponsePage getStudentList(String search, int page, int size) {
		
		String name = search;
		String lastName = search;
		
		ResponsePage responsePage = new ResponsePage();
		
		if (!search.isEmpty()) {
			
			if (size > 0) {
				
				Page<Student> studentPage = studentRepository.findByUserNameContainingOrUserLastNameContaining(name, lastName, PageRequest.of(page, size));
				Page<StudentDto> studentDtoPage = studentConverter.toStudentDtoPage(studentPage);		
				
				responsePage.setData(studentDtoPage.getContent());
				responsePage.setTotalPages(studentDtoPage.getTotalPages());
				return responsePage;
				
			} else {

				List<Student> userList = studentRepository.findByUserNameContainingOrUserLastNameContaining(name, lastName);
				List<StudentDto> studentDtoList = studentConverter.toStudentDtoList(userList);
								
				responsePage.setData(studentDtoList);
				responsePage.setTotalPages(studentDtoList.size() == 0 ? 0 : 1);
				return responsePage;
			}
		}
		
		if (size > 0) {
			
			Page<Student> studentPage = studentRepository.findAll(PageRequest.of(page, size));
			Page<StudentDto> studentDtoPage = studentConverter.toStudentDtoPage(studentPage);		
			
			responsePage.setData(studentDtoPage.getContent());
			responsePage.setTotalPages(studentDtoPage.getTotalPages());
			return responsePage;
		}
		
		List<Student> studentList = studentRepository.findAll();
		List<StudentDto> studentDtoList = studentConverter.toStudentDtoList(studentList);
		
		responsePage.setData(studentDtoList);
		responsePage.setTotalPages(studentDtoList == null ? 0 : 1);
		
		return responsePage;
	}
	
	public StudentDto getStudent(Long idStudent) {

		return studentConverter.toStudentDto(studentRepository.findByIdStudent(idStudent));
	}
	
	public StudentDto saveStudent(CreateStudentDto createStudentDto) {
		
		return null;
	}
}
