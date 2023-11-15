package christmas.validator;

import christmas.enumeration.ExceptionType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DateValidatorTest {
    @DisplayName("날짜 범위를 벗어난 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"0", "32", "100"})
    void validateInput_날짜의_범위를_벗어난_경우_예외(String input) {
        assertThatThrownBy(() -> DateValidator.validateInput(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @DisplayName("입력값이 숫자가 아닌 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"가", ";", "a", "0.3", "9999999999"})
    void validateInput_입력값이_숫자가_아닌_경우_예외(String input) {
        assertThatThrownBy(() -> DateValidator.validateInput(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }
}
