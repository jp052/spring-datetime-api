/*
 * Copyright and License:
 * UNLICENSE. Please visit the UNLICENSE.txt or refer to <http://unlicense.org> for more information.
 */

package com.plankdev.datetimeapi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * The class DateTimeApiConfig provides all application properties.
 * <p>
 * It is used to make all application properties available.
 * It can be make availbale in any class that depends on application properties via constructor injection.
 * As example: {@link com.plankdev.datetimeapi.api.DateTimeService}
 * <p>
 * The properties file is located at /datetime-api/src/main/resources/application.properties
 *
 * @author Jan Plank
 */

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix="dateTimeApi")
public class DateTimeApiConfig {

    private String durationFormat;

    public String getDurationFormat() {
        return durationFormat;
    }

    public void setDurationFormat(String durationFormat) {
        this.durationFormat = durationFormat;
    }
}
