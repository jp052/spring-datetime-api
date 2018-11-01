/*
 * Copyright and License:
 * UNLICENSE. Please visit the UNLICENSE.txt or refer to <http://unlicense.org> for more information.
 */

package com.plankdev.datetimeapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The class DatetimeApiApplication is the entry point of the datetime-api spring application.
 *
 * @author Jan Plank
 */

@SpringBootApplication
public class DatetimeApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatetimeApiApplication.class, args);
	}
}
