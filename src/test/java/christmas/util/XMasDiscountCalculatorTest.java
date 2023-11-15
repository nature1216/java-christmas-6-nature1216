package christmas.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class XMasDiscountCalculatorTest {
    @DisplayName("입력된 날짜에 해당하는 크리스마스 할인 가격을 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {
            "2023-12-01, 1000",
            "2023-12-03, 1200",
            "2023-12-25, 3400",
    })
    void getDiscount(LocalDate date, int expected) {
        assertThat(XMasDiscountCalculator.getDiscount(date)).isEqualTo(expected);
    }
}
