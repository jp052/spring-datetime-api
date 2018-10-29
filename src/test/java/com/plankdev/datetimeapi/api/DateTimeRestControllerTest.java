package com.plankdev.datetimeapi.api;

import com.plankdev.datetimeapi.api.testutils.RestControllerTestBase;

import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.junit.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

public class DateTimeRestControllerTest extends RestControllerTestBase {
	private final static String ENDPOINT_BASE = "/api/v1/datetime";
	
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
