package com.taller.asb.converter;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.taller.asb.dto.ParameterDto;
import com.taller.asb.dto.RoleDto;
import com.taller.asb.dto.user.CreateUserDto;
import com.taller.asb.dto.user.UpdateUserDto;
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
	
	public Page<UserDto> toUserDtoPage(Page<User> userPage) {
		if (userPage == null) {
			return null;
		}
		
		Page<UserDto> userDtoPage = userPage.map((Function<User, UserDto>) user -> {
			return toUserDto(user);
		});
				
		return userDtoPage; 
	}
	
	public List<UserDto> toUserDtoList(List<User> userList) {
		
		if (userList == null) {
			return null;
		}
		
		List<UserDto> userDtoList= new LinkedList<UserDto>();
		
		for (User user : userList) {
			userDtoList.add(toUserDto(user));
		}
		
		return userDtoList;
	}
	
	public User toUserModelForCreate(CreateUserDto createUserDto) {
		return User.builder()
				.username(createUserDto.getUsername().toUpperCase())
				.password(createUserDto.getPassword())
				.role(Role.builder()
						.idRole(Long.valueOf(createUserDto.getIdRole()))
						.build())
				.name(createUserDto.getName().toUpperCase())
				.lastName(createUserDto.getLastName().toUpperCase())
				.documentType(Parameter.builder()
						.idParameter(Long.valueOf(createUserDto.getIdDocumentType()))
						.build())
				.document(createUserDto.getDocument())
				.address(createUserDto.getAddress())
				.phone(createUserDto.getPhone())
				.build();
	}
	
	public User toUserModelForUpdate(UpdateUserDto updateUserDto) {
		return User.builder()
				.idUser(Long.valueOf(updateUserDto.getIdUser()))
				.username(updateUserDto.getUsername().toUpperCase())
				.password(updateUserDto.getPassword())
				.role(Role.builder()
						.idRole(Long.valueOf(updateUserDto.getIdRole()))
						.build())
				.name(updateUserDto.getName().toUpperCase())
				.lastName(updateUserDto.getLastName().toUpperCase())
				.documentType(Parameter.builder()
						.idParameter(Long.valueOf(updateUserDto.getIdDocumentType()))
						.build())
				.document(updateUserDto.getDocument())
				.address(updateUserDto.getAddress())
				.phone(updateUserDto.getPhone())
				.build();
	}
}
