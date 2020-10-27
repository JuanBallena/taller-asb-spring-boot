package com.taller.asb.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.taller.asb.converter.UserConverter;
import com.taller.asb.definition.EntityDefinition;
import com.taller.asb.definition.FieldDefinition;
import com.taller.asb.dto.user.CreateUserFormDto;
import com.taller.asb.dto.user.UpdateChangePasswordFormDto;
import com.taller.asb.dto.user.UpdateUserFormDto;
import com.taller.asb.dto.user.UserDto;
import com.taller.asb.interfaces.Existable;
import com.taller.asb.interfaces.Uniqueable;
import com.taller.asb.model.User;
import com.taller.asb.repository.UserRepository;
import com.taller.asb.response.ResponsePage;

@Service
public class UserManager implements Uniqueable, Existable {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserConverter userConverter;
	
	public ResponsePage getUserList(String query, int page, int size) {
		
		ResponsePage responsePage = new ResponsePage();
		
		if (!query.isEmpty()) {
			
			String username = query;
			
			if (size > 0) {
				
				Page<User> userPage = userRepository.findByUsernameContaining(username, PageRequest.of(page, size));
				Page<UserDto> userDtoPage = userConverter.toUserDtoPage(userPage);
				responsePage.setData(userDtoPage.getContent());
				responsePage.setTotalPages(userDtoPage.getTotalPages());
				
				return responsePage;
			} 
			else {
				
				List<User> userList = userRepository.findByUsernameContaining(username);
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
		System.out.println(userDtoList);
		responsePage.setData(userDtoList);
		responsePage.setTotalPages(userDtoList.size() == 0 ? 0 : 1);
		
		return responsePage;
	}
	
	public UserDto getUser(Long idUser) {
		
		User user = userRepository.findByIdUser(idUser);
		if (user == null) return null;
		
		return userConverter.toUserDto(user);
	}
	
	public UserDto saveUser(CreateUserFormDto createUserFormDto) {
		
		User user = userRepository.save(userConverter.toUserModel(createUserFormDto));
		if (user == null) return null;
		userRepository.refresh(user);
		
		return userConverter.toUserDto(user);
	}
	
	public UserDto updateUser(Long idUser, UpdateUserFormDto updateUserFormDto) {
		
		User user = userRepository.findByIdUser(idUser);
		if (user == null) return null;
		user = userConverter.replaceValuesInUserModel(user, updateUserFormDto);
		userRepository.save(user);
		userRepository.refresh(user);
		
		return userConverter.toUserDto(user);
	}
	
	public UserDto updateChangePassword(Long idUser, UpdateChangePasswordFormDto updateChangePasswordFormDto) {
		
		User user = userRepository.findByIdUser(idUser);
		if (user == null) return null;
		user.setChangePassword(updateChangePasswordFormDto.getChangePassword());
		userRepository.save(user);
		userRepository.refresh(user);
		
		return userConverter.toUserDto(user);
	}

	@Override
	public boolean uniqueValueOnCreate(String field, Object value) {
		
		User user = null;
		
		if (User.FIELD_USERNAME.equals(field)) {
			user = userRepository.findByUsername(value.toString().toUpperCase());
		}
		
		return user == null
				? FieldDefinition.FIELD_VALUE_NOT_EXISTS 
				: FieldDefinition.FIELD_VALUE_EXISTS;
	}
	
	@Override
	public boolean uniqueValueOnUpdate(String field, Object value, Integer id) {
		
		User user = null;
		
		if (User.FIELD_USERNAME.equals(field)) {
			user = userRepository.findByUsername(value.toString().toUpperCase());
		}
		
		if (user == null) return FieldDefinition.FIELD_VALUE_NOT_EXISTS;
		
		return (Long.valueOf(id) == user.getIdUser())
			? FieldDefinition.FIELD_VALUE_NOT_EXISTS 
			: FieldDefinition.FIELD_VALUE_EXISTS;
	}
	
	@Override
	public boolean entityExistsInDatabase(Long idUser) {
		
		User user = userRepository.findByIdUser(idUser);
		
		return user == null 
			? EntityDefinition.ENTITY_NOT_EXISTS 
			: EntityDefinition.ENTITY_EXISTS;
	}
}
