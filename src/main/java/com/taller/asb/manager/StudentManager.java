package com.taller.asb.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.taller.asb.converter.StudentConverter;
import com.taller.asb.converter.UserConverter;
import com.taller.asb.definition.EntityDefinition;
import com.taller.asb.definition.FieldDefinition;
import com.taller.asb.dto.student.CreateStudentFormDto;
import com.taller.asb.dto.student.StudentDto;
import com.taller.asb.dto.student.UpdateStudentFormDto;
import com.taller.asb.interfaces.Existable;
import com.taller.asb.interfaces.Uniqueable;
import com.taller.asb.model.Student;
import com.taller.asb.model.User;
import com.taller.asb.repository.StudentRepository;
import com.taller.asb.repository.UserRepository;
import com.taller.asb.response.ResponsePage;

@Service
public class StudentManager implements Uniqueable, Existable{

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private StudentConverter studentConverter;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserConverter userConverter;
	
	public ResponsePage getStudentList(String query, int page, int size) {
		
		ResponsePage responsePage = new ResponsePage();
		
		if (!query.isEmpty()) {
			
			String name = query;
			String lastName = query;
			
			if (size > 0) {
				Page<Student> studentPage = studentRepository.findByNameContainingOrLastNameContaining(name, lastName, PageRequest.of(page, size));
				Page<StudentDto> studentDtoPage = studentConverter.toStudentDtoPage(studentPage);		
				responsePage.setData(studentDtoPage.getContent());
				responsePage.setTotalPages(studentDtoPage.getTotalPages());
				
				return responsePage;
			} 
			else {
				List<Student> userList = studentRepository.findByNameContainingOrLastNameContaining(name, lastName);
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
		responsePage.setTotalPages(studentDtoList.size() == 0 ? 0 : 1);
		
		return responsePage;
	}
	
	public StudentDto getStudent(Long idStudent) {
		
		Student student = studentRepository.findByIdStudent(idStudent);
		if (student == null) return null;
		
		return studentConverter.toStudentDto(student);
	}
	
	public StudentDto saveStudent(CreateStudentFormDto createStudentFormDto) {
		
		User user = userRepository.save(userConverter.toUserModel(createStudentFormDto));
		if (user == null) return null;
		Student student = studentRepository.save(studentConverter.toStudentModel(createStudentFormDto, user));
		studentRepository.refresh(student);
		
		return studentConverter.toStudentDto(student);
	}
	
	public StudentDto updateStudent(UpdateStudentFormDto updateStudentFormDto) {
		return null;
	}

	@Override
	public boolean uniqueValueOnCreate(String field, Object value) {
		
		Student student = null;
		
		if (Student.FIELD_DOCUMENT.equals(field)) {
			student = studentRepository.findByDocument(value.toString());
		}
		
		return student == null
				? FieldDefinition.FIELD_VALUE_NOT_EXISTS 
				: FieldDefinition.FIELD_VALUE_EXISTS;
	}
	
	@Override
	public boolean uniqueValueOnUpdate(String field, Object value, Integer id) {
		
		Student student = null;
		
		if (Student.FIELD_DOCUMENT.equals(field)) {
			student = studentRepository.findByDocument(value.toString());
		}
		
		if (student == null) return FieldDefinition.FIELD_VALUE_NOT_EXISTS;
		
		return (Long.valueOf(id) == student.getIdStudent())
			? FieldDefinition.FIELD_VALUE_NOT_EXISTS 
			: FieldDefinition.FIELD_VALUE_EXISTS;
	}
	
	@Override
	public boolean entityExistsInDatabase(Long idStudent) {
		
		Student student = studentRepository.findByIdStudent(idStudent);
		
		return student == null 
			? EntityDefinition.ENTITY_NOT_EXISTS 
			: EntityDefinition.ENTITY_EXISTS;
	}
}
