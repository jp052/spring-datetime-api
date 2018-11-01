/*
 * Copyright and License:
 * UNLICENSE. Please visit the UNLICENSE.txt or refer to <http://unlicense.org> for more information.
 */

package com.plankdev.datetimeapi.api;

import java.time.*;

import com.plankdev.datetimeapi.config.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The class DateTimeRestController defines all available routes and delegates to the service classes.
 * <p>
 * It is used to define routes and delegates to service classes.
 * General Error and Exception Handling is appropriate at controller level too.
 *
 * @author Jan Plank
 */

@RequestMapping(value = "api/v1/datetime")
@RestController
public class DateTimeRestController {

    private DateTimeService dateTimeService;

    @Autowired
    public DateTimeRestController(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }

	/**
	 * Endpoint to find days between two given dateTime parameters.
	 * <p>
	 * Request parameters are provided to modify the result and support time zones.
	 *
	 * @param startDateTime
	 * 			the start date is mandatory, it needs to be provided in ISO date time format. yyyy-MM-dd'T'HH:mm:ss.SSSZ, e.g. "2000-10-31T01:30:00.000-05:00"
	 * @param endDateTime
	 * 			the end date is mandatory, it needs to be provided in ISO date time format. yyyy-MM-dd'T'HH:mm:ss.SSSZ, e.g. "2000-10-31T01:30:00.000-05:00"
	 * @param startZone
	 * 			the start zone is optional, it represents a timezone. e.g. "Australia/Adelaide"
	 * @param endZone
	 *			the end zone is optional, it represents a timezone. e.g. "Australia/Adelaide"
	 * @param format
	 * 			the format is optional, if present it will create a different output format
	 * @return a new populated DateTimeView
	 */
	@GetMapping("/days")
	public DateTimeView findDays (
						@RequestParam("startDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDateTime, 
						@RequestParam("endDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDateTime,
						@RequestParam(value = "startZone", required = false, defaultValue = "Australia/Adelaide") String startZone,
						@RequestParam(value = "endZone", required = false, defaultValue = "Australia/Adelaide") String endZone,
						@RequestParam(value = "format", required = false, defaultValue = Constants.DEFAULT_DURATION_FORMAT) String format) {

        DateTimeView dateTimeView = dateTimeService.findDays(startDateTime, endDateTime, startZone, endZone, format);
        return dateTimeView;
	}

	/**
	 * Endpoint to find weekdays between two given dateTime parameters.
	 * <p>
	 * Request parameters are provided to modify the result and support time zones.
	 *
	 * @param startDateTime
	 * 			the start date is mandatory, it needs to be provided in ISO date time format. yyyy-MM-dd'T'HH:mm:ss.SSSZ, e.g. "2000-10-31T01:30:00.000-05:00"
	 * @param endDateTime
	 * 			the end date is mandatory, it needs to be provided in ISO date time format. yyyy-MM-dd'T'HH:mm:ss.SSSZ, e.g. "2000-10-31T01:30:00.000-05:00"
	 * @param startZone
	 * 			the start zone is optional, it represents a timezone. e.g. "Australia/Adelaide"
	 * @param endZone
	 *			the end zone is optional, it represents a timezone. e.g. "Australia/Adelaide"
	 * @param format
	 * 			the format is optional, if present it will create a different output format
	 * @return a new populated DateTimeView
	 */
    @GetMapping("/weekdays")
    public DateTimeView findWeekdays(
                               @RequestParam("startDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDateTime,
                               @RequestParam("endDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDateTime,
                               @RequestParam(value = "startZone", required = false, defaultValue = "Australia/Adelaide") String startZone,
                               @RequestParam(value = "endZone", required = false, defaultValue = "Australia/Adelaide") String endZone,
                               @RequestParam(value = "format", required = false, defaultValue = Constants.DEFAULT_DURATION_FORMAT) String format) {

        DateTimeView dateTimeView = dateTimeService.findWeekdays(startDateTime, endDateTime, startZone, endZone, format);
        return dateTimeView;
    }

	/**
	 * Endpoint to find weekdays between two given dateTime parameters.
	 * <p>
	 * Request parameters are provided to modify the result and support time zones.
	 *
	 * @param startDateTime
	 * 			the start date is mandatory, it needs to be provided in ISO date time format. yyyy-MM-dd'T'HH:mm:ss.SSSZ, e.g. "2000-10-31T01:30:00.000-05:00"
	 * @param endDateTime
	 * 			the end date is mandatory, it needs to be provided in ISO date time format. yyyy-MM-dd'T'HH:mm:ss.SSSZ, e.g. "2000-10-31T01:30:00.000-05:00"
	 * @param startZone
	 * 			the start zone is optional, it represents a timezone. e.g. "Australia/Adelaide"
	 * @param endZone
	 *			the end zone is optional, it represents a timezone. e.g. "Australia/Adelaide"
	 * @param format
	 * 			the format is optional, if present it will create a different output format
	 * @return a new populated DateTimeView
	 */
    @GetMapping("/weeks")
	public DateTimeView findWeeks(
			@RequestParam("startDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDateTime,
			@RequestParam("endDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDateTime,
			@RequestParam(value = "startZone", required = false, defaultValue = "Australia/Adelaide") String startZone,
			@RequestParam(value = "endZone", required = false, defaultValue = "Australia/Adelaide") String endZone,
			@RequestParam(value = "format", required = false, defaultValue = Constants.DEFAULT_DURATION_FORMAT) String format) {

        DateTimeView dateTimeView = dateTimeService.findWeeks(startDateTime, endDateTime, startZone, endZone, format);
        return dateTimeView;
	}

	/**
	 * Endpoint to get a simple message to check if the api is up.
	 * <p>
	 * Eventually should not be removed or put into a more suitable statistics controller.
	 * At time of creation no other controller is available.
	 * @return message that the api is up
	 */
    @GetMapping("/ping")
	public String ping() {
		return "{\"ping\":\"datetime api up\"}";
	}


}