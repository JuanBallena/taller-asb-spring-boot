package com.taller.asb.converter;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.taller.asb.controller.UserController;
import com.taller.asb.dto.ParameterDto;
import com.taller.asb.dto.student.CreateStudentDto;
import com.taller.asb.dto.student.StudentDto;
import com.taller.asb.dto.user.UpdateUserDto;
import com.taller.asb.hateoas.LinkCreator;
import com.taller.asb.model.Student;

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
				.name(student.getUser().getName())
				.lastName(student.getUser().getLastName())
				.urlLocationPhoto(student.getUser().getUrlLocationPhoto())
				.documentType(ParameterDto.builder()
						.id(student.getUser().getDocumentType().getIdParameter())
						.description(student.getUser().getDocumentType().getDescription())
						.build())
				.document(student.getUser().getDocument())
				.address(student.getUser().getAddress())
				.phone(student.getUser().getPhone())
				.links(links)
				.build();
	}
	
	public Page<StudentDto> toStudentDtoPage(Page<Student> studentPage) {
		
		if (studentPage == null) return null;
		
		Page<StudentDto> studentDtoPage = studentPage.map((Function<Student, StudentDto>) student -> {
			return toStudentDto(student);
		});
		
		return studentDtoPage;
	}
	
	public List<StudentDto> toStudentDtoList(List<Student> studentList) {
		
		if (studentList == null) return null;
		
		List<StudentDto> studentDtoList = new LinkedList<StudentDto>();
		for (Student student : studentList) {
			studentDtoList.add(toStudentDto(student));
		}
		
		return studentDtoList;
	}
	
	public Student toStudentModel(CreateStudentDto createStudentDto) {
		
		if (createStudentDto == null) return null;
		
		return null;
	}
	
	public Student toStudentModel(UpdateUserDto updateStudentDto) {
		
		if (updateStudentDto == null) return null;
		
		return null;
	}
	
	public Object createLinkToUser(Long idUser) {
		WebMvcLinkBuilder linkToUser = linkTo(methodOn(UserController.class).getUser(idUser));
		return linkToUser.withRel("user");
	}
}
