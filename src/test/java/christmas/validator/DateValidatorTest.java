package christmas.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class DateValidatorTest {
    @DisplayName("입력 가능한 날짜 범위를 벗어난 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 32, 100})
    void validateRange_날짜의_범위를_벗어난_경우_예외(int input) {
        assertThatThrownBy(() -> DateValidator.validateRange(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @DisplayName("입력 가능한 날짜의 범위에 해당하는 경우 예외가 발생하지 않는다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 15, 31})
    void validateRange_날짜의_범위에_해당하는_경우_성공(int input) {
        assertDoesNotThrow(() -> DateValidator.validateRange(input));
    }

    @DisplayName("입력값이 숫자가 아닌 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"가", ";", "a", "0.3", "9999999999"})
    void validateType_입력값이_숫자가_아닌_경우_예외(String input) {
        assertThatThrownBy(() -> DateValidator.validateType(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @DisplayName("입력값이 숫자가 아닌 경우 예외가 발생하지 않는다.")
    @ParameterizedTest
    @ValueSource(strings = {"1", "31", "50"})
    void validateType_입력값이_숫자인_경우_성공(String input) {
        assertDoesNotThrow(() -> DateValidator.validateType(input));
    }
}
