package ru.javawebinar.topjava.util;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalDateFormatter implements Formatter<LocalDate> {
    @Override
    public LocalDate parse(String text, Locale locale) throws ParseException {
        return DateTimeUtil.parseLocalDate(text);
    }

    @Override
    public String print(LocalDate date, Locale locale) {
        return date.format(DateTimeFormatter.ISO_DATE);
    }
}
