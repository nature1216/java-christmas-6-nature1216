package christmas.service;

import christmas.domain.Benefit;
import christmas.domain.Order;
import christmas.enumeration.BenefitType;
import christmas.enumeration.MenuType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class BenefitServiceTest {
    private final BenefitService benefitService = new BenefitService();

    @DisplayName("할인 전 총주문금액이 10000원을 넘지 않는 경우 혜택이 적용되지 않는다.")
    @Test
    void applyBenefit_총주문금액_10000원_이하() {
        Order order = new Order();
        order.update(MenuType.TAPAS, 1);
        order.update(MenuType.ZERO_COKE, 1);
        Benefit benefit = benefitService.applyBenefit(order, 3, order.calcTotalCost());

        assertThat(benefit.getTotalBenefit()).isEqualTo(0);
    }

    @DisplayName("주말 할인을 적용한다.")
    @ParameterizedTest
    @CsvSource(value = {
            "2023-12-01, 4046",
            "2023-12-02, 4046",
            "2023-12-03, 0"
    })
    void applyWeekDiscount_평일_할인(LocalDate date, int expected) {
        Order order = new Order();
        Benefit benefit = new Benefit();
        order.update(MenuType.BARBEQUE_RIP, 1);
        order.update(MenuType.T_BONE_STEAK, 1);
        order.update(MenuType.CHOCOLATE_CAKE, 2);
        order.update(MenuType.ZERO_COKE, 1);

        assertThat(benefitService.applyWeekDiscount(benefit, order, date).getAmount(BenefitType.WEEKEND_DISCOUNT))
                .isEqualTo(expected);
    }

    @DisplayName("평일 할인을 적용한다.")
    @ParameterizedTest
    @CsvSource(value = {
            "2023-12-02, 0",
            "2023-12-03, 4046",
            "2023-12-04, 4046"
    })
    void applyWeekDiscount_주말_할인(LocalDate date, int expected) {
        Order order = new Order();
        Benefit benefit = new Benefit();
        order.update(MenuType.BARBEQUE_RIP, 1);
        order.update(MenuType.T_BONE_STEAK, 1);
        order.update(MenuType.CHOCOLATE_CAKE, 2);
        order.update(MenuType.ZERO_COKE, 1);
        benefitService.applyWeekDiscount(benefit, order, date);

        assertThat(benefit.getAmount(BenefitType.WEEKDAY_DISCOUNT)).isEqualTo(expected);
    }

    @DisplayName("크리스마스 디데이 할인을 적용한다.")
    @ParameterizedTest
    @CsvSource(value = {
            "2023-12-03, 1200",
            "2023-12-25, 3400",
            "2023-12-26, 0"
    })
    void applyXmasDiscount(LocalDate date, int expected) {
        Order order = new Order();
        Benefit benefit = new Benefit();
        order.update(MenuType.BARBEQUE_RIP, 1);
        order.update(MenuType.T_BONE_STEAK, 1);
        order.update(MenuType.CHOCOLATE_CAKE, 2);
        order.update(MenuType.ZERO_COKE, 1);
        benefitService.applyXmasDiscount(benefit, date);

        assertThat(benefit.getAmount(BenefitType.X_MAS_DISCOUNT)).isEqualTo(expected);
    }

    @DisplayName("특별 할인을 적용한다.")
    @ParameterizedTest
    @CsvSource(value = {
            "2023-12-03, 1000",
            "2023-12-04, 0",
            "2023-12-25, 1000"
    })
    void applySpecialDiscount(LocalDate date, int expected) {
        Order order = new Order();
        Benefit benefit = new Benefit();
        order.update(MenuType.BARBEQUE_RIP, 1);
        order.update(MenuType.T_BONE_STEAK, 1);
        order.update(MenuType.CHOCOLATE_CAKE, 2);
        order.update(MenuType.ZERO_COKE, 1);
        benefitService.applySpecialDiscount(benefit, date);

        assertThat(benefit.getAmount(BenefitType.SPECIAL_DISCOUNT)).isEqualTo(expected);
    }
}
