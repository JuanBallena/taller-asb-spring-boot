package com.taller.asb.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class RoleDto {

	private Long id;
	private String name;
	private String displayName;
}
