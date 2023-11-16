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

public class MenuValidator {
    public static void validateInput(String input) {
        validateFormat(input);
        List<String> menus = Arrays.asList(input.split(","));

        validateNames(menus);
        validateNums(menus);
    }

    public static void validateFormat(String input) {
        if (!isMatchFormat(input, SystemTextValue.MENU_INPUT_FORM.getValue())) {
            throw new IllegalArgumentException(ExceptionType.INVALID_MENU_FORMAT.getMessage());
        }
    }

    public static void validateNums(List<String> menus) {
        int num = 0;
        for (String menu : menus) {
            if(Integer.parseInt(Arrays.asList(menu.split("-")).get(1)) <= 0) {
                throw new IllegalArgumentException(ExceptionType.INVALID_MENU_FORMAT.getMessage());
            }
            num += Integer.parseInt(Arrays.asList(menu.split("-")).get(1));
        }
        if (isInvalidNum(num)) {
            throw new IllegalArgumentException(ExceptionType.INVALID_MENU_FORMAT.getMessage());
        }
    }

    public static void validateNames(List<String> menus) {
        List<String> names = new ArrayList<>();
        for (String menu : menus) {
            String name = Arrays.asList(menu.split("-")).get(0);
            MenuType.getByName(name);
            names.add(name);
        }
        if(hasDuplicatedName(names)) {
            throw new IllegalArgumentException(ExceptionType.DUPLICATED_MENU.getMessage());
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

    private static boolean hasDuplicatedName(List<String> menuNames) {
        return menuNames.stream().distinct().count() < menuNames.size();
    }

    private static boolean isInvalidNum(int num) {
        return num > SystemNumValue.MAX_MENU_NUM.getValue();
    }

    private static boolean isMatchFormat(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
}
