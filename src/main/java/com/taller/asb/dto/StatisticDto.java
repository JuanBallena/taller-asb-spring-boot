package com.taller.asb.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class StatisticDto {
	
	private String description;
	private Integer quantity;
}
