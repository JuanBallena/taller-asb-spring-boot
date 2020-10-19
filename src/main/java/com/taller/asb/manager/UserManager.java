package com.taller.asb.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.taller.asb.converter.UserConverter;
import com.taller.asb.dto.user.CreateUserFormDto;
import com.taller.asb.dto.user.UpdateUserFormDto;
import com.taller.asb.dto.user.UserDto;
import com.taller.asb.model.User;
import com.taller.asb.repository.UserRepository;
import com.taller.asb.response.ResponsePage;

@Service
public class UserManager implements Uniqueable {
	
	private static final String FIELD_USERNAME = "User_Username";
	private static final String FIELD_DOCUMENT = "User_Document";
	private static final Boolean VALUE_EXISTS = false;
	private static final Boolean VALUE_NOT_EXISTS = true;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserConverter userConverter;
	
	public ResponsePage getUserList(String search, int page, int size) {
		
		String name = search;
		String lastName = search;
		
		ResponsePage responsePage = new ResponsePage();
		
		if (!search.isEmpty()) {
			
			if (size > 0) {
				Page<User> userPage = userRepository.findByNameContainingOrLastNameContaining(name, lastName, PageRequest.of(page, size));
				Page<UserDto> userDtoPage = userConverter.toUserDtoPage(userPage);
				
				responsePage.setData(userDtoPage.getContent());
				responsePage.setTotalPages(userDtoPage.getTotalPages());
				return responsePage;
			} else {
				
				List<User> userList = userRepository.findByNameContainingOrLastNameContaining(name, lastName);
				List<UserDto> userDtoList = userConverter.toUserDtoList(userList);
				
				responsePage.setData(userDtoList);
				responsePage.setTotalPages(userDtoList.size() == 0 ? 0 : 1);
				return responsePage;
			}
			
		}
		
		if (size > 0) {
			Page<User> userPage = userRepository.findAll(PageRequest.of(page, size));
			Page<UserDto> userDtoPage = userConverter.toUserDtoPage(userPage);
			
			responsePage.setData(userDtoPage.getContent());
			responsePage.setTotalPages(userDtoPage.getTotalPages());
			return responsePage;
		}
		
		List<User> userList = userRepository.findAll();
		List<UserDto> userDtoList = userConverter.toUserDtoList(userList);
		
		responsePage.setData(userDtoList);
		responsePage.setTotalPages(userDtoList == null ? 0 : 1);
		return responsePage;
	}
	
	public UserDto getUser(Long idUser) {
		return userConverter.toUserDto(userRepository.findByIdUser(idUser));
	}
	
	public UserDto saveUser(CreateUserFormDto createUserFormDto) {
		
		User user = userRepository.save(userConverter.toUserModel(createUserFormDto));
		userRepository.refresh(user);
		return userConverter.toUserDto(user);
	}
	
	public UserDto updateUser(UpdateUserFormDto updateUserFormDto) {
		
		User user = userRepository.save(userConverter.toUserModel(updateUserFormDto));
		userRepository.refresh(user);
		return userConverter.toUserDto(user);
	}

	@Override
	public boolean valueExists(String field, Object value) {
		
		User user = new User();
		
		switch (field) {
			case FIELD_USERNAME:
				user = userRepository.findByUsername(value.toString().toUpperCase());
				
			case FIELD_DOCUMENT:
				user = userRepository.findByDocument(value.toString());
		}
		
		return user == null ? VALUE_NOT_EXISTS : VALUE_EXISTS;
	}

	public boolean usernameExists(UpdateUserFormDto updateUserDto) {
		
		User user = userRepository.findByUsername(updateUserDto.getUsername().toString().toUpperCase());
		
		if (user == null) return VALUE_NOT_EXISTS;
		
		return (Long.valueOf(updateUserDto.getIdUser()) == user.getIdUser()) ?  VALUE_NOT_EXISTS : VALUE_EXISTS;

	}
	
	public boolean documentExists(UpdateUserFormDto updateUserDto) {
		
		User user = userRepository.findByDocument(updateUserDto.getDocument());
		
		if (user == null) return VALUE_NOT_EXISTS;
		
		return (Long.valueOf(updateUserDto.getIdUser()) == user.getIdUser()) ?  VALUE_NOT_EXISTS : VALUE_EXISTS;

	}

}
