package christmas.enumeration;

public enum SystemValue {
    MENU_INPUT_FORM("^([가-힣]+-\\d+,)*[가-힣]+-\\d+$"),
    MAX_MENU_NUM(20),
    FIRST_DATE(1),
    END_DATE(31);

    private Object value;

    SystemValue(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

}
