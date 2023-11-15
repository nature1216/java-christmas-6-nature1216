package christmas.util;

import christmas.enumeration.BenefitType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class DateUtilTest {
    @DisplayName("날짜를 입력받아 LocalDate 객체로 변환한다.")
    @ParameterizedTest
    @CsvSource(value = {
            "3, 2023-12-03",
            "25, 2023-12-25"
    })
    void dateToDate(int day, LocalDate expected) {
        assertThat(DateUtil.dayToDate(day)).isEqualTo(expected);
    }

    @DisplayName("날짜가 이벤트 기간에 해당되는지 여부를 판단한다.")
    @ParameterizedTest
    @CsvSource(value = {
            "2023-12-03, WEEKDAY_DISCOUNT, true",
            "2023-12-26, X_MAS_DISCOUNT, false",
            "2023-12-25, X_MAS_DISCOUNT, true",
            "2023-12-31, GIFT_EVENT, true"
    })
    void isEventPeriod(LocalDate current, BenefitType benefitType, boolean expected) {
        assertThat(DateUtil.inEventPeriod(current, benefitType)).isEqualTo(expected);
    }
}
