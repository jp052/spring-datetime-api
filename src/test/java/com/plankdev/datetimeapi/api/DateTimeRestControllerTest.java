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

//TODO:
/*
 * - Add test for different timezones
 * - Improve URL building
 * - Refactore to remove duplicate code
 * - Add failing tests
 */
public class DateTimeRestControllerTest extends RestControllerTestBase {
	private final static String ENDPOINT_BASE = "/api/v1/datetime";
	
	private final static String START_DATE = "startDateTime=";
	private final static String END_DATE = "endDateTime=";
	private final static String START_ZONE = "startZone=";
	private final static String END_ZONE = "endZone=";
	private final static String FORMAT = "format=";
	
	@Test
	public void testReadDaysWithStartAndEndDateWorks() throws Exception {
		//assemble
		final String DAYS_EXPECTED = "5";
		final String FORMAT_EXPECTED = "day";
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
			.andExpect(status().is2xxSuccessful())
			.andExpect(jsonPath("$.result").value(DAYS_EXPECTED))
			.andExpect(jsonPath("$.format").value(FORMAT_EXPECTED))
			.andDo(print());
	}

	@Test
	public void testReadDaysWithStartEndDateAndFormatParameterWorks() throws Exception {
		//assemble
		final String RESULT_EXPECTED = "3:119:50:15";
		final String FORMAT_EXPECTED = "y:H:m:s";
		LocalDate startDate = LocalDate.of(2015, 9, 5);
		LocalTime startTime = LocalTime.of(5, 30);
		LocalDateTime startDateTime = LocalDateTime.of(startDate, startTime);

		LocalDate endDate = LocalDate.of(2018, 9, 10);
		LocalTime endTime = LocalTime.of(5, 20, 15);
		LocalDateTime endDateTime = LocalDateTime.of(endDate, endTime);

		String queryURL = ENDPOINT_BASE + "/days?" + START_DATE + startDateTime.toString() + "&" + END_DATE + endDateTime.toString() + "&" + FORMAT + FORMAT_EXPECTED;

		//action
		ResultActions readDaysResult = mockMvc.perform(get(queryURL));

		//assert
		readDaysResult
				.andExpect(status().is2xxSuccessful())
				.andExpect(jsonPath("$.result").value(RESULT_EXPECTED))
				.andExpect(jsonPath("$.format").value(FORMAT_EXPECTED))
				.andDo(print());
	}

	@Test
	public void testReadDaysWithTimeZonesWorks() throws Exception {
		//assemble
		final String DAYS_EXPECTED = "6";
		final String FORMAT_EXPECTED = "day";
		final String TIMEZONE_START_EXPECTED = "Australia/Adelaide";
		final String TIMEZONE_END_EXPECTED = "Europe/Berlin";

		LocalDate startDate = LocalDate.of(2018, 9, 5);
		LocalTime startTime = LocalTime.of(5, 30);
		LocalDateTime startDateTime = LocalDateTime.of(startDate, startTime);

		LocalDate endDate = LocalDate.of(2018, 9, 10);
		LocalTime endTime = LocalTime.of(23, 30);
		LocalDateTime endDateTime = LocalDateTime.of(endDate, endTime);

		String queryURL = ENDPOINT_BASE + "/days?" + START_DATE + startDateTime.toString() + "&" + END_DATE + endDateTime.toString();
		String querURLZone = queryURL + "&" + START_ZONE + TIMEZONE_START_EXPECTED + "&" + END_ZONE + TIMEZONE_END_EXPECTED;

		//action
		ResultActions readDaysResult = mockMvc.perform(get(querURLZone));

		//assert
		readDaysResult
				.andExpect(status().is2xxSuccessful())
				.andExpect(jsonPath("$.result").value(DAYS_EXPECTED))
				.andExpect(jsonPath("$.format").value(FORMAT_EXPECTED))
				.andDo(print());
	}

	@Test
    public void testFindWeekdaysWithStartAndEndDateWorks() throws Exception {
        //assemble
        final String DAYS_EXPECTED = "4";
        final String FORMAT_EXPECTED = "day";
        LocalDate startDate = LocalDate.of(2018, 9, 5);
        LocalTime startTime = LocalTime.of(5, 30);
        LocalDateTime startDateTime = LocalDateTime.of(startDate, startTime);

        LocalDate endDate = LocalDate.of(2018, 9, 10);
        LocalTime endTime = LocalTime.of(5, 30);
        LocalDateTime endDateTime = LocalDateTime.of(endDate, endTime);

        String queryURL = ENDPOINT_BASE + "/weekdays?" + START_DATE + startDateTime.toString() + "&" + END_DATE + endDateTime.toString();

        //action
        ResultActions readDaysResult = mockMvc.perform(get(queryURL));

        //assert
        readDaysResult
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.result").value(DAYS_EXPECTED))
                .andExpect(jsonPath("$.format").value(FORMAT_EXPECTED))
                .andDo(print());
    }

    @Test
    public void testFindWeekdaysWithStartEndDateAndFormatParameterWorks() throws Exception {
        //assemble
        final String RESULT_EXPECTED = "0:96:0:0";
        final String FORMAT_EXPECTED = "y:H:m:s";
        LocalDate startDate = LocalDate.of(2018, 9, 5);
        LocalTime startTime = LocalTime.of(5, 30);
        LocalDateTime startDateTime = LocalDateTime.of(startDate, startTime);

        LocalDate endDate = LocalDate.of(2018, 9, 10);
        LocalTime endTime = LocalTime.of(5, 20, 50);
        LocalDateTime endDateTime = LocalDateTime.of(endDate, endTime);

        String queryURL = ENDPOINT_BASE + "/weekdays?" + START_DATE + startDateTime.toString() + "&" + END_DATE + endDateTime.toString() + "&" + FORMAT + FORMAT_EXPECTED;

        //action
        ResultActions readDaysResult = mockMvc.perform(get(queryURL));

        //assert
        readDaysResult
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.result").value(RESULT_EXPECTED))
                .andExpect(jsonPath("$.format").value(FORMAT_EXPECTED))
                .andDo(print());
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
