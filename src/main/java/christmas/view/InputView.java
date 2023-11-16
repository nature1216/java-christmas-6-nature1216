package christmas.view;

import christmas.enumeration.NoticeType;
import camp.nextstep.edu.missionutils.Console;
import christmas.validator.MenuValidator;
import christmas.validator.DateValidator;

public class InputView {
    public String readDate() {
        System.out.println(NoticeType.DATE_INPUT.getMessage());
        String input = Console.readLine();
        DateValidator.validateInput(input);
        return input;
    }

    public String readMenu() {
        System.out.println(NoticeType.MENU_INPUT.getMessage());
        String input = Console.readLine();
        MenuValidator.validateInput(input.replaceAll("\\s", ""));

        return input;
    }
}
