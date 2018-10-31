package com.plankdev.datetimeapi.api;

import java.time.*;

import com.plankdev.datetimeapi.config.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//TODO: Refactor and put logic in separate classes, eg. DateTimeService
//TODO: Improve format string to enum or just simple boolean

@RequestMapping(value = "api/v1/datetime")
@RestController
public class DateTimeRestController {

    private DateTimeService dateTimeService;

    @Autowired
    public DateTimeRestController(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }

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
	public DateTimeView findDays (
						@RequestParam("startDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDateTime, 
						@RequestParam("endDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDateTime,
						@RequestParam(value = "startZone", required = false, defaultValue = "Australia/Adelaide") String startZone,
						@RequestParam(value = "endZone", required = false, defaultValue = "Australia/Adelaide") String endZone,
						@RequestParam(value = "format", required = false, defaultValue = Constants.DEFAULT_DURATION_FORMAT) String format) {

        DateTimeView dateTimeView = dateTimeService.findDays(startDateTime, endDateTime, startZone, endZone, format);
        return dateTimeView;
	}


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

    @GetMapping("/ping")
	public String ping() {
		return "{\"ping\":\"datetime api up\"}";
	}


}