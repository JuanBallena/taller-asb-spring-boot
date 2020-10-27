package com.taller.asb.converter;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.taller.asb.definition.RoleDefinition;
import com.taller.asb.dto.ParameterDto;
import com.taller.asb.dto.RoleDto;
import com.taller.asb.dto.student.CreateStudentFormDto;
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
				.build();
	}
	
	public User toUserModel(CreateStudentFormDto createStudentFormDto) {
		return User.builder()
				.username(createStudentFormDto.getUsername().toUpperCase())
				.password(createStudentFormDto.getPassword())
				.role(Role.builder()
						.idRole(RoleDefinition.ROLE_STUDENT)
						.build())
				.build();
	}
	
	public User replaceValuesInUserModel(User user, UpdateUserFormDto updateUserFormDto) {
		user.setUsername(updateUserFormDto.getUsername().toUpperCase());
		user.setPassword(updateUserFormDto.getPassword());
		user.setRole(Role.builder()
				.idRole(Long.valueOf(updateUserFormDto.getIdRole()))
				.build());
		user.setStatus(Parameter.builder()
				.idParameter(Long.valueOf(updateUserFormDto.getIdStatus()))
				.build());
		
		return user;
	}
	
	public Page<UserDto> toUserDtoPage(Page<User> userPage) {
		
		Page<UserDto> userDtoPage = userPage.map((Function<User, UserDto>) user -> {
			return toUserDto(user);
		});
				
		return userDtoPage; 
	}
	
	public List<UserDto> toUserDtoList(List<User> userList) {
		
		List<UserDto> userDtoList= new LinkedList<UserDto>();
		
		for (User user : userList) {
			userDtoList.add(toUserDto(user));
		}
		
		return userDtoList;
	}
}
