package christmas.domain;

import christmas.enumeration.MenuType;

import java.util.EnumMap;

public class Orders {
    EnumMap<MenuType, Integer> orders;

    public Orders() {
        orders = new EnumMap<>(MenuType.class);
        for(MenuType menuType : MenuType.values()) {
            orders.put(menuType, 0);
        }
    }

    public void update(MenuType menuType, int num) {
        orders.put(menuType, num);
    }
}
