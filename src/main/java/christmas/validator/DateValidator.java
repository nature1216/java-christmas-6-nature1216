package christmas.validator;

import christmas.enumeration.ExceptionType;
import christmas.enumeration.SystemNumValue;

public class DateValidator {
    public static void validateInput(String input) {
        int date = validateType(input);
        if (isOutOfRange(date)) {
            throw new IllegalArgumentException(ExceptionType.INVALID_DATE_TYPE.getMessage());
        }
    }

    public static int validateType(String input) {
        try {
            return Integer.parseInt(input);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(ExceptionType.INVALID_DATE_TYPE.getMessage());
        }
    }

    private static boolean isOutOfRange(int input) {
        return input < SystemNumValue.FIRST_DATE.getValue() ||
                input > SystemNumValue.END_DATE.getValue();
    }
}
