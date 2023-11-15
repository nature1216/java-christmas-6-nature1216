package christmas.util;

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
}
