package com.taller.asb.converter;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.taller.asb.dto.parameter.ParameterDto;
import com.taller.asb.dto.student.CreateStudentFormDto;
import com.taller.asb.dto.student.StudentDto;
import com.taller.asb.dto.student.UpdateStudentFormDto;
import com.taller.asb.dto.user.UpdateUserFormDto;
import com.taller.asb.hateoas.LinkCreator;
import com.taller.asb.model.Parameter;
import com.taller.asb.model.Student;
import com.taller.asb.model.User;

@Component
public class StudentConverter {
	
	@Autowired
	private LinkCreator linkCreator;
	
	public StudentDto toStudentDto(Student student) {
		
		if (student == null) return null;
		
		List<Object> links = new LinkedList<Object>();
		links.add(linkCreator.createLinkToUser(student.getUser().getIdUser()));

		return StudentDto.builder()
				.id(student.getIdStudent())
				.name(student.getName())
				.lastName(student.getLastName())
				.urlLocationPhoto(student.getUrlLocationPhoto())
				.documentType(ParameterDto.builder()
						.id(student.getDocumentType().getIdParameter())
						.description(student.getDocumentType().getDescription())
						.build())
				.document(student.getDocument())
				.address(student.getAddress())
				.phone(student.getPhone())
				.hasDocumentCopy(student.getHasDocumentCopy())
				.suspended(student.getSuspended())
				.status(ParameterDto.builder()
						.id(student.getUser().getStatus().getIdParameter())
						.description(student.getUser().getStatus().getDescription())
						.build())
				.links(links)
				.build();
	}
	
	public Student toStudentModel(CreateStudentFormDto createStudentFormDto, User user) {
		return Student.builder()
				.user(user)
				.name(createStudentFormDto.getName())
				.lastName(createStudentFormDto.getLastName())
				.documentType(Parameter.builder()
						.idParameter(Long.valueOf(createStudentFormDto.getIdDocumentType()))
						.build())
				.document(createStudentFormDto.getDocument())
				.address(createStudentFormDto.getAddress())
				.phone(createStudentFormDto.getPhone())
				.build();
	}
	
	public Student replaceValuesInStudentModel(Student student, UpdateStudentFormDto updateStudentFormDto) {
		
		student.setName(updateStudentFormDto.getName());
		student.setLastName(updateStudentFormDto.getLastName());
		student.setDocumentType(Parameter.builder()
				.idParameter(Long.valueOf(updateStudentFormDto.getIdDocumentType()))
				.build());
		student.setDocument(updateStudentFormDto.getDocument());
		student.setAddress(updateStudentFormDto.getAddress());
		student.setHasDocumentCopy(updateStudentFormDto.getHasDocumentCopy());
		student.setSuspended(updateStudentFormDto.getSuspended());
		
		return student;
	}
	
	public Page<StudentDto> toStudentDtoPage(Page<Student> studentPage) {
		
		Page<StudentDto> studentDtoPage = studentPage.map((Function<Student, StudentDto>) student -> {
			return toStudentDto(student);
		});
		
		return studentDtoPage;
	}
	
	public List<StudentDto> toStudentDtoList(List<Student> studentList) {
		
		List<StudentDto> studentDtoList = new LinkedList<StudentDto>();
		for (Student student : studentList) {
			studentDtoList.add(toStudentDto(student));
		}
		
		return studentDtoList;
	}
	
	
	
	public Student toStudentModel(UpdateUserFormDto updateStudentDto) {
		
		if (updateStudentDto == null) return null;
		
		return null;
	}
}
