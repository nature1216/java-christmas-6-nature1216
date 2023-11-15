package christmas.view;

import christmas.enumeration.NoticeType;
import camp.nextstep.edu.missionutils.Console;
import christmas.validator.Validator;

public class InputView {
    public String readDate() {
        System.out.println(NoticeType.DATE_INPUT.getMessage());
        String input = Console.readLine();
        Validator.validateDateInput(input);
        return input;
    }

    public String readMenu() {
        System.out.println(NoticeType.MENU_INPUT.getMessage());
        String input = Console.readLine();
        Validator.validateMenuInput(input.replaceAll("\\s", ""));

        return input;
    }
}
