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
        if (canGetGift(calcTotalBeforeDiscount(order), date)) {
            benefit.update(BenefitType.GIFT_EVENT,
                    MenuType.getByName(SystemValue.GIFT.getValue().toString()).getCost());
        }
        applyWeekDiscount(benefit, order, date);
        if (canGetDiscount(date, BenefitType.X_MAS_DISCOUNT)) {
            benefit.update(BenefitType.X_MAS_DISCOUNT, XMasDiscountCalculator.getDiscount(date));
        }
        if (canGetDiscount(date, BenefitType.SPECIAL_DISCOUNT)) {
            applySpecialDiscount(benefit, order, date);
        }

        return benefit;
    }

    private void applySpecialDiscount(Benefit benefit, Order order, LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        if (dayOfWeek == DayOfWeek.SUNDAY || date.isEqual(LocalDate.of(2023, 12, 25))) {
            benefit.update(BenefitType.SPECIAL_DISCOUNT, Integer.parseInt(SystemValue.SPECIAL_DISCOUNT.getValue().toString()));
        }
    }

    private Benefit applyWeekDiscount(Benefit benefit, Order order, LocalDate date) {
        if (isWeekDay(date) && canGetDiscount(date, BenefitType.WEEKDAY_DISCOUNT)) {
            int discount = Integer.parseInt(SystemValue.WEEKDAY_DISCOUNT_AMOUNT.getValue().toString()) * order.countDessert();
            benefit.update(BenefitType.WEEKDAY_DISCOUNT, discount);
            return benefit;
        }
        if (!isWeekDay(date) && canGetDiscount(date, BenefitType.WEEKEND_DISCOUNT)) {
            int discount = Integer.parseInt(SystemValue.WEEKEND_DISCOUNT_AMOUNT.getValue().toString()) * order.countMain();
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
                dayOfWeek != DayOfWeek.SATURDAY &&
                dayOfWeek != SystemValue.SPECIAL_DISCOUNT.getValue();
    }

    private boolean canGetGift(int amount, LocalDate date) {
        return amount >= Integer.parseInt(SystemValue.GIFT_THRESHOLD.getValue().toString())
                && DateUtil.inEventPeriod(date, BenefitType.GIFT_EVENT.getStart(), BenefitType.GIFT_EVENT.getEnd());
    }

    public String getGiftOutput(Benefit benefit) {
        if (benefit.getAmount(BenefitType.GIFT_EVENT) == MenuType.getByName(SystemValue.GIFT.getValue().toString()).getCost()) {
            return SystemValue.GIFT.getValue().toString() + " " +
                    SystemValue.GIFT_NUM.getValue().toString() + "개";
        }
        return NoticeType.NONE.getMessage();
    }

    public int calcTotalAfterDiscount(int beforeAmount, Benefit benefit) {
        return beforeAmount - benefit.getTotalDiscount();
    }

    public BadgeType awardBadge(int benefitAmount) {
        if(benefitAmount >= BadgeType.STAR.getAmount() && benefitAmount < BadgeType.TREE.getAmount()) {
            return BadgeType.STAR;
        }
        if(benefitAmount >= BadgeType.TREE.getAmount() && benefitAmount < BadgeType.SANTA.getAmount()) {
            return BadgeType.TREE;
        }
        if(benefitAmount >= BadgeType.SANTA.getAmount()) {
            return BadgeType.SANTA;
        }
        return BadgeType.NONE;
    }
}
