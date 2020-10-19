package com.taller.asb.converter;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.taller.asb.dto.ParameterDto;
import com.taller.asb.dto.RoleDto;
import com.taller.asb.dto.user.CreateUserFormDto;
import com.taller.asb.dto.user.UpdateUserFormDto;
import com.taller.asb.dto.user.UserDto;
import com.taller.asb.model.Parameter;
import com.taller.asb.model.Role;
import com.taller.asb.model.User;

@Component
public class UserConverter {

	public UserDto toUserDto(User user) {
		
		if (user == null) return null;
		
		return UserDto.builder()
				.id(user.getIdUser())
				.username(user.getUsername())
				.role(RoleDto.builder()
						.id(user.getRole().getIdRole())
						.name(user.getRole().getName())
						.displayName(user.getRole().getDisplayName())
						.build())
				.urlLocationPhoto(user.getUrlLocationPhoto())
				.name(user.getName())
				.lastName(user.getLastName())
				.documentType(ParameterDto.builder()
						.id(user.getDocumentType().getIdParameter())
						.description(user.getDocumentType().getDescription())
						.build())
				.document(user.getDocument())
				.address(user.getAddress())
				.phone(user.getPhone())
				.changePassword(user.getChangePassword())
				.status(ParameterDto.builder()
						.id(user.getStatus().getIdParameter())
						.description(user.getStatus().getDescription())
						.build())
				.build();
	}
	
	public User toUserModel(CreateUserFormDto createUserFormDto) {
		return User.builder()
				.username(createUserFormDto.getUsername().toUpperCase())
				.password(createUserFormDto.getPassword())
				.role(Role.builder()
						.idRole(Long.valueOf(createUserFormDto.getIdRole()))
						.build())
				.name(createUserFormDto.getName().toUpperCase())
				.lastName(createUserFormDto.getLastName().toUpperCase())
				.documentType(Parameter.builder()
						.idParameter(Long.valueOf(createUserFormDto.getIdDocumentType()))
						.build())
				.document(createUserFormDto.getDocument())
				.address(createUserFormDto.getAddress())
				.phone(createUserFormDto.getPhone())
				.build();
	}
	
	public User toUserModel(UpdateUserFormDto updateUserFormDto) {
		return User.builder()
				.idUser(Long.valueOf(updateUserFormDto.getIdUser()))
				.username(updateUserFormDto.getUsername().toUpperCase())
				.password(updateUserFormDto.getPassword())
				.role(Role.builder()
						.idRole(Long.valueOf(updateUserFormDto.getIdRole()))
						.build())
				.name(updateUserFormDto.getName().toUpperCase())
				.lastName(updateUserFormDto.getLastName().toUpperCase())
				.documentType(Parameter.builder()
						.idParameter(Long.valueOf(updateUserFormDto.getIdDocumentType()))
						.build())
				.document(updateUserFormDto.getDocument())
				.address(updateUserFormDto.getAddress())
				.phone(updateUserFormDto.getPhone())
				.build();
	}
	
	public Page<UserDto> toUserDtoPage(Page<User> userPage) {
		if (userPage == null) return null;
		
		Page<UserDto> userDtoPage = userPage.map((Function<User, UserDto>) user -> {
			return toUserDto(user);
		});
				
		return userDtoPage; 
	}
	
	public List<UserDto> toUserDtoList(List<User> userList) {
		
		if (userList == null) return null;
		
		List<UserDto> userDtoList= new LinkedList<UserDto>();
		
		for (User user : userList) {
			userDtoList.add(toUserDto(user));
		}
		
		return userDtoList;
	}
	
	
	
	
}
