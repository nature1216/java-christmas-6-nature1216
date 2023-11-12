package christmas.enumeration;

import java.time.DayOfWeek;

public enum SystemValue {
    MENU_INPUT_FORM("^([가-힣]+-\\d+,)*[가-힣]+-\\d+$"),
    MAX_MENU_NUM(20),
    FIRST_DATE(1),
    END_DATE(31),
    GIFT_THRESHOLD(120000),
    GIFT(MenuType.CHAMPAGNE.getName()),
    GIFT_NUM(1),
    EVENT_YEAR(2023),
    EVENT_MONTH(12),
    SPECIAL_DISCOUNT(DayOfWeek.SUNDAY),
    WEEKDAY_DISCOUNT_CATEGORY("DESSERT"),
    WEEKEND_DISCOUNT_CATEGORY("MAIN"),
    ;

    private Object value;

    SystemValue(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

}
