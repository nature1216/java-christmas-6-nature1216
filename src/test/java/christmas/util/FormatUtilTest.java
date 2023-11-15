package christmas.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class FormatUtilTest {
    @DisplayName("입력된 숫자 3자리마다 콤마(,)를 삽입하여 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {
            "100000, '100,000'",
            "100, '100'",
            "15000, '15,000'"
    })
    void toWonFormat(int origin, String expected) {
        assertThat(FormatUtil.toWonFormat(origin)).isEqualTo(expected);
    }
}
