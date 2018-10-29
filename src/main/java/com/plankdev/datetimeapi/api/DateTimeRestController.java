package com.plankdev.datetimeapi.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "api/v1/datetime")
@RestController
public class DateTimeRestController {

	@GetMapping(value = "/hello")
	public String hello() {
		return "{\"hello\":\"datetime api\"}";
	}


}