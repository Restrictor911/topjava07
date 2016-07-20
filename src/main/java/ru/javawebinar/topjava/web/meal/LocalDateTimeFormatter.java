package ru.javawebinar.topjava.web.meal;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Created by Restrictor on 20.07.2016.
 */
public class LocalDateTimeFormatter implements Formatter<LocalDateTime> {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;

    @Override
    public LocalDateTime parse(String s, Locale locale) throws ParseException {
        return LocalDateTime.parse(s);
    }

    @Override
    public String print(LocalDateTime dateTime, Locale locale) {
        return dateTime.format(DATE_TIME_FORMATTER);
    }
}
