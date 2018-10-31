package com.plankdev.datetimeapi.api;

import com.plankdev.datetimeapi.config.Constants;
import com.plankdev.datetimeapi.config.DateTimeApiConfig;
import org.apache.commons.lang3.time.DurationFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.temporal.ChronoUnit;

@Service
public class DateTimeService {

    private DateTimeApiConfig config;

    @Autowired
    public DateTimeService(DateTimeApiConfig config) {
        this.config = config;
    }

    public DateTimeView findDays(LocalDateTime startDateTime, LocalDateTime endDateTime, String startZone, String endZone, String format) {
        ZonedDateTime startDateTimeZoned = createZonedDateTime(startDateTime, startZone);
        ZonedDateTime endDateTimeZoned = createZonedDateTime(endDateTime, endZone);

        Duration durationBetweenDates = Duration.between(startDateTimeZoned, endDateTimeZoned);
        Long days = durationBetweenDates.toDays();

        DateTimeView dateTimeView = new DateTimeView();
        if (isDefaultDurationFormat(format)) {
            dateTimeView.setResult(days.toString());
            dateTimeView.setFormat(Constants.DEFAULT_DURATION_FORMAT);
        } else {
            long startMillis = startDateTimeZoned.toInstant().toEpochMilli();
            long endMillis = endDateTimeZoned.toInstant().toEpochMilli();
            String formatedDuration = DurationFormatUtils.formatPeriod(startMillis, endMillis, config.getDurationFormat());  //TODO: Check if durationFormat is valid

            dateTimeView.setResult(formatedDuration);
            dateTimeView.setFormat(config.getDurationFormat());
        }

        return dateTimeView;
    }

    public DateTimeView findWeekdays(LocalDateTime startDateTime, LocalDateTime endDateTime, String startZone, String endZone, String format) {
        ZonedDateTime startDateTimeZoned = createZonedDateTime(startDateTime, startZone);
        ZonedDateTime endDateTimeZoned = createZonedDateTime(endDateTime, endZone);

        int weekdayCounter = 0;
        while (startDateTimeZoned.isBefore(endDateTimeZoned.plusDays(1))) {
            DayOfWeek currentWeekDay = startDateTimeZoned.getDayOfWeek();
            if(! (currentWeekDay.equals(DayOfWeek.SATURDAY) || currentWeekDay.equals(DayOfWeek.SUNDAY))) {
                weekdayCounter++;
            }

            startDateTimeZoned = startDateTimeZoned.plusDays(1);
        }

        DateTimeView dateTimeView = new DateTimeView();
        if(isDefaultDurationFormat(format)) {
            dateTimeView.setResult(Integer.toString(weekdayCounter));
            dateTimeView.setFormat(Constants.DEFAULT_DURATION_FORMAT);
        } else {
            Duration weekdaysDuration = Duration.ofDays(weekdayCounter);

            long periodInMillis = weekdaysDuration.toMillis();
            String formatedDuration = DurationFormatUtils.formatPeriod(0, periodInMillis, config.getDurationFormat());

            dateTimeView.setResult(formatedDuration);
            dateTimeView.setFormat(config.getDurationFormat());
        }

        return dateTimeView;
    }

    public DateTimeView findWeeks(LocalDateTime startDateTime, LocalDateTime endDateTime, String startZone, String endZone, String format) {
        ZonedDateTime startDateTimeZoned = createZonedDateTime(startDateTime, startZone);
        ZonedDateTime endDateTimeZoned = createZonedDateTime(endDateTime, endZone);

        long weeksBetweenDates = ChronoUnit.WEEKS.between(startDateTimeZoned, endDateTimeZoned);

        DateTimeView dateTimeView = new DateTimeView();
        if(isDefaultDurationFormat(format)) {
            dateTimeView.setResult(Long.toString(weeksBetweenDates));
            dateTimeView.setFormat(Constants.DEFAULT_DURATION_FORMAT);
        } else {
            Duration durationBetweenDates = Duration.between(startDateTimeZoned, endDateTimeZoned);

            long durationInMillis = durationBetweenDates.toMillis();
            String formatedDuration = DurationFormatUtils.formatPeriod(0, durationInMillis, config.getDurationFormat());

            dateTimeView.setResult(formatedDuration);
            dateTimeView.setFormat(config.getDurationFormat());
        }

        return dateTimeView;
    }

    private boolean isDefaultDurationFormat(String format) {
        return format.equals(Constants.DEFAULT_DURATION_FORMAT);
    }

    private ZonedDateTime createZonedDateTime(LocalDateTime startDateTime, String startZone) {
        ZoneId startZoneId = ZoneId.of(startZone);
        return ZonedDateTime.of(startDateTime, startZoneId);
    }
}
