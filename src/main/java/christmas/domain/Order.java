package christmas.domain;

import christmas.enumeration.MenuType;

import java.util.EnumMap;

public class Order {
    EnumMap<MenuType, Integer> orders;

    public Order() {
        orders = new EnumMap<>(MenuType.class);
        for(MenuType menuType : MenuType.values()) {
            orders.put(menuType, 0);
        }
    }

    public void update(MenuType menuType, int num) {
        orders.put(menuType, num);
    }

    public int getNum(MenuType menuType) {
        return orders.get(menuType);
    }

    public int countDessert() {
        int count = 0;
        for(MenuType menuType : MenuType.values()) {
            if(menuType.getCategory().equals("DESSERT") && getNum(menuType) >= 1) {
                count++;
            }
        }
        return count;
    }

    public int countMain() {
        int count = 0;
        for(MenuType menuType : MenuType.values()) {
            if(menuType.getCategory().equals("MAIN") && getNum(menuType) >= 1) {
                count++;
            }
        }
        return count;
    }

}
