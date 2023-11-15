package christmas.service;

import christmas.domain.Benefit;
import christmas.domain.Order;
import christmas.enumeration.*;
import christmas.util.DateUtil;
import christmas.util.XMasDiscountCalculator;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;

public class EventService {
    public Order stringToOrder(String input) {
        String[] menus = input.split(",");
        Order orders = new Order();
        for (String menu : menus) { // 함수로 빼기
            String name = Arrays.asList(menu.split("-")).get(0);
            int num = Integer.parseInt(Arrays.asList(menu.split("-")).get(1));
            MenuType menuType = MenuType.getByName(name);

            orders.update(menuType, num);
        }
        return orders;
    }

    public int calcTotalBeforeDiscount(Order order) {
        int amount = 0;
        for (MenuType menuType : MenuType.values()) {
            if (order.getNum(menuType) > 0) {
                amount += menuType.getCost() * order.getNum(menuType);
            }
        }
        return amount;
    }

    public Benefit applyBenefit(Order order, int day) {
        Benefit benefit = new Benefit();
        LocalDate date = DateUtil.dayToDate(day);
        int totalBeforeDiscount = calcTotalBeforeDiscount(order);

        if (!canGetBenefit(totalBeforeDiscount)) {
            return benefit;
        }

        giveGift(benefit, date, totalBeforeDiscount);
        applyWeekDiscount(benefit, order, date);
        applyXmasDiscount(benefit, date);
        applySpecialDiscount(benefit, date);

        return benefit;
    }

    private void applyXmasDiscount(Benefit benefit, LocalDate date) {
        if (canGetDiscount(date, BenefitType.X_MAS_DISCOUNT)) {
            benefit.update(BenefitType.X_MAS_DISCOUNT, XMasDiscountCalculator.getDiscount(date));
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

    private void applySpecialDiscount(Benefit benefit, LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        if (canGetDiscount(date, BenefitType.SPECIAL_DISCOUNT)) {
            if (dayOfWeek == DayOfWeek.SUNDAY || date.isEqual(LocalDate.of(2023, 12, 25))) {
                benefit.update(BenefitType.SPECIAL_DISCOUNT, SystemNumValue.SPECIAL_DISCOUNT.getValue());
            }
        }
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

    private boolean canGetDiscount(LocalDate date, BenefitType benefitType) {
        return DateUtil.inEventPeriod(date, benefitType.getStart(), benefitType.getEnd());
    }

    private boolean isWeekDay(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek != DayOfWeek.FRIDAY &&
                dayOfWeek != DayOfWeek.SATURDAY;
    }

    private boolean canGetGift(int amount, LocalDate date) {
        return amount >= SystemNumValue.GIFT_THRESHOLD.getValue()
                && DateUtil.inEventPeriod(date, BenefitType.GIFT_EVENT.getStart(), BenefitType.GIFT_EVENT.getEnd());
    }

    public String getGiftOutput(Benefit benefit) {
        if (benefit.getAmount(BenefitType.GIFT_EVENT) == MenuType.getByName(SystemTextValue.GIFT.getValue()).getCost()) {
            return SystemTextValue.GIFT.getValue().toString() + " " +
                    SystemNumValue.GIFT_NUM.getValue() + "개\n";
        }
        return NoticeType.NONE.getMessage();
    }

    public int calcTotalAfterDiscount(int beforeAmount, Benefit benefit) {
        return beforeAmount - benefit.getTotalDiscount();
    }

    public BadgeType awardBadge(int benefitAmount) {
        if (benefitAmount >= BadgeType.STAR.getAmount() && benefitAmount < BadgeType.TREE.getAmount()) {
            return BadgeType.STAR;
        }
        if (benefitAmount >= BadgeType.TREE.getAmount() && benefitAmount < BadgeType.SANTA.getAmount()) {
            return BadgeType.TREE;
        }
        if (benefitAmount >= BadgeType.SANTA.getAmount()) {
            return BadgeType.SANTA;
        }
        return BadgeType.NONE;
    }
}
