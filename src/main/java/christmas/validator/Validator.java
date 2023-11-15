package christmas.validator;

import christmas.enumeration.ExceptionType;
import christmas.enumeration.MenuType;
import christmas.enumeration.SystemNumValue;
import christmas.enumeration.SystemTextValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    public static void validateDateInput(String input) {
        int date = validateDateType(input);
        if (isDateOutOfRange(date)) {
            throw new IllegalArgumentException(ExceptionType.INVALID_DATE_TYPE.getMessage());
        }
    }

    public static int validateDateType(String input) {
        try {
            return Integer.parseInt(input);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(ExceptionType.INVALID_DATE_TYPE.getMessage());
        }
    }

    private static boolean isDateOutOfRange(int input) {
        return input < SystemNumValue.FIRST_DATE.getValue() ||
                input > SystemNumValue.END_DATE.getValue();
    }

    public static void validateMenuInput(String input) {
        if (!isMatchFormat(input, SystemTextValue.MENU_INPUT_FORM.getValue())) {
            throw new IllegalArgumentException(ExceptionType.INVALID_MENU_FORMAT.getMessage());
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
            throw new IllegalArgumentException(ExceptionType.INVALID_MENU_FORMAT.getMessage());
        }
    }

    private static void validateMenuNames(List<String> menus) {
        List<String> names = new ArrayList<>();
        for (String menu : menus) {
            String name = Arrays.asList(menu.split("-")).get(0);
            MenuType.getByName(name);
            names.add(name);
        }
        if(hasDuplicatedMenuName(names)) {
            throw new IllegalArgumentException(ExceptionType.INVALID_MENU_FORMAT.getMessage());
        }
        if(hasOnlyDrink(names)) {
            throw new IllegalArgumentException(ExceptionType.ORDERED_ONLY_DRINK.getMessage());
        }
    }

    private static boolean hasOnlyDrink(List<String> menuNames) {
        for(String name : menuNames) {
            if(!MenuType.getByName(name).getCategory().equals("DRINK")) {
                return false;
            }
        }
        return true;
    }

    private static boolean hasDuplicatedMenuName(List<String> menuNames) {
        return menuNames.stream().distinct().count() < menuNames.size();
    }

    private static boolean isInvalidMenuNum(int num) {
        return num > SystemNumValue.MAX_MENU_NUM.getValue();
    }

    private static boolean isMatchFormat(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
}
