package christmas.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MenuValidatorTest {
    @DisplayName("메뉴 입력 형식이 명시된 양식과 다르면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {
            "양송이수프 티본스테이크",
            "양송이수프,티본스테이크",
            "양송이수프2 티본스테이크2",
            "양송이수프-2 티본스테이크-2",
            "양송이수프2, 티본스테이크2"
    })
    void validateFormat_입력_형식이_다르면_예외(String input) {
        assertThatThrownBy(() -> MenuValidator.validateFormat(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }
}
