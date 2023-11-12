package christmas.util;

import christmas.enumeration.SystemValue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static LocalDate dayToDate(int dayInput) {
        String year = SystemValue.EVENT_YEAR.getValue().toString();
        String month = SystemValue.EVENT_MONTH.getValue().toString();
        String day = Integer.toString(dayInput);
        if (dayInput < 10) {
            day = "0" + day;
        }

        return LocalDate.parse(year + month + day, DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    public static boolean inEventPeriod(LocalDate currentDate, LocalDate startDate, LocalDate endDate) {
        return !currentDate.isBefore(startDate) && !currentDate.isAfter(endDate);
    }
}
