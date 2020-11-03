package com.taller.asb.dto.user;

import com.taller.asb.dto.parameter.ParameterDto;
import com.taller.asb.dto.role.RoleDto;

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
