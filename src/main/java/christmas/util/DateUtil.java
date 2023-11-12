package christmas.util;

import christmas.enumeration.SystemValue;

import java.time.LocalDate;

public class DateUtil {
    public static LocalDate dayToDate(int day) {
        String year = SystemValue.EVENT_YEAR.getValue().toString();
        String month = SystemValue.EVENT_MONTH.getValue().toString();

        return LocalDate.parse(year + month + day);
    }

    public static boolean inEventPeriod(LocalDate currentDate, LocalDate startDate, LocalDate endDate) {
        return !currentDate.isBefore(startDate) && !currentDate.isAfter(endDate);
    }
}
