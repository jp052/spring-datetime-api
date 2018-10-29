package com.plankdev.datetimeapi.api;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "api/v1/datetime")
@RestController
public class DateTimeRestController {
	
	///api/v1/datetime/days?startDateTime=2018-09-05T05:30&endDateTime=2018-09-10T05:30
	@GetMapping("/days")
	public String readDays (
						@RequestParam("startDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDateTime, 
						@RequestParam("endDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDateTime,
						@RequestParam(value = "startZone", required = false, defaultValue = "Australia/Adelaide") String startZone,
						@RequestParam(value = "endZone", required = false, defaultValue = "Australia/Adelaide") String endZone,
						@RequestParam(value = "format", required = false, defaultValue = "day") String format) {
		
		//check if endDate is younger than startdate
		//create ZonedDateTime
		//subtract startdate from endDate
		//convert to correct format (sec, min, day, year)
		
		String timeBetweenDates = "";
		
		return timeBetweenDates;
	}

	@GetMapping("/hello")
	public String hello() {
		return "{\"hello\":\"datetime api\"}";
	}


}