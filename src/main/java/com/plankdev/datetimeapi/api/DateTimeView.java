/*
 * Copyright and License:
 * UNLICENSE. Please visit the UNLICENSE.txt or refer to <http://unlicense.org> for more information.
 */

package com.plankdev.datetimeapi.api;

/**
 * The class DateTimeView is a representation of the datetime calculation.
 * <p>
 * It is used to show the result to the client.
 *
 * @author Jan Plank
 */

public class DateTimeView {

    private String result;
    private String format;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
