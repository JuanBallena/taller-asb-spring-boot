package com.taller.asb.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ParameterDto {

	private Long id;
	private String description;
}
