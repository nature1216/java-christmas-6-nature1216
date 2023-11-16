package christmas.service;

import christmas.domain.Benefit;
import christmas.domain.Order;
import christmas.enumeration.BadgeType;
import christmas.enumeration.BenefitType;
import christmas.enumeration.MenuType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class EventServiceTest {
    private final EventService eventService = new EventService();

    @DisplayName("입력값을 Order 객체로 변환한다.")
    @ParameterizedTest
    @ValueSource(strings = "바비큐립-1,티본스테이크-1,초코케이크-2,제로콜라-1")
    void stringToOrder(String string) {
        Order order = new Order();
        order.update(MenuType.BARBEQUE_RIP, 1);
        order.update(MenuType.T_BONE_STEAK, 1);
        order.update(MenuType.CHOCOLATE_CAKE, 2);
        order.update(MenuType.ZERO_COKE, 1);

        Order testOrder = eventService.stringToOrder(string);

        assertAll(
                () -> assertThat(testOrder.getNum(MenuType.BARBEQUE_RIP)).isEqualTo(1),
                () -> assertThat(testOrder.getNum(MenuType.T_BONE_STEAK)).isEqualTo(1),
                () -> assertThat(testOrder.getNum(MenuType.CHOCOLATE_CAKE)).isEqualTo(2),
                () -> assertThat(testOrder.getNum(MenuType.ZERO_COKE)).isEqualTo(1),
                () -> assertThat(testOrder.countDessert()).isEqualTo(2),
                () -> assertThat(testOrder.countMain()).isEqualTo(2)
        );
    }

    @DisplayName("할인 적용 후 결제 예상 금액을 반환한다.")
    @Test
    void calcTotalAfterDiscount() {
        Benefit benefit = new Benefit();
        benefit.update(BenefitType.GIFT_EVENT, 25000);
        benefit.update(BenefitType.X_MAS_DISCOUNT, 1200);
        benefit.update(BenefitType.WEEKEND_DISCOUNT, 2023);

        assertThat(eventService.calcTotalAfterDiscount(142000, benefit))
                .isEqualTo(138777);
    }

    @DisplayName("혜택 금액에 따라 배지를 부여한다.")
    @ParameterizedTest
    @CsvSource(value = {
            "4000, NONE",
            "5000, STAR",
            "10000, TREE",
            "20000, SANTA",
            "25000, SANTA"
    })
    void BadgeTest(int benefitAmount, BadgeType expected) {
        assertThat(eventService.awardBadge(benefitAmount)).isEqualTo(expected);
    }
}
