package com.project.workingtime.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeConverter {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static LocalDateTime stringToLocalDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, formatter);
    }

    public static String localDateTimeToString(LocalDateTime dateTime) {
        return dateTime.format(formatter);
    }
}
