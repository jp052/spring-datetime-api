package com.plankdev.datetimeapi.api;

import com.plankdev.datetimeapi.api.testutils.RestControllerTestBase;

import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

public class DateTimeRestControllerTest extends RestControllerTestBase {
	private final static String ENDPOINT_BASE = "/api/v1/datetime";
	
	private final static String START_DATE = "startDateTime=";
	private final static String END_DATE = "endDateTime=";
	private final static String START_ZONE = "startZone=";
	private final static String END_ZONE = "endZone=";
	private final static String FORMAT = "format=";
	
	@Test
	public void testReadDays() throws Exception {
		//assemble
		LocalDate startDate = LocalDate.of(2018, 9, 5);
		LocalTime startTime = LocalTime.of(5, 30);
		LocalDateTime startDateTime = LocalDateTime.of(startDate, startTime);
		
		LocalDate endDate = LocalDate.of(2018, 9, 10);
		LocalTime endTime = LocalTime.of(5, 30);
		LocalDateTime endDateTime = LocalDateTime.of(endDate, endTime);
		
		String queryURL = ENDPOINT_BASE + "/days?" + START_DATE + startDateTime.toString() + "&" + END_DATE + endDateTime.toString();
		
		//action
		ResultActions readDaysResult = mockMvc.perform(get(queryURL));
		
		//assert
		readDaysResult
			.andExpect(status().is2xxSuccessful());
			//.andExpect(jsonPath("$.days").value(5))
			//.andExpect(jsonPath("$.format").value("day"));
	}
	
	@Test
	public void testHello() throws Exception {
		//assemble
		
		//action
		ResultActions readHelloResult = mockMvc.perform(get(ENDPOINT_BASE + "/hello"));
		
		//assert
		readHelloResult
			.andExpect(status().is2xxSuccessful())
            .andExpect(jsonPath("$.hello").value("datetime api"));
	}
}
