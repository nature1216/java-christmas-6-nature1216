package christmas.service;

import christmas.domain.Benefit;
import christmas.domain.Order;
import christmas.enumeration.BenefitType;
import christmas.enumeration.MenuType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

public class BenefitServiceTest {
    private final BenefitService benefitService = new BenefitService();

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

        assertThat(benefitService.applyWeekDiscount(benefit, order, date).getAmount(BenefitType.WEEKDAY_DISCOUNT))
                .isEqualTo(expected);
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
}
