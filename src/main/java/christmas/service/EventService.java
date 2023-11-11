package christmas.service;

import christmas.domain.Orders;
import christmas.enumeration.MenuType;

import java.util.Arrays;
import java.util.List;

public class EventService {
    public Orders stringToOrders(String input) {
        String[] menus = input.split(",");
        Orders orders = new Orders();
        for (String menu : menus) { // 함수로 빼기
            String name = Arrays.asList(menu.split("-")).get(0);
            int num = Integer.parseInt(Arrays.asList(menu.split("-")).get(1));
            MenuType menuType = MenuType.getByName(name);

            orders.update(menuType, num);
        }
        return orders;
    }

    public int calcTotalBeforeDiscount(Orders orders) {
        int amount = 0;
        for(MenuType menuType : MenuType.values()) {
            if(orders.getNum(menuType) > 0) {
                amount += menuType.getCost() * orders.getNum(menuType);
            }
        }
        return amount;
    }
}
