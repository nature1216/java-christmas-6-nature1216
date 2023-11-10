package christmas.service;

import christmas.domain.Orders;
import christmas.enumeration.MenuType;
import org.junit.jupiter.api.Order;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

public class EventService {
    public Orders stringToOrders(String input) {
        List<String> menus = Arrays.asList(input.split(","));
        Orders orders = new Orders();
        for(String menu : menus) {
            String name = Arrays.asList(menu.split("-")).get(0);
            int num = Integer.parseInt(Arrays.asList(menu.split("-")).get(1));
            MenuType menuType = MenuType.getByName(name);

            orders.update(menuType, num);
        }
        return orders;
    }
}
