package com.plankdev.datetimeapi.api.datetimerestcontroller;

import com.plankdev.datetimeapi.testutils.RestControllerTestBase;
import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class WeeksTest extends RestControllerTestBase {
    private final static String ENDPOINT_BASE = "/api/v1/datetime/weeks";

    private final static String START_DATE = "startDateTime=";
    private final static String END_DATE = "endDateTime=";
    private final static String START_ZONE = "startZone=";
    private final static String END_ZONE = "endZone=";
    private final static String FORMAT = "format=";

    @Test
    public void testFindWeeksWithStartAndEndDateWorks() throws Exception {
        //assemble
        final String WEEKS_EXPECTED = "5";
        final String FORMAT_EXPECTED = "default";

        LocalDateTime startDateTime = LocalDateTime.of(2018, 9,5,5, 30);
        LocalDateTime endDateTime = LocalDateTime.of(2018, 10, 10, 5, 30);

        String queryURL = ENDPOINT_BASE + "?" + START_DATE + startDateTime.toString() + "&" + END_DATE + endDateTime.toString();

        //action
        ResultActions readDaysResult = mockMvc.perform(get(queryURL));

        //assert
        readDaysResult
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.result").value(WEEKS_EXPECTED))
                .andExpect(jsonPath("$.format").value(FORMAT_EXPECTED))
                .andDo(print());
    }

    @Test
    public void testFindWeeksWithFormatParameterWorks() throws Exception {
        //assemble
        final String RESULT_EXPECTED = "0:839:0:0"; //note: its not 840h because of daylight savings time change between the used dates.
        final String FORMAT_EXPECTED = "y:H:m:s";

        LocalDateTime startDateTime = LocalDateTime.of(2018, 9, 5, 5, 30);
        LocalDateTime endDateTime = LocalDateTime.of(2018, 10, 10, 5, 30);

        String queryURL = ENDPOINT_BASE + "?" + START_DATE + startDateTime.toString() + "&" + END_DATE + endDateTime.toString() + "&" + FORMAT + FORMAT_EXPECTED;

        //action
        ResultActions readDaysResult = mockMvc.perform(get(queryURL));

        //assert
        readDaysResult
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.result").value(RESULT_EXPECTED))
                .andExpect(jsonPath("$.format").value(FORMAT_EXPECTED))
                .andDo(print());
    }

}
