package com.plankdev.datetimeapi.api;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.apache.commons.lang3.time.DurationFormatUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "api/v1/datetime")
@RestController
public class DateTimeRestController {
	private final String DURATION_FORMAT = "y:H:m:s";
	private final String DAY_FORMAT = "day";



	/**
	 *  Simple example URL: /api/v1/datetime/days?startDateTime=2018-09-05T05:30&endDateTime=2018-09-10T05:30
	 * @param startDateTime
	 * @param endDateTime
	 * @param startZone
	 * @param endZone
	 * @param format
	 * @return
	 */
	@GetMapping("/days")
	public JsonResult readDays (
						@RequestParam("startDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDateTime, 
						@RequestParam("endDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDateTime,
						@RequestParam(value = "startZone", required = false, defaultValue = "Australia/Adelaide") String startZone,
						@RequestParam(value = "endZone", required = false, defaultValue = "Australia/Adelaide") String endZone,
						@RequestParam(value = "format", required = false, defaultValue = DAY_FORMAT) String format) {

		//TODO: Refactor and put logic in separate classes, eg. DateTimeService
		//TODO: Improve format string to enum or just simple boolean

		ZoneId startZoneId = ZoneId.of(startZone);
		ZonedDateTime startDateTimeZoned = ZonedDateTime.of(startDateTime, startZoneId);

		ZoneId endZoneId = ZoneId.of(endZone);
		ZonedDateTime endDateTimeZoned = ZonedDateTime.of(endDateTime, endZoneId);
		
		Duration durationBetweenDates = Duration.between(startDateTimeZoned, endDateTimeZoned);
		Long days = durationBetweenDates.toDays();

		JsonResult jsonResult = new JsonResult();

		if(format.equals("day")) {
			jsonResult.setResult(days.toString());
			jsonResult.setFormat(DAY_FORMAT);
		} else {
			long startMillis = startDateTimeZoned.toInstant().toEpochMilli();
			long endMillis = endDateTimeZoned.toInstant().toEpochMilli();
			String formatedDuration = DurationFormatUtils.formatPeriod(startMillis, endMillis, DURATION_FORMAT);

			jsonResult.setResult(formatedDuration);
			jsonResult.setFormat(DURATION_FORMAT);
		}

		return jsonResult;
	}

	@GetMapping("/hello")
	public String hello() {
		return "{\"hello\":\"datetime api\"}";
	}


}