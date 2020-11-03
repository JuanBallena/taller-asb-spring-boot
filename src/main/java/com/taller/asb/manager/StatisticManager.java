package com.taller.asb.manager;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taller.asb.definition.StatisticDefinition;
import com.taller.asb.dto.StatisticDto;

@Service
public class StatisticManager {
	
	@Autowired
	private ExStudentManager exStudentManager;

	public List<StatisticDto> getDashboardData() {
		
		List<StatisticDto> statisticDtoList = new LinkedList<>();
		
		statisticDtoList.add(getNumberOfActiveExStudent());
		
		return statisticDtoList;
	}
	
	public StatisticDto getNumberOfActiveExStudent() {
		
		return StatisticDto.builder()
				.description(StatisticDefinition.NUMBER_OF_ACTIVE_EX_STUDENTS)
				.quantity(exStudentManager.countActiveExStudent())
				.build();
	}
}
