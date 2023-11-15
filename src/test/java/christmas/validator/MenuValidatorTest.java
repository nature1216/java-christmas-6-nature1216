package christmas.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class MenuValidatorTest {
    @DisplayName("메뉴 입력 형식이 틀리면 예외가 발생한다.")
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

    @DisplayName("메뉴 입력 형식이 올바르면 예외가 발생하지 않는다.")
    @ParameterizedTest
    @ValueSource(strings = {"양송이수프-2,티본스테이크-1"})
    void validateFormat_입력_형식이_올바르면_성공(String input) {
        assertDoesNotThrow(() -> MenuValidator.validateFormat(input));
    }

    @DisplayName("주문한 메뉴의 개수가 20개 초과이면 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("inValidMenuProvider")
    void validateNums_주문한_메뉴갸_20개_초과이면_예외(List<String> menus) {
        assertThatThrownBy(() -> MenuValidator.validateNums(menus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("주문한 메뉴의 개수가 20개 이하이면 예외가 발생하지 않는다.")
    @ParameterizedTest
    @MethodSource("validMenuProvider")
    void validateNums_주문한_메뉴갸_20개_이하이면_성공(List<String> menus) {
        assertDoesNotThrow(() -> MenuValidator.validateNums(menus));
    }

    static Stream<List<String>> inValidMenuProvider() {
        return Stream.of(
                Arrays.asList("양송이수프-5", "티본스테이크-6", "제로콜라-5", "초코케이크-5"),
                List.of("양송이수프-30")
        );
    }

    static Stream<List<String>> validMenuProvider() {
        return Stream.of(
                Arrays.asList("양송이수프-5", "티본스테이크-5", "제로콜라-5", "초코케이크-5"),
                List.of("양송이수프-20")
        );
    }

}
