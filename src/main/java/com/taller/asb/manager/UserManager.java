package com.taller.asb.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.taller.asb.converter.UserConverter;
import com.taller.asb.dto.user.CreateUserDto;
import com.taller.asb.dto.user.UpdateUserDto;
import com.taller.asb.dto.user.UserDto;
import com.taller.asb.model.User;
import com.taller.asb.repository.UserRepository;
import com.taller.asb.response.ResponsePage;

@Service
public class UserManager implements UniqueValueManager {
	
	private static final String COLUMN_USERNAME = "User_Username";
	private static final String COLUMN_DOCUMENT = "User_Document";
	
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
	
	public UserDto saveUser(CreateUserDto createUserDto) {
		
		User user = userRepository.save(userConverter.toUserModelForCreate(createUserDto));
		userRepository.refresh(user);
		return userConverter.toUserDto(user);
	}
	
	public UserDto updateUser(UpdateUserDto updateUserDto) {
		
		User user = userRepository.save(userConverter.toUserModelForUpdate(updateUserDto));
		userRepository.refresh(user);
		return userConverter.toUserDto(user);
	}

	@Override
	public boolean valueExistsInDatabase(String column, Object value) {
		User user = new User();
		
		switch (column) {
			case COLUMN_USERNAME:
				user = userRepository.findByUsername(value.toString().toUpperCase());
				return user == null;
			case COLUMN_DOCUMENT:
				user = userRepository.findByDocument(value.toString());
				return user == null;	
			default:
				return false;
		}
	}
}
