package com.taller.asb.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taller.asb.converter.RoleConverter;
import com.taller.asb.dto.role.RoleDto;
import com.taller.asb.model.Role;
import com.taller.asb.repository.RoleRepository;

@Service
public class RoleManager {

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private RoleConverter roleConverter;
	
	public List<RoleDto> getRoleList() {
		List<Role> roleList = roleRepository.findAll();
		List<RoleDto> roleDtoList = roleConverter.toRoleDtoList(roleList);
		
		return roleDtoList;
	}
}
