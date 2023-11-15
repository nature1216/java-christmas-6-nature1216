package christmas.enumeration;

public enum SystemNumValue {
    MAX_MENU_NUM(20),
    FIRST_DATE(1),
    END_DATE(31),
    GIFT_THRESHOLD(120000),
    MINIMUM_AMOUNT_FOR_BENEFIT(10000),
    GIFT_NUM(1),
    EVENT_YEAR(2023),
    EVENT_MONTH(12),
    SPECIAL_DISCOUNT(1000),
    X_MAS_DISCOUNT_BASE(1000),
    WEEKDAY_DISCOUNT_AMOUNT(2023),
    WEEKEND_DISCOUNT_AMOUNT(2023);

    private int value;

    SystemNumValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
