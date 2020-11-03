package com.taller.asb.converter;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.taller.asb.dto.role.RoleDto;
import com.taller.asb.model.Role;

@Component
public class RoleConverter {
	
	public RoleDto toRoleDto(Role role) {
		if (role == null) return null;
		
		return RoleDto.builder()
				.id(role.getIdRole())
				.name(role.getName())
				.displayName(role.getDisplayName())
				.build();
	}
	
	public List<RoleDto> toRoleDtoList(List<Role> roleList) {
		
		List<RoleDto> roleDtoList = new LinkedList<RoleDto>();
		
		for (Role role : roleList) {
			roleDtoList.add(toRoleDto(role));
		}
		
		return roleDtoList;
	}
}
