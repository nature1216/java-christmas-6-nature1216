package christmas.service;

import christmas.domain.Benefit;
import christmas.domain.Order;
import christmas.enumeration.*;

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
