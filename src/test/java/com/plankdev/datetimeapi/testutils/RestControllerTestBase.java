/*
 * Copyright and License:
 * UNLICENSE. Please visit the UNLICENSE.txt or refer to <http://unlicense.org> for more information.
 */

package com.plankdev.datetimeapi.testutils;



import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/*
Static import needed in sub classes:
    import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
    import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
    import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
    import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
*/

/**
 * The class RestControllerTestBase is the base class for integration tests.
 * <p>
 * It is used to prepare and start the mocked spring boot application.
 *
 * @author Jan Plank
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public abstract class RestControllerTestBase {
    protected MockMvc mockMvc;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    protected MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Autowired
    private WebApplicationContext context;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {
        this.mappingJackson2HttpMessageConverter = Arrays.stream(converters)
                .filter(converter -> converter instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null", this.mappingJackson2HttpMessageConverter);
    }

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

}