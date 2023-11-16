package christmas.enumeration;

public enum SystemTextValue {
    MENU_INPUT_FORM("^([가-힣]+-\\d+,)*[가-힣]+-\\d+$"),
    GIFT(MenuType.CHAMPAGNE.getName());

    private String value;

    SystemTextValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
