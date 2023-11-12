package christmas.service;

import christmas.domain.Benefit;
import christmas.domain.Order;
import christmas.enumeration.BenefitType;
import christmas.enumeration.MenuType;
import christmas.enumeration.SystemValue;
import christmas.util.DateUtil;

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
            benefit.update(BenefitType.GIFT_EVENT);
        }
        benefit = applyWeekDiscount(benefit, order, date);


        return benefit;
    }

    private Benefit applyWeekDiscount(Benefit benefit, Order order, LocalDate date) {
        if(isWeekDay(date)) {
            for(int i=0;i<order.countDessert();i++) {
                benefit.update(BenefitType.WEEKDAY_DISOUNT);
            }
            return benefit;
        }
        for(int i=0;i<order.countMain();i++) {
            benefit.update(BenefitType.WEEKEND_DISCOUNT);
        }
        return benefit;
    }

    private boolean isWeekDay(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek != DayOfWeek.FRIDAY && dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != SystemValue.SPECIAL_DISCOUNT.getValue();
    }

    private boolean canGetGift(int amount, LocalDate date) {
        return amount >= Integer.parseInt(SystemValue.GIFT_THRESHOLD.getValue().toString())
                && DateUtil.inEventPeriod(date, BenefitType.GIFT_EVENT.getStart(), BenefitType.GIFT_EVENT.getEnd());
    }

    public String getGiftOutput(Benefit benefit) {
        if (benefit.getNum(BenefitType.GIFT_EVENT) == 1) {
            return SystemValue.GIFT.getValue().toString() + " " +
                    SystemValue.GIFT_NUM.getValue().toString() + "개";
        }
        return "없음";
    }
}
