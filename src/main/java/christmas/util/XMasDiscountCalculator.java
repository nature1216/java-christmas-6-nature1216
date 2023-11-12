package christmas.util;

import christmas.enumeration.BenefitType;
import christmas.enumeration.SystemValue;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class XMasDiscountCalculator {
    public static int getDiscount(LocalDate date) {
        Long diff = ChronoUnit.DAYS.between(date, BenefitType.X_MAS_DISCOUNT.getStart());
        return Integer.parseInt(SystemValue.X_MAS_DISCOUNT_BASE.getValue().toString()) +
                diff.intValue() * 100;
    }
}
