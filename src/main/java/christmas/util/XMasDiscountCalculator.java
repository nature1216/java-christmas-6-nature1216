package christmas.util;

import christmas.enumeration.BenefitType;
import christmas.enumeration.SystemNumValue;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class XMasDiscountCalculator {
    public static int getDiscount(LocalDate date) {
        long diff = ChronoUnit.DAYS.between(date, BenefitType.X_MAS_DISCOUNT.getStart());
        return SystemNumValue.X_MAS_DISCOUNT_BASE.getValue() + Math.abs((int) diff) * 100;
    }
}
