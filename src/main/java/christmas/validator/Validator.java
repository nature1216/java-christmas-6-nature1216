package christmas.validator;

import christmas.enumeration.MenuType;
import christmas.enumeration.SystemValue;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    public static void validateDateInput(String input) {
        int date = validateDateType(input);
        if (isDateOutOfRange(date)) {
            throw new IllegalArgumentException();
        }
    }

    public static int validateDateType(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    private static boolean isDateOutOfRange(int input) {
        return input < Integer.parseInt(String.valueOf(SystemValue.FIRST_DATE.getValue())) ||
                input > Integer.parseInt(String.valueOf(SystemValue.END_DATE.getValue()));
    }

    public static void validateMenuInput(String input) {
        if (!isMatchFormat(input, SystemValue.MENU_INPUT_FORM.getValue().toString())) {
            throw new IllegalArgumentException();
        }
        List<String> menus = Arrays.asList(input.split(","));

        validateMenuNames(menus);
        validateMenuNums(menus);
    }

    private static void validateMenuNums(List<String> menus) {
        int num = 0;
        for (String menu : menus) {
            num += Integer.parseInt(Arrays.asList(menu.split("-")).get(1));
        }
        if (isInvalidMenuNum(num)) {
            throw new IllegalArgumentException();
        }
    }

    private static void validateMenuNames(List<String> menus) {
        for (String menu : menus) {
            String name = Arrays.asList(menu.split("-")).get(0);
            MenuType.getByName(name);
        }
    }

    private static boolean isInvalidMenuNum(int num) {
        return num > Integer.parseInt(String.valueOf(SystemValue.MAX_MENU_NUM.getValue()));
    }

    private static boolean isMatchFormat(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
}
