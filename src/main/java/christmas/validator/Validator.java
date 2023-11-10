package christmas.validator;

public class Validator {
    public static void validateDateInput(String input) {
        int date = validateDateType();
        if(isDateOutOfRange(date)) {
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

    public static boolean isDateOutOfRange(int input) {
        return input < 1 || input > 31;
    }
}
