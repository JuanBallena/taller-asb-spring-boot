package com.taller.asb.dto.user;

import com.taller.asb.dto.ParameterDto;
import com.taller.asb.dto.RoleDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class UserDto {

	private Long id;
	private String username;
	private RoleDto role;
	private Boolean changePassword;
	private ParameterDto status;
	private Object links;
}
