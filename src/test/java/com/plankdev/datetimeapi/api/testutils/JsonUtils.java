package com.plankdev.datetimeapi.api.testutils;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;

/**
 * Utility class to create json from a POJO or the other way around.
 */
public class JsonUtils {

    private static ObjectMapper mapperIgnoringFails = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    public JsonUtils(HttpMessageConverter mappingJackson2HttpMessageConverter) {
        if (mappingJackson2HttpMessageConverter == null) {
            throw new NullPointerException("mappingJackson2HttpMessageConverter has to be set");
        }
        this.mappingJackson2HttpMessageConverter = mappingJackson2HttpMessageConverter;
    }

    public <T> T jsonStringToPojo(String jsonString, Class<T> clazz) throws IOException {
        T model = null;
        try {
            model = mapperIgnoringFails.readValue(jsonString, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }

    public <T> T mvcResultToPojo(MvcResult mvcResult, Class<T> clazz) throws IOException {
        T createdPojo = null;
        try {
            String jobJsonAsString = mvcResult.getResponse().getContentAsString();
            createdPojo = jsonStringToPojo(jobJsonAsString, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return createdPojo;
    }

    public String pojoToJson(Object obj) throws IOException {
        String convertedString = null;
        try {

            MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
            this.mappingJackson2HttpMessageConverter.write(obj, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
            convertedString = mockHttpOutputMessage.getBodyAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertedString;
    }

}