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
    @MethodSource("inValidNumProvider")
    void validateNums_주문한_메뉴갸_20개_초과이면_예외(List<String> menus) {
        assertThatThrownBy(() -> MenuValidator.validateNums(menus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("주문한 메뉴의 개수가 20개 이하이면 예외가 발생하지 않는다.")
    @ParameterizedTest
    @MethodSource("validNumProvider")
    void validateNums_주문한_메뉴갸_20개_이하이면_성공(List<String> menus) {
        assertDoesNotThrow(() -> MenuValidator.validateNums(menus));
    }

    @DisplayName("메뉴의 이름이 일치하지 않는 경우 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("invalidNameProvider")
    void validateNames_메뉴의_이름이_일치하지_않으면_예외(List<String> menus) {
        assertThatThrownBy(() -> MenuValidator.validateNames(menus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("메뉴의 이름이 일치하는 경우 예외가 발생하지 않는다.")
    @ParameterizedTest
    @MethodSource("validNameProvider")
    void validateNames_메뉴의_이름이_일치하면_성공(List<String> menus) {
        assertDoesNotThrow(() -> MenuValidator.validateNames(menus));
    }

    @DisplayName("음료만 주문한 경우 경우 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("onlyDrinkProvider")
    void validateNames_음료만_주문하면_예외(List<String> menus) {
        assertThatThrownBy(() -> MenuValidator.validateNames(menus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 음료만 주문할 수 없습니다.");
    }

    static Stream<List<String>> inValidNumProvider() {
        return Stream.of(
                Arrays.asList("양송이수프-5", "티본스테이크-6", "제로콜라-5", "초코케이크-5"),
                List.of("양송이수프-30")
        );
    }

    static Stream<List<String>> validNumProvider() {
        return Stream.of(
                Arrays.asList("양송이수프-5", "티본스테이크-5", "제로콜라-5", "초코케이크-5"),
                List.of("양송이수프-20")
        );
    }

    static Stream<List<String>> invalidNameProvider() {
        return Stream.of(
                Arrays.asList("양송이스프-5", "제로콜라-5"),
                Arrays.asList("티본스테이크-2","제로콕-5"),
                List.of("비비큐립-2"),
                List.of("초콜릿케익-5")
        );
    }

    static Stream<List<String>> validNameProvider() {
        return Stream.of(
                Arrays.asList("양송이수프-5", "제로콜라-5"),
                Arrays.asList("티본스테이크-2","제로콜라-5"),
                List.of("바비큐립-2"),
                List.of("초코케이크-5")
        );
    }

    static Stream<List<String>> onlyDrinkProvider() {
        return Stream.of(
                Arrays.asList("제로콜라-2", "샴페인-1"),
                List.of("제로콜라-5")
        );
    }
}
