package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static boolean isTimeBetweenHalfOpen(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) < 0;
    }
    public static boolean isDateBetweenClosed(LocalDate ld, LocalDate startDate, LocalDate endDate) {
        return ld.compareTo(startDate) >= 0 && ld.compareTo(endDate) <= 0;
    }

    public static LocalDate getMinDate (List<Meal> meals) {
        return meals.stream()
                .sorted(Comparator.comparing(Meal::getDate))
                .map(Meal::getDate)
                .findFirst()
                .get();
    }

    public static LocalDate getMaxDate (List<Meal> meals) {
        return meals.stream()
                .sorted(Comparator.comparing(Meal::getDate).reversed())
                .map(Meal::getDate)
                .findFirst()
                .get();
    }

    public static LocalDate validateDate(String date) {
        LocalDate localDate = null;
        try {
            localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return localDate;
    }

    public static LocalTime validateTime(String time) {
        LocalTime localTime = null;
        try {
            localTime = LocalTime.parse(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return localTime;
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }
}

