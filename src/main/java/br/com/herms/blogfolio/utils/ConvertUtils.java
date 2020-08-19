package br.com.herms.blogfolio.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ConvertUtils {

    public static LocalDate stringToLocalDate(String dateStr) {
        if(dateStr != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(dateStr, formatter);
        }

        return null;
    }

    public static String localDateToString(LocalDate date) {
        if(date != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return date.format(formatter);
        }
        return null;
    }
}
