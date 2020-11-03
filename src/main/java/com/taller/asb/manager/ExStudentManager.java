package com.taller.asb.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.taller.asb.converter.ExStudentConverter;
import com.taller.asb.converter.UserConverter;
import com.taller.asb.definition.EntityDefinition;
import com.taller.asb.definition.FieldDefinition;
import com.taller.asb.definition.ParameterDefinition;
import com.taller.asb.dto.ex_student.CreateExStudentFormDto;
import com.taller.asb.dto.ex_student.ExStudentDto;
import com.taller.asb.dto.ex_student.UpdateExStudentFormDto;
import com.taller.asb.dto.ex_student.UpdatePhotoExStudentFormDto;
import com.taller.asb.interfaces.Existable;
import com.taller.asb.interfaces.Uniqueable;
import com.taller.asb.model.ExStudent;
import com.taller.asb.model.Parameter;
import com.taller.asb.model.User;
import com.taller.asb.repository.ExStudentRepository;
import com.taller.asb.repository.UserRepository;
import com.taller.asb.response.ResponsePage;

@Service
public class ExStudentManager implements Uniqueable, Existable {
	
	@Autowired
	private ExStudentRepository exStudentRepository;
	
	@Autowired
	private ExStudentConverter exStudentConverter;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserConverter userConverter;

	public ResponsePage getExStudentList(String search, Integer page, Integer size) {
		
		String name = search;
		String lastName = search;
		
		ResponsePage responsePage = new ResponsePage();
		
		if (!search.isEmpty()) {
			
			if (size > 0) {
				
				Page<ExStudent> exStudentPage = exStudentRepository.findByNameContainingOrLastNameContaining(name, lastName, PageRequest.of(page, size));
				Page<ExStudentDto> exStudentDtoPage = exStudentConverter.toExStudentDtoPage(exStudentPage);		
				
				responsePage.setData(exStudentDtoPage.getContent());
				responsePage.setTotalPages(exStudentDtoPage.getTotalPages());
				return responsePage;
				
			} else {

				List<ExStudent> exStudentList = exStudentRepository.findByNameContainingOrLastNameContaining(name, lastName);
				List<ExStudentDto> exStudentDtoList = exStudentConverter.toExStudentDtoList(exStudentList);
								
				responsePage.setData(exStudentDtoList);
				responsePage.setTotalPages(exStudentDtoList == null ? 0 : 1);
				return responsePage;
			}
		}
		
		if (size > 0) {
			
			Page<ExStudent> exStudentPage = exStudentRepository.findAll(PageRequest.of(page, size));
			Page<ExStudentDto> exStudentDtoPage = exStudentConverter.toExStudentDtoPage(exStudentPage);		
			
			responsePage.setData(exStudentDtoPage.getContent());
			responsePage.setTotalPages(exStudentDtoPage.getTotalPages());
			return responsePage;
		}
		
		List<ExStudent> exStudentList = exStudentRepository.findAll();
		List<ExStudentDto> exStudentDtoList = exStudentConverter.toExStudentDtoList(exStudentList);
		
		responsePage.setData(exStudentDtoList);
		responsePage.setTotalPages(exStudentDtoList == null ? 0 : 1);
		System.out.println(responsePage);
		return responsePage;
	}
	
	public ExStudentDto getExStudent(Long idExStudent) {

		ExStudent exStudent = exStudentRepository.findByIdExStudent(idExStudent);
		if (exStudent == null) return null;
		
		return exStudentConverter.toExStudentDto(exStudent);
	}
	
	public ExStudentDto saveExStudent(CreateExStudentFormDto createExStudentFormDto) {
		
		User user = userRepository.save(userConverter.toUserModel(createExStudentFormDto));
		if (user == null) return null;
		userRepository.refresh(user);
		ExStudent exStudent = exStudentRepository.save(exStudentConverter.toExStudentModel(createExStudentFormDto, user));
		exStudentRepository.save(exStudent);
		exStudentRepository.refresh(exStudent);
		
		return exStudentConverter.toExStudentDto(exStudent);
	}
	
	public ExStudentDto updateExStudent(Long idExStudent, UpdateExStudentFormDto updateExStudentFormDto) {
		
		ExStudent exStudent = exStudentRepository.findByIdExStudent(idExStudent);
		if (exStudent == null) return null;
		exStudent = exStudentConverter.replaceValuesInExStudentModel(exStudent, updateExStudentFormDto);
		exStudentRepository.save(exStudent);
		exStudentRepository.refresh(exStudent);
		
		return exStudentConverter.toExStudentDto(exStudent);
	}
	
	public ExStudentDto updatePhoto(Long idExStudent, UpdatePhotoExStudentFormDto updatePhotoExStudentFormDto) {
		
		ExStudent exStudent = exStudentRepository.findByIdExStudent(idExStudent);
		if (exStudent == null) return null;
		exStudent.setUrlLocationPhoto("http://cloud...");
		exStudentRepository.save(exStudent);
		exStudentRepository.refresh(exStudent);
		
		return exStudentConverter.toExStudentDto(exStudent);
	}
	
	public Integer countActiveExStudent() {
		
		Parameter parameter = Parameter.builder().idParameter(ParameterDefinition.PARAMETER_ACTIVE).build();
		List<ExStudent> exStudentList = exStudentRepository.findByUserStatus(parameter);
		
		return exStudentList.size();
	}
	
	@Override
	public boolean uniqueValueOnCreate(String field, Object value) {
		
		ExStudent exStudent = null;
		
		if (ExStudent.FIELD_DOCUMENT.equals(field)) {
			exStudent = exStudentRepository.findByDocument(value.toString());
		}
		
		return exStudent == null
				? FieldDefinition.FIELD_VALUE_NOT_EXISTS 
				: FieldDefinition.FIELD_VALUE_EXISTS;
	}
	
	@Override
	public boolean uniqueValueOnUpdate(String field, Object value, Integer id) {
		
		ExStudent exStudent = null;
		
		if (ExStudent.FIELD_DOCUMENT.equals(field)) {
			exStudent = exStudentRepository.findByDocument(value.toString());
		}
		
		if (exStudent == null) return FieldDefinition.FIELD_VALUE_NOT_EXISTS;
		
		return (Long.valueOf(id) == exStudent.getIdExStudent())
				? FieldDefinition.FIELD_VALUE_NOT_EXISTS 
				: FieldDefinition.FIELD_VALUE_EXISTS;
	}

	@Override
	public boolean entityExistsInDatabase(Long idExStudent) {
		
		ExStudent exStudent = exStudentRepository.findByIdExStudent(idExStudent);
		
		return exStudent == null 
				? EntityDefinition.ENTITY_NOT_EXISTS
				: EntityDefinition.ENTITY_EXISTS;
	}
	
}
