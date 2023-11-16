package christmas.util;

import christmas.enumeration.BenefitType;
import christmas.enumeration.SystemNumValue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static LocalDate dayToDate(int dayInput) {
        String year = String.valueOf(SystemNumValue.EVENT_YEAR.getValue());
        String month = String.valueOf(SystemNumValue.EVENT_MONTH.getValue());
        String day = Integer.toString(dayInput);
        if (dayInput < 10) {
            day = "0" + day;
        }

        return LocalDate.parse(year + month + day, DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    public static boolean inEventPeriod(LocalDate currentDate, BenefitType benefitType) {
        return !currentDate.isBefore(benefitType.getStart()) && !currentDate.isAfter(benefitType.getEnd());
    }
}
