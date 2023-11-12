package christmas.service;

import christmas.domain.Benefit;
import christmas.domain.Order;
import christmas.enumeration.BenefitType;
import christmas.enumeration.MenuType;
import christmas.enumeration.SystemValue;
import christmas.util.DateUtil;

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
        if (canGetGift(calcTotalBeforeDiscount(order), date, BenefitType.GIFT_EVENT)) {
            benefit.update(BenefitType.GIFT_EVENT);
        }

        return benefit;
    }

    private boolean canGetGift(int amount, LocalDate date, BenefitType benefitType) {
        return amount >= Integer.parseInt(SystemValue.GIFT_THRESHOLD.getValue().toString())
                && DateUtil.inEventPeriod(date, benefitType.getStart(), benefitType.getEnd());
    }
}
