package com.taller.asb.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.taller.asb.converter.ExStudentConverter;
import com.taller.asb.dto.ex_student.ExStudentDto;
import com.taller.asb.model.ExStudent;
import com.taller.asb.repository.ExStudentRepository;
import com.taller.asb.response.ResponsePage;

@Service
public class ExStudentManager {
	
	@Autowired
	private ExStudentRepository exStudentRepository;
	
	@Autowired
	private ExStudentConverter exStudentConverter;

	public ResponsePage getExStudentList(String search, Integer page, Integer size) {
		
		String name = search;
		String lastName = search;
		
		ResponsePage responsePage = new ResponsePage();
		
		if (!search.isEmpty()) {
			
			if (size > 0) {
				
				Page<ExStudent> exStudentPage = exStudentRepository.findByUserNameContainingOrUserLastNameContaining(name, lastName, PageRequest.of(page, size));
				Page<ExStudentDto> exStudentDtoPage = exStudentConverter.toExStudentDtoPage(exStudentPage);		
				
				responsePage.setData(exStudentDtoPage.getContent());
				responsePage.setTotalPages(exStudentDtoPage.getTotalPages());
				return responsePage;
				
			} else {

				List<ExStudent> exStudentList = exStudentRepository.findByUserNameContainingOrUserLastNameContaining(name, lastName);
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

		return exStudentConverter.toExStudentDto(exStudentRepository.findByIdExStudent(idExStudent));
	}
}
