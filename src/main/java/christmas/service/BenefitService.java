package christmas.service;

import christmas.domain.Benefit;
import christmas.domain.Order;
import christmas.enumeration.*;
import christmas.util.DateUtil;
import christmas.util.XMasDiscountCalculator;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class BenefitService {
    public Benefit applyBenefit(Order order, int day, int totalBeforeDiscount) {
        Benefit benefit = new Benefit();
        LocalDate date = DateUtil.dayToDate(day);

        if (!canGetBenefit(totalBeforeDiscount)) {
            return benefit;
        }

        giveGift(benefit, date, totalBeforeDiscount);
        applyWeekDiscount(benefit, order, date);
        applyXmasDiscount(benefit, date);
        applySpecialDiscount(benefit, date);

        return benefit;
    }

    public String getGiftOutput(Benefit benefit) {
        if (benefit.getAmount(BenefitType.GIFT_EVENT) == MenuType.getByName(SystemTextValue.GIFT.getValue()).getCost()) {
            return SystemTextValue.GIFT.getValue().toString() + " " +
                    SystemNumValue.GIFT_NUM.getValue() + "개\n";
        }
        return NoticeType.NONE.getMessage();
    }

    private Benefit applyWeekDiscount(Benefit benefit, Order order, LocalDate date) {
        if (isWeekDay(date) && canGetDiscount(date, BenefitType.WEEKDAY_DISCOUNT)) {
            int discount = SystemNumValue.WEEKDAY_DISCOUNT_AMOUNT.getValue() * order.countDessert();
            benefit.update(BenefitType.WEEKDAY_DISCOUNT, discount);
            return benefit;
        }
        if (!isWeekDay(date) && canGetDiscount(date, BenefitType.WEEKEND_DISCOUNT)) {
            int discount = SystemNumValue.WEEKEND_DISCOUNT_AMOUNT.getValue() * order.countMain();
            benefit.update(BenefitType.WEEKEND_DISCOUNT, discount);
            return benefit;
        }
        return benefit;
    }

    private void applyXmasDiscount(Benefit benefit, LocalDate date) {
        if (canGetDiscount(date, BenefitType.X_MAS_DISCOUNT)) {
            benefit.update(BenefitType.X_MAS_DISCOUNT, XMasDiscountCalculator.getDiscount(date));
        }
    }

    private void applySpecialDiscount(Benefit benefit, LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        if (canGetDiscount(date, BenefitType.SPECIAL_DISCOUNT)) {
            if (dayOfWeek == DayOfWeek.SUNDAY || date.isEqual(LocalDate.of(2023, 12, 25))) {
                benefit.update(BenefitType.SPECIAL_DISCOUNT, SystemNumValue.SPECIAL_DISCOUNT.getValue());
            }
        }
    }

    private void giveGift(Benefit benefit, LocalDate date, int totalBeforeDiscount) {
        if (canGetGift(totalBeforeDiscount, date)) {
            benefit.update(BenefitType.GIFT_EVENT,
                    MenuType.getByName(SystemTextValue.GIFT.getValue()).getCost());
        }
    }

    private boolean canGetBenefit(int totalAmount) {
        return totalAmount >= SystemNumValue.MINIMUM_AMOUNT_FOR_BENEFIT.getValue();
    }

    private boolean canGetDiscount(LocalDate date, BenefitType benefitType) {
        return DateUtil.inEventPeriod(date, benefitType.getStart(), benefitType.getEnd());
    }

    private boolean canGetGift(int amount, LocalDate date) {
        return amount >= SystemNumValue.GIFT_THRESHOLD.getValue()
                && DateUtil.inEventPeriod(date, BenefitType.GIFT_EVENT.getStart(), BenefitType.GIFT_EVENT.getEnd());
    }

    private boolean isWeekDay(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek != DayOfWeek.FRIDAY &&
                dayOfWeek != DayOfWeek.SATURDAY;
    }
}
