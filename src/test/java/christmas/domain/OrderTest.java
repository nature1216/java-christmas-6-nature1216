package christmas.domain;

import christmas.enumeration.MenuType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderTest {
    @DisplayName("주문한 메뉴 중 디저트의 총 개수를 반환한다.")
    @Test
    void countDessert() {
        Order order = new Order();
        order.update(MenuType.BARBEQUE_RIP, 1);
        order.update(MenuType.CHOCOLATE_CAKE, 2);
        order.update(MenuType.ICE_CREAM, 1);
        order.update(MenuType.ZERO_COKE, 2);

        assertThat(order.countDessert()).isEqualTo(3);
    }

    @DisplayName("주문한 메뉴 중 메인메뉴의 총 개수를 반환한다.")
    @Test
    void countMain() {
        Order order = new Order();
        order.update(MenuType.BARBEQUE_RIP, 1);
        order.update(MenuType.ICE_CREAM, 1);
        order.update(MenuType.CHRISTMAS_PASTA, 2);
        order.update(MenuType.MUSHROOM_SOUP, 1);

        assertThat(order.countMain()).isEqualTo(3);

    }
}
