package com.taller.asb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taller.asb.definition.ResponseDefinition;
import com.taller.asb.definition.TypeDefinition;
import com.taller.asb.dto.StatisticDto;
import com.taller.asb.manager.StatisticManager;
import com.taller.asb.response.ResponseService;

@RestController
public class StatisticController {
	
	@Autowired
	private StatisticManager statisticManager;

	@GetMapping("/statistics")
	public ResponseService getDashboardData() {
		ResponseService responseService = new ResponseService();
		responseService.setResponseCode(ResponseDefinition.RESPONSECODE_INTERNAL_SERVER_ERROR);
		responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_INTERNAL_SERVER_ERROR_S);
		
		try {
			List<StatisticDto> statisticDtoList = statisticManager.getDashboardData();
			
			responseService.setType(TypeDefinition.STATISTICS);
			responseService.setData(statisticDtoList);
			responseService.setResponseCode(ResponseDefinition.RESPONSECODE_OK);
			responseService.setResponseMessage(ResponseDefinition.RESPONSECODE_OK_S);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return responseService;
	}
}
